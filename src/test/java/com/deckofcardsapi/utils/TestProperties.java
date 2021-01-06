package com.deckofcardsapi.utils;

import org.apache.poi.ss.formula.functions.T;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private String currentEnv;
    private Properties properties;
    private static TestProperties testProperties;

    public static void loadProperties(String env) throws IOException {
        if (testProperties == null) {
            testProperties = new TestProperties();
            testProperties.currentEnv = env;
            FileInputStream Locator = new FileInputStream("src/test/resources/" + testProperties.currentEnv + ".properties");
            testProperties.properties = new Properties();
            testProperties.properties.load(Locator);
        }
    }

    public static TestProperties getTestProperties() throws Exception {
        if (testProperties == null) {
            throw new Exception("Test properties have not been loaded yet. Please use function \" loadProperites(String env)\" to load properties.");
        }
        return testProperties;
    }

    public String getCurrentEnv() {
        return currentEnv;
    }

    public void setCurrentEnv(String currentEnv) {
        this.currentEnv = currentEnv;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
