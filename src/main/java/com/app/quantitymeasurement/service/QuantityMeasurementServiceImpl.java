package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.quantity.Quantity;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.IMeasurable;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.unit.WeightUnit;

public class
QuantityMeasurementServiceImpl
        implements
        IQuantityMeasurementService {

    private final
    IQuantityMeasurementRepository
            repository;

    public
    QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository
                    repository
    ) {

        this.repository =
                repository;
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
                    throw new
                            QuantityMeasurementException(
                            "Invalid type"
                    );
        };
    }

    private Quantity<?> buildQuantity(
            QuantityDTO dto
    ) {

        IMeasurable measurable =
                parseUnit(
                        dto.getMeasurementType(),
                        dto.getUnit()
                );

        return new Quantity<>(
                dto.getValue(),
                measurable
        );
    }

    @Override
    public QuantityMeasurementEntity compare(
            QuantityDTO q1,
            QuantityDTO q2
    ) {

        try {

            boolean result =
                    buildQuantity(q1)
                            .equals(
                                    buildQuantity(q2)
                            );

            QuantityMeasurementEntity
                    entity =
                    new
                            QuantityMeasurementEntity(
                            "COMPARE",
                            String.valueOf(
                                    result
                            )
                    );

            repository.save(entity);

            return entity;

        } catch (Exception e) {

            return new
                    QuantityMeasurementEntity(
                    "COMPARE",
                    null,
                    true,
                    e.getMessage()
            );
        }
    }

    @Override
    public QuantityMeasurementEntity convert(
            QuantityDTO source,
            String targetUnit
    ) {

        try {

            Quantity quantity =
                    buildQuantity(source);

            IMeasurable target =
                    parseUnit(
                            source
                                    .getMeasurementType(),
                            targetUnit
                    );

            Quantity result =
                    quantity.convertTo(
                            target
                    );

            QuantityMeasurementEntity
                    entity =
                    new
                            QuantityMeasurementEntity(
                            "CONVERT",
                            result.toString()
                    );

            repository.save(entity);

            return entity;

        } catch (Exception e) {

            return new
                    QuantityMeasurementEntity(
                    "CONVERT",
                    null,
                    true,
                    e.getMessage()
            );
        }
    }

    @Override
    public QuantityMeasurementEntity add(
            QuantityDTO q1,
            QuantityDTO q2,
            String targetUnit
    ) {

        try {

            Quantity quantity1 =
                    buildQuantity(q1);

            Quantity quantity2 =
                    buildQuantity(q2);

            IMeasurable target =
                    parseUnit(
                            q1.getMeasurementType(),
                            targetUnit
                    );

            Quantity result =
                    quantity1.add(
                            quantity2,
                            target
                    );

            QuantityMeasurementEntity
                    entity =
                    new
                            QuantityMeasurementEntity(
                            "ADD",
                            result.toString()
                    );

            repository.save(entity);

            return entity;

        } catch (Exception e) {

            return new
                    QuantityMeasurementEntity(
                    "ADD",
                    null,
                    true,
                    e.getMessage()
            );
        }
    }

    @Override
    public QuantityMeasurementEntity subtract(
            QuantityDTO q1,
            QuantityDTO q2,
            String targetUnit
    ) {

        try {

            Quantity quantity1 =
                    buildQuantity(q1);

            Quantity quantity2 =
                    buildQuantity(q2);

            IMeasurable target =
                    parseUnit(
                            q1.getMeasurementType(),
                            targetUnit
                    );

            Quantity result =
                    quantity1.subtract(
                            quantity2,
                            target
                    );

            QuantityMeasurementEntity
                    entity =
                    new
                            QuantityMeasurementEntity(
                            "SUBTRACT",
                            result.toString()
                    );

            repository.save(entity);

            return entity;

        } catch (Exception e) {

            return new
                    QuantityMeasurementEntity(
                    "SUBTRACT",
                    null,
                    true,
                    e.getMessage()
            );
        }
    }

    @Override
    public QuantityMeasurementEntity divide(
            QuantityDTO q1,
            QuantityDTO q2
    ) {

        try {

            Quantity quantity1 =
                    buildQuantity(q1);

            Quantity quantity2 =
                    buildQuantity(q2);

            double result =
                    quantity1.divide(
                            quantity2
                    );

            QuantityMeasurementEntity
                    entity =
                    new
                            QuantityMeasurementEntity(
                            "DIVIDE",
                            String.valueOf(
                                    result
                            )
                    );

            repository.save(entity);

            return entity;

        } catch (Exception e) {

            return new
                    QuantityMeasurementEntity(
                    "DIVIDE",
                    null,
                    true,
                    e.getMessage()
            );
        }
    }
}