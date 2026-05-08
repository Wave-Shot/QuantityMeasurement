package com.app.quantitymeasurement.service;

public interface IQuantityMeasurementService {

    String compare(
            double value1,
            String unit1,
            String type1,

            double value2,
            String unit2,
            String type2
    );
}