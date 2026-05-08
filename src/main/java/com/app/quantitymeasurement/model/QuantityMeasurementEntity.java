package com.app.quantitymeasurement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private Long id;

    private String operation;

    private String result;

    private boolean error;

    public QuantityMeasurementEntity() {

    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getOperation() {

        return operation;
    }

    public void setOperation(
            String operation
    ) {

        this.operation = operation;
    }

    public String getResult() {

        return result;
    }

    public void setResult(
            String result
    ) {

        this.result = result;
    }

    public boolean isError() {

        return error;
    }

    public void setError(
            boolean error
    ) {

        this.error = error;
    }
}