package com.app.quantitymeasurement;

import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import com.app.quantitymeasurement.util.ApplicationConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Logger;

public class QuantityMeasurementApp {

    private static final Logger logger =
            Logger.getLogger(
                    QuantityMeasurementApp.class.getName()
            );

    public static void main(String[] args) {

        initializeDatabase();

        IQuantityMeasurementRepository
                repository =
                new QuantityMeasurementDatabaseRepository();

        IQuantityMeasurementService
                service =
                new QuantityMeasurementServiceImpl(
                        repository
                );

        QuantityMeasurementController
                controller =
                new QuantityMeasurementController(
                        service
                );

        logger.info(
                "Application initialized successfully"
        );

        controller.performComparison(
                new QuantityDTO(
                        1.0,
                        "FEET",
                        "Length"
                ),
                new QuantityDTO(
                        12.0,
                        "INCHES",
                        "Length"
                )
        );

        controller.performConversion(
                new QuantityDTO(
                        100.0,
                        "CELSIUS",
                        "Temperature"
                ),
                "FAHRENHEIT"
        );

        controller.performAddition(
                new QuantityDTO(
                        1.0,
                        "KILOGRAM",
                        "Weight"
                ),
                new QuantityDTO(
                        1000.0,
                        "GRAM",
                        "Weight"
                ),
                "KILOGRAM"
        );

        controller.performSubtraction(
                new QuantityDTO(
                        10.0,
                        "FEET",
                        "Length"
                ),
                new QuantityDTO(
                        6.0,
                        "INCHES",
                        "Length"
                ),
                "FEET"
        );

        controller.performDivision(
                new QuantityDTO(
                        24.0,
                        "INCHES",
                        "Length"
                ),
                new QuantityDTO(
                        2.0,
                        "FEET",
                        "Length"
                )
        );

        controller.performAddition(
                new QuantityDTO(
                        100.0,
                        "CELSIUS",
                        "Temperature"
                ),
                new QuantityDTO(
                        50.0,
                        "CELSIUS",
                        "Temperature"
                ),
                "CELSIUS"
        );

        logger.info(
                "Total measurements stored: "
                        + repository.getTotalCount()
        );
    }

    private static void initializeDatabase() {

        try (
                Connection connection =
                        DriverManager.getConnection(
                                ApplicationConfig.getProperty(
                                        "db.url"
                                ),

                                ApplicationConfig.getProperty(
                                        "db.username"
                                ),

                                ApplicationConfig.getProperty(
                                        "db.password"
                                )
                        );

                Statement statement =
                        connection.createStatement()
        ) {

            statement.execute(
                    """
                    CREATE TABLE IF NOT EXISTS quantity_measurements(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        operation VARCHAR(100),
                        result VARCHAR(255),
                        error BOOLEAN,
                        error_message VARCHAR(255)
                    )
                    """
            );

            logger.info(
                    "Database initialized successfully"
            );

        } catch (Exception e) {

            logger.severe(
                    "Database initialization failed: "
                            + e.getMessage()
            );
        }
    }
}