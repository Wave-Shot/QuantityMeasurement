package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.quantity.Quantity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.IMeasurable;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.unit.WeightUnit;

import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final
    QuantityMeasurementRepository
            repository;

    public
    QuantityMeasurementServiceImpl(
            QuantityMeasurementRepository
                    repository
    ) {

        this.repository = repository;
    }

    private IMeasurable parseUnit(
            String type,
            String unit
    ) {

        return switch (type) {

            case "Length" ->
                    LengthUnit.valueOf(unit);

            case "Weight" ->
                    WeightUnit.valueOf(unit);

            case "Volume" ->
                    VolumeUnit.valueOf(unit);

            case "Temperature" ->
                    TemperatureUnit.valueOf(unit);

            default ->
                    throw new IllegalArgumentException(
                            "Invalid type"
                    );
        };
    }

    @Override
    public String compare(
            double value1,
            String unit1,
            String type1,

            double value2,
            String unit2,
            String type2
    ) {

        Quantity<?> q1 =
                new Quantity<>(
                        value1,
                        parseUnit(type1, unit1)
                );

        Quantity<?> q2 =
                new Quantity<>(
                        value2,
                        parseUnit(type2, unit2)
                );

        boolean result =
                q1.equals(q2);

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setOperation("COMPARE");
        entity.setResult(
                String.valueOf(result)
        );
        entity.setError(false);

        repository.save(entity);

        return String.valueOf(result);
    }
}