package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.client.UserServiceClient;

import com.app.quantitymeasurement.dto.HistoryRequest;

import com.app.quantitymeasurement.service.IQuantityMeasurementService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quantity")
public class QuantityMeasurementController {

    private final
    IQuantityMeasurementService
            service;

    private final
    UserServiceClient
            userServiceClient;

    public QuantityMeasurementController(

            IQuantityMeasurementService
                    service,

            UserServiceClient
                    userServiceClient
    ) {

        this.service = service;

        this.userServiceClient =
                userServiceClient;
    }

    @GetMapping("/compare")
    public String compare(

            @RequestParam double value1,

            @RequestParam String unit1,

            @RequestParam String type1,

            @RequestParam double value2,

            @RequestParam String unit2,

            @RequestParam String type2
    ) {

        String result =
                service.compare(

                        value1,
                        unit1,
                        type1,

                        value2,
                        unit2,
                        type2
                );

        HistoryRequest request =
                new HistoryRequest();

        request.setUserId(1L);

        request.setType(type1);

        request.setFromUnit(unit1);

        request.setToUnit(unit2);

        request.setInputValue(value1);

        request.setOutputValue(value2);

        userServiceClient.saveHistory(
                request
        );

        return result;
    }
}