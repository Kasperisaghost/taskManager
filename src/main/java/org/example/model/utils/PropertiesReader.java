package org.example.model.utils;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {
    private static Properties properties;

    static {
        properties = new Properties();
        try (var input = new FileInputStream("src/main/resources/config.properties")) {
            if (input == null) {
                throw new RuntimeException("Can't find properties config file");
            }

            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error while load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void printAll() {
        properties.list(System.out);
    }
}
