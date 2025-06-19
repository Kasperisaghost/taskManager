package org.example.utils;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertiesReader {
    private static Properties properties = new Properties();

    static {
        try(var data = PropertiesReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(data);
        } catch (IOException io) {
            System.out.println("Occured problem with properties reading");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
