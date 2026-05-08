import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {

            if (Math.abs(b) < 1e-12) {

                throw new ArithmeticException(
                        "Division by zero"
                );
            }

            return a / b;
        });

        private final DoubleBinaryOperator
                operator;

        ArithmeticOperation(
                DoubleBinaryOperator operator
        ) {

            this.operator = operator;
        }

        public double compute(
                double a,
                double b
        ) {

            return operator
                    .applyAsDouble(a, b);
        }
    }

    public Quantity(
            double value,
            U unit
    ) {

        validateValue(value);

        if (unit == null) {

            throw new IllegalArgumentException(
                    "Unit cannot be null"
            );
        }

        this.value = value;
        this.unit = unit;
    }

    private static void validateValue(
            double value
    ) {

        if (!Double.isFinite(value)) {

            throw new IllegalArgumentException(
                    "Invalid numeric value"
            );
        }
    }

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired,
            String operation
    ) {

        if (other == null) {

            throw new IllegalArgumentException(
                    "Other quantity cannot be null"
            );
        }

        validateValue(this.value);
        validateValue(other.value);

        if (this.unit.getClass() !=
                other.unit.getClass()) {

            throw new IllegalArgumentException(
                    "Cross-category operation not allowed"
            );
        }

        if (targetUnitRequired &&
                targetUnit == null) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        this.unit
                .validateOperationSupport(
                        operation
                );
    }

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation
    ) {

        double thisBase =
                this.convertToBaseUnit();

        double otherBase =
                other.convertToBaseUnit();

        return operation.compute(
                thisBase,
                otherBase
        );
    }

    private double convertToBaseUnit() {

        return unit.convertToBaseUnit(
                value
        );
    }

    private double roundToTwoDecimals(
            double value
    ) {

        return Math.round(
                value * 100.0
        ) / 100.0;
    }

    public Quantity<U> convertTo(
            U targetUnit
    ) {

        if (targetUnit == null) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double baseValue =
                convertToBaseUnit();

        double convertedValue =
                targetUnit.convertFromBaseUnit(
                        baseValue
                );

        return new Quantity<>(
                roundToTwoDecimals(
                        convertedValue
                ),
                targetUnit
        );
    }

    public Quantity<U> add(
            Quantity<U> other
    ) {

        return add(other, this.unit);
    }

    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit
    ) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true,
                "addition"
        );

        double resultBase =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.ADD
                );

        double converted =
                targetUnit
                        .convertFromBaseUnit(
                                resultBase
                        );

        return new Quantity<>(
                roundToTwoDecimals(
                        converted
                ),
                targetUnit
        );
    }

    public Quantity<U> subtract(
            Quantity<U> other
    ) {

        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(
            Quantity<U> other,
            U targetUnit
    ) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true,
                "subtraction"
        );

        double resultBase =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.SUBTRACT
                );

        double converted =
                targetUnit
                        .convertFromBaseUnit(
                                resultBase
                        );

        return new Quantity<>(
                roundToTwoDecimals(
                        converted
                ),
                targetUnit
        );
    }

    public double divide(
            Quantity<U> other
    ) {

        validateArithmeticOperands(
                other,
                null,
                false,
                "division"
        );

        return performBaseArithmetic(
                other,
                ArithmeticOperation.DIVIDE
        );
    }

    public double getValue() {

        return value;
    }

    public U getUnit() {

        return unit;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null ||
                getClass() != obj.getClass()) {

            return false;
        }

        Quantity<?> quantity =
                (Quantity<?>) obj;

        if (this.unit.getClass() !=
                quantity.unit.getClass()) {

            return false;
        }

        return Math.abs(
                this.convertToBaseUnit() -
                        quantity.convertToBaseUnit()
        ) < 1e-6;
    }

    @Override
    public int hashCode() {

        return Double.hashCode(
                convertToBaseUnit()
        );
    }

    @Override
    public String toString() {

        return "Quantity(" +
                value +
                ", " +
                unit.getUnitName()
                + ")";
    }
}