package com.saucedemo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties = new Properties();
            if (input == null) {
                System.err.println("Sorry, unable to find config.properties in the classpath.");
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getUrl() {
        return getProperty("url");
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static boolean getHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("timeout.implicit"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("timeout.explicit"));
    }
}
