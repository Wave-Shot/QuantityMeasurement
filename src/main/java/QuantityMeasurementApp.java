public class QuantityMeasurementApp {

    enum LengthUnit {

        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(
                double value,
                LengthUnit unit
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

            return value *
                    unit.getConversionFactor();
        }

        public QuantityLength convertTo(
                LengthUnit targetUnit
        ) {

            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null"
                );
            }

            double baseValue =
                    convertToBaseUnit();

            double convertedValue =
                    baseValue /
                            targetUnit.getConversionFactor();

            return new QuantityLength(
                    convertedValue,
                    targetUnit
            );
        }

        private static QuantityLength performAddition(
                QuantityLength first,
                QuantityLength second,
                LengthUnit targetUnit
        ) {

            double firstBase =
                    first.convertToBaseUnit();

            double secondBase =
                    second.convertToBaseUnit();

            double totalBase =
                    firstBase + secondBase;

            double result =
                    totalBase /
                            targetUnit.getConversionFactor();

            return new QuantityLength(
                    result,
                    targetUnit
            );
        }

        public QuantityLength add(
                QuantityLength other
        ) {

            if (other == null) {
                throw new IllegalArgumentException(
                        "Second operand cannot be null"
                );
            }

            return performAddition(
                    this,
                    other,
                    this.unit
            );
        }

        public QuantityLength add(
                QuantityLength other,
                LengthUnit targetUnit
        ) {

            if (other == null) {
                throw new IllegalArgumentException(
                        "Second operand cannot be null"
                );
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null"
                );
            }

            return performAddition(
                    this,
                    other,
                    targetUnit
            );
        }

        public static QuantityLength add(
                QuantityLength first,
                QuantityLength second,
                LengthUnit targetUnit
        ) {

            if (first == null ||
                    second == null) {

                throw new IllegalArgumentException(
                        "Operands cannot be null"
                );
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null"
                );
            }

            return performAddition(
                    first,
                    second,
                    targetUnit
            );
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
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

            QuantityLength quantity =
                    (QuantityLength) obj;

            return Math.abs(
                    this.convertToBaseUnit() -
                            quantity.convertToBaseUnit()
            ) < 1e-6;
        }

        @Override
        public String toString() {

            return "Quantity(" +
                    value +
                    ", " +
                    unit +
                    ")";
        }
    }

    public static void main(String[] args) {

        QuantityLength feet =
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                );

        QuantityLength inches =
                new QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                );

        System.out.println(
                feet.add(
                        inches,
                        LengthUnit.FEET
                )
        );

        System.out.println(
                feet.add(
                        inches,
                        LengthUnit.INCHES
                )
        );

        System.out.println(
                feet.add(
                        inches,
                        LengthUnit.YARDS
                )
        );

        System.out.println(
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS
                ).add(
                        new QuantityLength(
                                3.0,
                                LengthUnit.FEET
                        ),
                        LengthUnit.YARDS
                )
        );

        System.out.println(
                new QuantityLength(
                        36.0,
                        LengthUnit.INCHES
                ).add(
                        new QuantityLength(
                                1.0,
                                LengthUnit.YARDS
                        ),
                        LengthUnit.FEET
                )
        );
    }
}