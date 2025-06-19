package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DataBaseConnector {
    private static final String URL_KEY = PropertiesReader.getProperty("db.url");
    private static final String PASSWORD_KEY = PropertiesReader.getProperty("db.password");
    private static final String USER_NAME_KEY = PropertiesReader.getProperty("db.user.name");

    public static Connection connection() {

        try {
            Connection connection = DriverManager.getConnection(URL_KEY, USER_NAME_KEY, PASSWORD_KEY);
            System.out.println("The connection was succeed");
            return connection;
        } catch (SQLException sql) {
            System.out.println("Problem with connection to database!");
            sql.printStackTrace();
            throw new RuntimeException();
        }


    }

    private DataBaseConnector() {}
}
