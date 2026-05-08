public class QuantityMeasurementApp {

    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0);

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

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength inch1 =
                new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength inch2 =
                new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println(
                "Equal (" + feet.equals(inches) + ")"
        );

        System.out.println(
                "Equal (" + inch1.equals(inch2) + ")"
        );
    }
}