package com.solvd.airportsystem.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {

    private static ConnectionPool instance;
    private final List<Connection> connections;

    private ConnectionPool() {
        try {
            Class.forName(Config.DRIVER.getValue());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver not found", e);
        }

        int poolSize = Integer.parseInt(Config.POOL_SIZE.getValue());
        this.connections = new ArrayList<>();

        IntStream.range(0, poolSize)
                .boxed()
                .forEach(i -> connections.add(createConnection()));
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection(
                    Config.URL.getValue(),
                    Config.USERNAME.getValue(),
                    Config.PASSWORD.getValue()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public synchronized Connection getConnection() {
        while (connections.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for connection", e);
            }
        }
        return connections.remove(0);
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            connections.add(connection);
            notify();
        }
    }
}

