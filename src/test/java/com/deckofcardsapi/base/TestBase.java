package com.deckofcardsapi.base;

import com.deckofcardsapi.utils.TestProperties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import java.util.Properties;


public class TestBase {
    protected Logger logger;
    protected Properties properties;

    @BeforeClass
    public void setup() throws Exception {
        logger= Logger.getLogger("");
        PropertyConfigurator.configure("src/test/resources/logs/Log4j.properties"); //added logger

        // Uncomment this line if we run only one test method because listener will not be trigger when we run on test
        // method level. TODO: Will improve this in future
        //TestProperties.loadProperties("qa");
        properties = TestProperties.getTestProperties().getProperties();
        logger.log(Level.INFO, "Testing with environment: " + TestProperties.getTestProperties().getCurrentEnv());
    }
}

