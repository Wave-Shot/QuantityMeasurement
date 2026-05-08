public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    void demonstrateSubtraction(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit
    ) {

        System.out.println(
                q1.subtract(q2, targetUnit)
        );
    }

    public static <U extends IMeasurable>
    void demonstrateDivision(
            Quantity<U> q1,
            Quantity<U> q2
    ) {

        System.out.println(
                q1.divide(q2)
        );
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> inches =
                new Quantity<>(
                        6.0,
                        LengthUnit.INCHES
                );

        demonstrateSubtraction(
                feet,
                inches,
                LengthUnit.FEET
        );

        demonstrateSubtraction(
                feet,
                inches,
                LengthUnit.INCHES
        );

        demonstrateDivision(
                new Quantity<>(
                        24.0,
                        LengthUnit.INCHES
                ),
                new Quantity<>(
                        2.0,
                        LengthUnit.FEET
                )
        );

        Quantity<WeightUnit> kg =
                new Quantity<>(
                        10.0,
                        WeightUnit.KILOGRAM
                );

        Quantity<WeightUnit> gram =
                new Quantity<>(
                        5000.0,
                        WeightUnit.GRAM
                );

        demonstrateSubtraction(
                kg,
                gram,
                WeightUnit.KILOGRAM
        );

        demonstrateDivision(
                kg,
                new Quantity<>(
                        5.0,
                        WeightUnit.KILOGRAM
                )
        );

        Quantity<VolumeUnit> litre =
                new Quantity<>(
                        5.0,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> ml =
                new Quantity<>(
                        500.0,
                        VolumeUnit.MILLILITRE
                );

        demonstrateSubtraction(
                litre,
                ml,
                VolumeUnit.LITRE
        );

        demonstrateDivision(
                litre,
                new Quantity<>(
                        10.0,
                        VolumeUnit.LITRE
                )
        );
    }
}