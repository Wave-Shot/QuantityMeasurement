package com.app.quantitymeasurement;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository
                repository =
                QuantityMeasurementCacheRepository
                        .getInstance();

        IQuantityMeasurementService
                service =
                new
                        QuantityMeasurementServiceImpl(
                        repository
                );

        QuantityMeasurementController
                controller =
                new
                        QuantityMeasurementController(
                        service
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
    }
}