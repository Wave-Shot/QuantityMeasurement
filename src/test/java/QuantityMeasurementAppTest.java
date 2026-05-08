import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testConversion_FeetToInches() {

        assertEquals(
                12.0,
                QuantityMeasurementApp.convert(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES
                ),
                EPSILON
        );
    }

    @Test
    public void testConversion_InchesToFeet() {

        assertEquals(
                2.0,
                QuantityMeasurementApp.convert(
                        24.0,
                        QuantityMeasurementApp.LengthUnit.INCHES,
                        QuantityMeasurementApp.LengthUnit.FEET
                ),
                EPSILON
        );
    }

    @Test
    public void testConversion_YardsToInches() {

        assertEquals(
                36.0,
                QuantityMeasurementApp.convert(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS,
                        QuantityMeasurementApp.LengthUnit.INCHES
                ),
                EPSILON
        );
    }

    @Test
    public void testConversion_InchesToYards() {

        assertEquals(
                2.0,
                QuantityMeasurementApp.convert(
                        72.0,
                        QuantityMeasurementApp.LengthUnit.INCHES,
                        QuantityMeasurementApp.LengthUnit.YARDS
                ),
                EPSILON
        );
    }

    @Test
    public void testConversion_CentimetersToInches() {

        assertEquals(
                1.0,
                QuantityMeasurementApp.convert(
                        2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                        QuantityMeasurementApp.LengthUnit.INCHES
                ),
                1e-3
        );
    }

    @Test
    public void testConversion_FeetToYard() {

        assertEquals(
                2.0,
                QuantityMeasurementApp.convert(
                        6.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.YARDS
                ),
                EPSILON
        );
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue() {

        double original = 5.0;

        double converted =
                QuantityMeasurementApp.convert(
                        original,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        double roundTrip =
                QuantityMeasurementApp.convert(
                        converted,
                        QuantityMeasurementApp.LengthUnit.INCHES,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertEquals(
                original,
                roundTrip,
                EPSILON
        );
    }

    @Test
    public void testConversion_ZeroValue() {

        assertEquals(
                0.0,
                QuantityMeasurementApp.convert(
                        0.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES
                ),
                EPSILON
        );
    }

    @Test
    public void testConversion_NegativeValue() {

        assertEquals(
                -12.0,
                QuantityMeasurementApp.convert(
                        -1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES
                ),
                EPSILON
        );
    }

    @Test
    public void testConversion_InvalidUnit_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(
                        1.0,
                        null,
                        QuantityMeasurementApp.LengthUnit.FEET
                )
        );
    }

    @Test
    public void testConversion_NaNOrInfinite_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(
                        Double.NaN,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES
                )
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(
                        Double.POSITIVE_INFINITY,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES
                )
        );
    }

    @Test
    public void testConversion_PrecisionTolerance() {

        double result =
                QuantityMeasurementApp.convert(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(
                0.393701,
                result,
                1e-6
        );
    }
}