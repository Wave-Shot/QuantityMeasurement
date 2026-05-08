package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.service.IQuantityMeasurementService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quantity")
public class QuantityMeasurementController {

    private final
    IQuantityMeasurementService
            service;

    public QuantityMeasurementController(
            IQuantityMeasurementService
                    service
    ) {

        this.service = service;
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

        return service.compare(
                value1,
                unit1,
                type1,
                value2,
                unit2,
                type2
        );
    }
}