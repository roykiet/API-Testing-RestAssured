package com.deckofcardsapi.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.deckofcardsapi.contants.Properties;
import org.apache.log4j.Level;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.apache.log4j.Logger;

public class TestListener  extends TestListenerAdapter {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;
    private Logger logger = Logger.getLogger("");

    public void onStart(ITestContext testContext)
    {
        //specify location of the report
        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/reports/report.html");

        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Rest API Testing Report");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent=new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Project Name","Deck Of Card API");
        try {
            // Load configuration properties
            String env = System.getProperty("env");
            if (env == null || env.isEmpty()) {
                env = "qa";
            }
            TestProperties.loadProperties(env);
            TestProperties testProperties = TestProperties.getTestProperties();
            extent.setSystemInfo("Base URL",testProperties.getProperties().getProperty(Properties.BASE_URL));
            extent.setSystemInfo("Environment",testProperties.getCurrentEnv());
        } catch (Exception e) {
            extent.setSystemInfo("Host name","N/A");
            extent.setSystemInfo("Environment","N/A");
            logger.log(Level.ERROR, e.getStackTrace());
        }
    }
    public void onTestStart(ITestResult result) {
        logger.log(Level.INFO, "Start test: " + result.getName());
    }

    public void onTestSuccess(ITestResult result)
    {
        test=extent.createTest(result.getName());
        logger.log(Level.INFO, "Test " + result.getName() + " is PASSED");
        test.log(Status.PASS, "Test " + result.getName() + " is PASSED");
    }

    public void onTestFailure(ITestResult result)
    {
        test=extent.createTest(result.getName()); // create new entry in the report
        logger.log(Level.INFO, "Test " + result.getName() + " is FAILED");
        test.log(Status.FAIL, "Test " + result.getName() + " is FAILED because of error: \n" + result.getThrowable());
    }

    public void onTestSkipped(ITestResult result)
    {
        test=extent.createTest(result.getName()); // create new entry in the report
        logger.log(Level.INFO, "Test " + result.getName() + " is SKIPPED");
        test.log(Status.SKIP, "Test SKIPPED IS " + result.getName());
    }

    public void onFinish(ITestContext testContext)
    {
        extent.flush();
    }
}
