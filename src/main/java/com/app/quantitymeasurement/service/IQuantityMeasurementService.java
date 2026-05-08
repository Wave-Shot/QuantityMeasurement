package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface
IQuantityMeasurementService {

    QuantityMeasurementEntity compare(
            QuantityDTO q1,
            QuantityDTO q2
    );

    QuantityMeasurementEntity convert(
            QuantityDTO source,
            String targetUnit
    );

    QuantityMeasurementEntity add(
            QuantityDTO q1,
            QuantityDTO q2,
            String targetUnit
    );

    QuantityMeasurementEntity subtract(
            QuantityDTO q1,
            QuantityDTO q2,
            String targetUnit
    );

    QuantityMeasurementEntity divide(
            QuantityDTO q1,
            QuantityDTO q2
    );
}