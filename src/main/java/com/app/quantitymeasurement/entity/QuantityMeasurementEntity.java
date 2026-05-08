package com.app.quantitymeasurement.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity
        implements Serializable {

    private static final long
            serialVersionUID = 1L;

    private final String operation;
    private final String result;
    private final boolean error;
    private final String errorMessage;

    public QuantityMeasurementEntity(
            String operation,
            String result
    ) {

        this.operation = operation;
        this.result = result;
        this.error = false;
        this.errorMessage = null;
    }

    public QuantityMeasurementEntity(
            String operation,
            String result,
            boolean error,
            String errorMessage
    ) {

        this.operation = operation;
        this.result = result;
        this.error = error;
        this.errorMessage =
                errorMessage;
    }

    public String getOperation() {

        return operation;
    }

    public String getResult() {

        return result;
    }

    public boolean hasError() {

        return error;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    @Override
    public String toString() {

        if (error) {

            return "ERROR : "
                    + errorMessage;
        }

        return operation
                + " -> "
                + result;
    }
}