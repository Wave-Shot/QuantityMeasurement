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

        public QuantityLength add(
                QuantityLength other,
                LengthUnit targetUnit
        ) {

            double totalBase =
                    this.convertToBaseUnit() +
                            other.convertToBaseUnit();

            double result =
                    targetUnit.convertFromBaseUnit(
                            totalBase
                    );

            return new QuantityLength(
                    result,
                    targetUnit
            );
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
                    unit +
                    ")";
        }
    }

    static class QuantityWeight {

        private final double value;
        private final WeightUnit unit;

        public QuantityWeight(
                double value,
                WeightUnit unit
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

        public QuantityWeight convertTo(
                WeightUnit targetUnit
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

            return new QuantityWeight(
                    convertedValue,
                    targetUnit
            );
        }

        public QuantityWeight add(
                QuantityWeight other
        ) {

            return add(other, this.unit);
        }

        public QuantityWeight add(
                QuantityWeight other,
                WeightUnit targetUnit
        ) {

            if (other == null) {

                throw new IllegalArgumentException(
                        "Other weight cannot be null"
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

            return new QuantityWeight(
                    result,
                    targetUnit
            );
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

            QuantityWeight quantity =
                    (QuantityWeight) obj;

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
                    unit +
                    ")";
        }
    }

    public static void main(String[] args) {

        QuantityWeight kg =
                new QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        QuantityWeight gram =
                new QuantityWeight(
                        1000.0,
                        WeightUnit.GRAM
                );

        QuantityWeight pound =
                new QuantityWeight(
                        2.20462,
                        WeightUnit.POUND
                );

        System.out.println(
                kg.equals(gram)
        );

        System.out.println(
                kg.equals(pound)
        );

        System.out.println(
                kg.convertTo(
                        WeightUnit.GRAM
                )
        );

        System.out.println(
                pound.convertTo(
                        WeightUnit.KILOGRAM
                )
        );

        System.out.println(
                kg.add(
                        gram
                )
        );

        System.out.println(
                kg.add(
                        gram,
                        WeightUnit.GRAM
                )
        );

        QuantityLength feet =
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                );

        System.out.println(
                kg.equals(feet)
        );
    }
}