package com.solvd.airportsystem.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum Config {

    DRIVER("driver"),
    URL("url"),
    USERNAME("username"),
    PASSWORD("password"),
    POOL_SIZE("poolSize");

    private final String key;
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("config.properties file not found");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    Config(String key) {
        this.key = key;
    }

    public String getValue() {
        return properties.getProperty(key);
    }
}


