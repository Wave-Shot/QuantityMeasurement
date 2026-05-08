public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

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

    private void validateOperand(
            Quantity<U> other
    ) {

        if (other == null) {

            throw new IllegalArgumentException(
                    "Other quantity cannot be null"
            );
        }

        if (this.unit.getClass() !=
                other.unit.getClass()) {

            throw new IllegalArgumentException(
                    "Cross-category operation not allowed"
            );
        }
    }

    private double convertToBaseUnit() {

        return unit.convertToBaseUnit(value);
    }

    private double round(
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
                round(convertedValue),
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

        validateOperand(other);

        if (targetUnit == null) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double totalBase =
                this.convertToBaseUnit() +
                        other.convertToBaseUnit();

        double result =
                targetUnit.convertFromBaseUnit(
                        totalBase
                );

        return new Quantity<>(
                round(result),
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

        validateOperand(other);

        if (targetUnit == null) {

            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double resultBase =
                this.convertToBaseUnit() -
                        other.convertToBaseUnit();

        double result =
                targetUnit.convertFromBaseUnit(
                        resultBase
                );

        return new Quantity<>(
                round(result),
                targetUnit
        );
    }

    public double divide(
            Quantity<U> other
    ) {

        validateOperand(other);

        double divisor =
                other.convertToBaseUnit();

        if (Math.abs(divisor) < 1e-12) {

            throw new ArithmeticException(
                    "Division by zero"
            );
        }

        return this.convertToBaseUnit() /
                divisor;
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
                unit.getUnitName() +
                ")";
    }
}