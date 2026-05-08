import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;

    @Test
    public void testSubtraction_SameUnit_FeetMinusFeet() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).subtract(
                        new Quantity<>(
                                5.0,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                5.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testSubtraction_CrossUnit_FeetMinusInches() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).subtract(
                        new Quantity<>(
                                6.0,
                                LengthUnit.INCHES
                        )
                );

        assertEquals(
                9.5,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Inches() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).subtract(
                        new Quantity<>(
                                6.0,
                                LengthUnit.INCHES
                        ),
                        LengthUnit.INCHES
                );

        assertEquals(
                114.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testSubtraction_ResultingInNegative() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        5.0,
                        LengthUnit.FEET
                ).subtract(
                        new Quantity<>(
                                10.0,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                -5.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testSubtraction_ResultingInZero() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).subtract(
                        new Quantity<>(
                                120.0,
                                LengthUnit.INCHES
                        )
                );

        assertEquals(
                0.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testDivision_SameUnit() {

        double result =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).divide(
                        new Quantity<>(
                                2.0,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                5.0,
                result,
                EPSILON
        );
    }

    @Test
    public void testDivision_CrossUnit() {

        double result =
                new Quantity<>(
                        24.0,
                        LengthUnit.INCHES
                ).divide(
                        new Quantity<>(
                                2.0,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                1.0,
                result,
                EPSILON
        );
    }

    @Test
    public void testDivision_RatioLessThanOne() {

        double result =
                new Quantity<>(
                        5.0,
                        LengthUnit.FEET
                ).divide(
                        new Quantity<>(
                                10.0,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                0.5,
                result,
                EPSILON
        );
    }

    @Test
    public void testDivision_ByZero() {

        assertThrows(
                ArithmeticException.class,
                () -> new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).divide(
                        new Quantity<>(
                                0.0,
                                LengthUnit.FEET
                        )
                )
        );
    }

    @Test
    public void testSubtraction_NullOperand() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).subtract(null)
        );
    }

    @Test
    public void testDivision_NullOperand() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).divide(null)
        );
    }

    @Test
    public void testSubtraction_AllMeasurementCategories() {

        Quantity<WeightUnit> weight =
                new Quantity<>(
                        10.0,
                        WeightUnit.KILOGRAM
                ).subtract(
                        new Quantity<>(
                                5000.0,
                                WeightUnit.GRAM
                        )
                );

        Quantity<VolumeUnit> volume =
                new Quantity<>(
                        5.0,
                        VolumeUnit.LITRE
                ).subtract(
                        new Quantity<>(
                                500.0,
                                VolumeUnit.MILLILITRE
                        )
                );

        assertEquals(
                5.0,
                weight.getValue(),
                EPSILON
        );

        assertEquals(
                4.5,
                volume.getValue(),
                EPSILON
        );
    }

    @Test
    public void testDivision_AllMeasurementCategories() {

        double weight =
                new Quantity<>(
                        10.0,
                        WeightUnit.KILOGRAM
                ).divide(
                        new Quantity<>(
                                5.0,
                                WeightUnit.KILOGRAM
                        )
                );

        double volume =
                new Quantity<>(
                        5.0,
                        VolumeUnit.LITRE
                ).divide(
                        new Quantity<>(
                                10.0,
                                VolumeUnit.LITRE
                        )
                );

        assertEquals(
                2.0,
                weight,
                EPSILON
        );

        assertEquals(
                0.5,
                volume,
                EPSILON
        );
    }

    @Test
    public void testSubtraction_Immutability() {

        Quantity<LengthUnit> original =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                );

        original.subtract(
                new Quantity<>(
                        5.0,
                        LengthUnit.FEET
                )
        );

        assertEquals(
                10.0,
                original.getValue(),
                EPSILON
        );
    }

    @Test
    public void testDivision_Immutability() {

        Quantity<LengthUnit> original =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                );

        original.divide(
                new Quantity<>(
                        2.0,
                        LengthUnit.FEET
                )
        );

        assertEquals(
                10.0,
                original.getValue(),
                EPSILON
        );
    }
}