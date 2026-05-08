import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;

    @Test
    public void testAdd_UC12_BehaviorPreserved() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                ).add(
                        new Quantity<>(
                                12.0,
                                LengthUnit.INCHES
                        )
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testSubtract_UC12_BehaviorPreserved() {

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
    public void testDivide_UC12_BehaviorPreserved() {

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
    public void testValidation_NullOperand_ConsistentAcrossOperations() {

        Quantity<LengthUnit> quantity =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                );

        Exception addException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> quantity.add(null)
                );

        Exception subtractException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> quantity.subtract(null)
                );

        Exception divideException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> quantity.divide(null)
                );

        assertEquals(
                addException.getMessage(),
                subtractException.getMessage()
        );

        assertEquals(
                subtractException.getMessage(),
                divideException.getMessage()
        );
    }

    @Test
    public void testValidation_CrossCategory_ConsistentAcrossOperations() {

        Quantity<LengthUnit> length =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                );

        Quantity weightRaw =
                new Quantity<>(
                        5.0,
                        WeightUnit.KILOGRAM
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> length.add(weightRaw)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> length.subtract(weightRaw)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> length.divide(weightRaw)
        );
    }

    @Test
    public void testValidation_NullTargetUnit_AddSubtractReject() {

        Quantity<LengthUnit> quantity =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> other =
                new Quantity<>(
                        5.0,
                        LengthUnit.FEET
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> quantity.add(other, null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> quantity.subtract(other, null)
        );
    }

    @Test
    public void testRounding_AddSubtract_TwoDecimalPlaces() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        1.23456,
                        LengthUnit.FEET
                ).add(
                        new Quantity<>(
                                0.11111,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                1.35,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testRounding_Divide_NoRounding() {

        double result =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                ).divide(
                        new Quantity<>(
                                3.0,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                0.333333,
                result,
                1e-5
        );
    }

    @Test
    public void testImmutability_AfterAdd_ViaCentralizedHelper() {

        Quantity<LengthUnit> original =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        original.add(
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                )
        );

        assertEquals(
                1.0,
                original.getValue(),
                EPSILON
        );
    }

    @Test
    public void testImmutability_AfterSubtract_ViaCentralizedHelper() {

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
    public void testImmutability_AfterDivide_ViaCentralizedHelper() {

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

    @Test
    public void testAllOperations_AcrossAllCategories() {

        Quantity<WeightUnit> weightAdd =
                new Quantity<>(
                        10.0,
                        WeightUnit.KILOGRAM
                ).add(
                        new Quantity<>(
                                5000.0,
                                WeightUnit.GRAM
                        )
                );

        Quantity<VolumeUnit> volumeSubtract =
                new Quantity<>(
                        5.0,
                        VolumeUnit.LITRE
                ).subtract(
                        new Quantity<>(
                                500.0,
                                VolumeUnit.MILLILITRE
                        )
                );

        double lengthDivide =
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
                15.0,
                weightAdd.getValue(),
                EPSILON
        );

        assertEquals(
                4.5,
                volumeSubtract.getValue(),
                EPSILON
        );

        assertEquals(
                1.0,
                lengthDivide,
                EPSILON
        );
    }

    @Test
    public void testArithmetic_Chain_Operations() {

        double result =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).add(
                        new Quantity<>(
                                2.0,
                                LengthUnit.FEET
                        )
                ).subtract(
                        new Quantity<>(
                                1.0,
                                LengthUnit.FEET
                        )
                ).divide(
                        new Quantity<>(
                                11.0,
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
    public void testDivision_ByZero_EnumThrows() {

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
    public void testErrorMessage_Consistency_Across_Operations() {

        Quantity<LengthUnit> quantity =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                );

        String addMessage =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> quantity.add(null)
                ).getMessage();

        String subtractMessage =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> quantity.subtract(null)
                ).getMessage();

        String divideMessage =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> quantity.divide(null)
                ).getMessage();

        assertEquals(
                addMessage,
                subtractMessage
        );

        assertEquals(
                subtractMessage,
                divideMessage
        );
    }
}