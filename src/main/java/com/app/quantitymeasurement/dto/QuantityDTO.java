package com.app.quantitymeasurement.dto;

public class QuantityDTO {

    private final double value;
    private final String unit;
    private final String measurementType;

    public QuantityDTO(
            double value,
            String unit,
            String measurementType
    ) {

        this.value = value;
        this.unit = unit;
        this.measurementType =
                measurementType;
    }

    public double getValue() {

        return value;
    }

    public String getUnit() {

        return unit;
    }

    public String getMeasurementType() {

        return measurementType;
    }

    @Override
    public String toString() {

        return "DTO.QuantityDTO(" +
                value +
                ", " +
                unit +
                ", " +
                measurementType +
                ")";
    }
}