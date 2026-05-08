public class QuantityMeasurementApp {

    enum LengthUnit {

        FEET(12.0),
        INCH(1.0),
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

        public QuantityLength(double value, LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        private double convertToBaseUnit() {
            return value * unit.getConversionFactor();
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            QuantityLength quantity =
                    (QuantityLength) obj;

            return Double.compare(
                    this.convertToBaseUnit(),
                    quantity.convertToBaseUnit()
            ) == 0;
        }
    }

    public static void main(String[] args) {

        QuantityLength yard =
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS
                );

        QuantityLength feet =
                new QuantityLength(
                        3.0,
                        LengthUnit.FEET
                );

        QuantityLength inches =
                new QuantityLength(
                        36.0,
                        LengthUnit.INCH
                );

        QuantityLength cm =
                new QuantityLength(
                        1.0,
                        LengthUnit.CENTIMETERS
                );

        QuantityLength inch =
                new QuantityLength(
                        0.393701,
                        LengthUnit.INCH
                );

        System.out.println(
                "Equal (" + yard.equals(feet) + ")"
        );

        System.out.println(
                "Equal (" + yard.equals(inches) + ")"
        );

        System.out.println(
                "Equal (" + cm.equals(inch) + ")"
        );
    }
}