import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-3;

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(
                24.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(
                0.667,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS
                );

        assertEquals(
                5.08,
                result.getValue(),
                1e-2
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                3.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(
                3.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                3.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertEquals(
                9.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {

        QuantityMeasurementApp.QuantityLength result1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        QuantityMeasurementApp.QuantityLength result2 =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertTrue(result1.equals(result2));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        5.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                0.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(
                1.667,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        5.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                -2.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(
                36.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        null
                )
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        1000.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                500.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(
                18000.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(
                0.667,
                result.getValue(),
                EPSILON
        );
    }
}