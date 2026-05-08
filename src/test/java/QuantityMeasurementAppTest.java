import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testLengthUnitEnum_FeetConstant() {

        assertEquals(
                1.0,
                LengthUnit.FEET.getConversionFactor(),
                EPSILON
        );
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {

        assertEquals(
                1.0 / 12.0,
                LengthUnit.INCHES.getConversionFactor(),
                EPSILON
        );
    }

    @Test
    public void testLengthUnitEnum_YardsConstant() {

        assertEquals(
                3.0,
                LengthUnit.YARDS.getConversionFactor(),
                EPSILON
        );
    }

    @Test
    public void testLengthUnitEnum_CentimetersConstant() {

        assertEquals(
                1.0 / 30.48,
                LengthUnit.CENTIMETERS.getConversionFactor(),
                EPSILON
        );
    }

    @Test
    public void testConvertToBaseUnit_FeetToFeet() {

        assertEquals(
                5.0,
                LengthUnit.FEET
                        .convertToBaseUnit(5.0),
                EPSILON
        );
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {

        assertEquals(
                1.0,
                LengthUnit.INCHES
                        .convertToBaseUnit(12.0),
                EPSILON
        );
    }

    @Test
    public void testConvertToBaseUnit_YardsToFeet() {

        assertEquals(
                3.0,
                LengthUnit.YARDS
                        .convertToBaseUnit(1.0),
                EPSILON
        );
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToFeet() {

        assertEquals(
                1.0,
                LengthUnit.CENTIMETERS
                        .convertToBaseUnit(30.48),
                1e-3
        );
    }

    @Test
    public void testConvertFromBaseUnit_FeetToFeet() {

        assertEquals(
                2.0,
                LengthUnit.FEET
                        .convertFromBaseUnit(2.0),
                EPSILON
        );
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {

        assertEquals(
                12.0,
                LengthUnit.INCHES
                        .convertFromBaseUnit(1.0),
                EPSILON
        );
    }

    @Test
    public void testConvertFromBaseUnit_FeetToYards() {

        assertEquals(
                1.0,
                LengthUnit.YARDS
                        .convertFromBaseUnit(3.0),
                EPSILON
        );
    }

    @Test
    public void testConvertFromBaseUnit_FeetToCentimeters() {

        assertEquals(
                30.48,
                LengthUnit.CENTIMETERS
                        .convertFromBaseUnit(1.0),
                1e-3
        );
    }

    @Test
    public void testQuantityLengthRefactored_Equality() {

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                );

        assertTrue(feet.equals(inches));
    }

    @Test
    public void testQuantityLengthRefactored_ConvertTo() {

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength result =
                feet.convertTo(
                        LengthUnit.INCHES
                );

        assertEquals(
                12.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testQuantityLengthRefactored_Add() {

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                );

        QuantityMeasurementApp.QuantityLength result =
                feet.add(
                        inches,
                        LengthUnit.FEET
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit() {

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                );

        QuantityMeasurementApp.QuantityLength result =
                feet.add(
                        inches,
                        LengthUnit.YARDS
                );

        assertEquals(
                0.667,
                result.getValue(),
                1e-3
        );
    }

    @Test
    public void testQuantityLengthRefactored_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityMeasurementApp
                        .QuantityLength(
                        1.0,
                        null
                )
        );
    }

    @Test
    public void testQuantityLengthRefactored_InvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityMeasurementApp
                        .QuantityLength(
                        Double.NaN,
                        LengthUnit.FEET
                )
        );
    }

    @Test
    public void testRoundTripConversion_RefactoredDesign() {

        QuantityMeasurementApp.QuantityLength original =
                new QuantityMeasurementApp.QuantityLength(
                        5.0,
                        LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength converted =
                original.convertTo(
                        LengthUnit.INCHES
                );

        QuantityMeasurementApp.QuantityLength roundTrip =
                converted.convertTo(
                        LengthUnit.FEET
                );

        assertEquals(
                original.getValue(),
                roundTrip.getValue(),
                EPSILON
        );
    }

    @Test
    public void testUnitImmutability() {

        assertNotNull(LengthUnit.FEET);
        assertNotNull(LengthUnit.INCHES);
        assertNotNull(LengthUnit.YARDS);
        assertNotNull(LengthUnit.CENTIMETERS);
    }
}