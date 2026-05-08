package com.app.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {

    private static ConnectionPool instance;

    private final Queue<Connection>
            connections =
            new LinkedList<>();

    private final int poolSize;

    private ConnectionPool()
            throws SQLException {

        poolSize =
                Integer.parseInt(
                        ApplicationConfig
                                .getProperty(
                                        "db.pool.size"
                                )
                );

        initializePool();
    }

    private void initializePool()
            throws SQLException {

        for (int i = 0;
             i < poolSize;
             i++) {

            connections.add(
                    createConnection()
            );
        }
    }

    private Connection createConnection()
            throws SQLException {

        return DriverManager.getConnection(
                ApplicationConfig
                        .getProperty("db.url"),

                ApplicationConfig
                        .getProperty(
                                "db.username"
                        ),

                ApplicationConfig
                        .getProperty(
                                "db.password"
                        )
        );
    }

    public static synchronized
    ConnectionPool getInstance()
            throws SQLException {

        if (instance == null) {

            instance =
                    new ConnectionPool();
        }

        return instance;
    }

    public synchronized Connection
    getConnection() {

        return connections.poll();
    }

    public synchronized void
    releaseConnection(
            Connection connection
    ) {

        connections.offer(connection);
    }
}