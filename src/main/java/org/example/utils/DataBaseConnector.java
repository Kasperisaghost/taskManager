package org.example.utils;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public final class DataBaseConnector {
    private static final String URL_KEY = PropertiesReader.getProperty("db.url");
    private static final String PASSWORD_KEY = PropertiesReader.getProperty("db.password");
    private static final String USER_NAME_KEY = PropertiesReader.getProperty("db.user.name");
    private static final int DEFAULT_QUEUE_SIZE = 10;
    private static final int TIMEOUT_SECONDS = 10;

    private static BlockingQueue<Connection> arrayBlockingDeque;


    static {
        initializePool();
    }

    private static void initializePool() {
        arrayBlockingDeque = new ArrayBlockingQueue<>(DEFAULT_QUEUE_SIZE);
        for (int i = 0; i < DEFAULT_QUEUE_SIZE; i++) {
            Connection originalConnection = connection();
            var proxyConnection = (Connection) Proxy.newProxyInstance(DataBaseConnector.class.getClassLoader(),
                    new Class[] {Connection.class},
                    ((proxy, method, args) -> {
                        if ("close".equals(method.getName())) {
                            arrayBlockingDeque.add((Connection) proxy);
                            return null;
                        }
                        return method.invoke(originalConnection, args);
                    }));
            arrayBlockingDeque.add(proxyConnection);
        }

    }

    public static Connection getConnection()  {
        try {
            return arrayBlockingDeque.poll(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

     private static Connection connection() {

        try {
            Connection connection = DriverManager.getConnection(URL_KEY, USER_NAME_KEY, PASSWORD_KEY);
            return connection;
        } catch (SQLException sql) {
            System.out.println("Problem with connection to database!");
            sql.printStackTrace();
            throw new RuntimeException();
        }


    }

    private DataBaseConnector() {}
}
