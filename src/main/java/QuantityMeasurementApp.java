public class QuantityMeasurementApp {

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

            return unit.convertToBaseUnit(value);
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
                    unit.convertToBaseUnit(value);

            double convertedValue =
                    targetUnit.convertFromBaseUnit(
                            baseValue
                    );

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
                    first.unit.convertToBaseUnit(
                            first.value
                    );

            double secondBase =
                    second.unit.convertToBaseUnit(
                            second.value
                    );

            double totalBase =
                    firstBase + secondBase;

            double result =
                    targetUnit.convertFromBaseUnit(
                            totalBase
                    );

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

        QuantityLength yards =
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS
                );

        QuantityLength cm =
                new QuantityLength(
                        2.54,
                        LengthUnit.CENTIMETERS
                );

        System.out.println(
                feet.convertTo(
                        LengthUnit.INCHES
                )
        );

        System.out.println(
                feet.add(
                        inches,
                        LengthUnit.FEET
                )
        );

        System.out.println(
                new QuantityLength(
                        36.0,
                        LengthUnit.INCHES
                ).equals(yards)
        );

        System.out.println(
                yards.add(
                        new QuantityLength(
                                3.0,
                                LengthUnit.FEET
                        ),
                        LengthUnit.YARDS
                )
        );

        System.out.println(
                cm.convertTo(
                        LengthUnit.INCHES
                )
        );

        System.out.println(
                LengthUnit.FEET
                        .convertToBaseUnit(12.0)
        );

        System.out.println(
                LengthUnit.INCHES
                        .convertToBaseUnit(12.0)
        );
    }
}