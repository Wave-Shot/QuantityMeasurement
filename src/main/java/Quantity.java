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

    private double convertToBaseUnit() {

        return unit.convertToBaseUnit(value);
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
                unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(
                        baseValue
                );

        convertedValue =
                Math.round(
                        convertedValue * 100.0
                ) / 100.0;

        return new Quantity<>(
                convertedValue,
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

        if (other == null) {

            throw new IllegalArgumentException(
                    "Other quantity cannot be null"
            );
        }

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

        result =
                Math.round(
                        result * 100.0
                ) / 100.0;

        return new Quantity<>(
                result,
                targetUnit
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
                unit.getUnitName() +
                ")";
    }
}