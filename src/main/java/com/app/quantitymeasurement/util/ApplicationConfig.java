package com.app.quantitymeasurement.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final
    Properties properties =
            new Properties();

    static {

        try {

            InputStream inputStream =
                    ApplicationConfig.class
                            .getClassLoader()
                            .getResourceAsStream(
                                    "application.properties"
                            );

            properties.load(inputStream);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    public static String getProperty(
            String key
    ) {

        return properties.getProperty(key);
    }
}