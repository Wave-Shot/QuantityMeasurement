import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(
                        0.0,
                        TemperatureUnit.CELSIUS
                );

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(
                        32.0,
                        TemperatureUnit.FAHRENHEIT
                );

        assertTrue(
                celsius.equals(fahrenheit)
        );
    }

    @Test
    public void testTemperatureEquality_CelsiusToKelvin() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(
                        0.0,
                        TemperatureUnit.CELSIUS
                );

        Quantity<TemperatureUnit> kelvin =
                new Quantity<>(
                        273.15,
                        TemperatureUnit.KELVIN
                );

        assertTrue(
                celsius.equals(kelvin)
        );
    }

    @Test
    public void testTemperatureConversion_CelsiusToFahrenheit() {

        Quantity<TemperatureUnit> result =
                new Quantity<>(
                        100.0,
                        TemperatureUnit.CELSIUS
                ).convertTo(
                        TemperatureUnit.FAHRENHEIT
                );

        assertEquals(
                212.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testTemperatureConversion_FahrenheitToCelsius() {

        Quantity<TemperatureUnit> result =
                new Quantity<>(
                        32.0,
                        TemperatureUnit.FAHRENHEIT
                ).convertTo(
                        TemperatureUnit.CELSIUS
                );

        assertEquals(
                0.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testTemperatureConversion_CelsiusToKelvin() {

        Quantity<TemperatureUnit> result =
                new Quantity<>(
                        0.0,
                        TemperatureUnit.CELSIUS
                ).convertTo(
                        TemperatureUnit.KELVIN
                );

        assertEquals(
                273.15,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testTemperatureConversion_Negative40EqualPoint() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(
                        -40.0,
                        TemperatureUnit.CELSIUS
                );

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(
                        -40.0,
                        TemperatureUnit.FAHRENHEIT
                );

        assertTrue(
                celsius.equals(fahrenheit)
        );
    }

    @Test
    public void testTemperatureUnsupportedOperation_Add() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> new Quantity<>(
                        100.0,
                        TemperatureUnit.CELSIUS
                ).add(
                        new Quantity<>(
                                50.0,
                                TemperatureUnit.CELSIUS
                        )
                )
        );
    }

    @Test
    public void testTemperatureUnsupportedOperation_Subtract() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> new Quantity<>(
                        100.0,
                        TemperatureUnit.CELSIUS
                ).subtract(
                        new Quantity<>(
                                50.0,
                                TemperatureUnit.CELSIUS
                        )
                )
        );
    }

    @Test
    public void testTemperatureUnsupportedOperation_Divide() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> new Quantity<>(
                        100.0,
                        TemperatureUnit.CELSIUS
                ).divide(
                        new Quantity<>(
                                50.0,
                                TemperatureUnit.CELSIUS
                        )
                )
        );
    }

    @Test
    public void testTemperatureVsLengthIncompatibility() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(
                        100.0,
                        TemperatureUnit.CELSIUS
                );

        Quantity<LengthUnit> length =
                new Quantity<>(
                        100.0,
                        LengthUnit.FEET
                );

        assertFalse(
                temp.equals(length)
        );
    }

    @Test
    public void testTemperatureVsWeightIncompatibility() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(
                        50.0,
                        TemperatureUnit.CELSIUS
                );

        Quantity<WeightUnit> weight =
                new Quantity<>(
                        50.0,
                        WeightUnit.KILOGRAM
                );

        assertFalse(
                temp.equals(weight)
        );
    }

    @Test
    public void testTemperatureVsVolumeIncompatibility() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(
                        25.0,
                        TemperatureUnit.CELSIUS
                );

        Quantity<VolumeUnit> volume =
                new Quantity<>(
                        25.0,
                        VolumeUnit.LITRE
                );

        assertFalse(
                temp.equals(volume)
        );
    }

    @Test
    public void testOperationSupportMethods_TemperatureUnit() {

        assertFalse(
                TemperatureUnit.CELSIUS
                        .supportsArithmetic()
        );
    }

    @Test
    public void testOperationSupportMethods_LengthUnit() {

        assertTrue(
                LengthUnit.FEET
                        .supportsArithmetic()
        );
    }

    @Test
    public void testTemperatureValidateOperationSupport_MethodBehavior() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> TemperatureUnit.CELSIUS
                        .validateOperationSupport(
                                "addition"
                        )
        );
    }

    @Test
    public void testTemperatureIntegrationWithGenericQuantity() {

        Quantity<TemperatureUnit> quantity =
                new Quantity<>(
                        50.0,
                        TemperatureUnit.CELSIUS
                );

        assertEquals(
                50.0,
                quantity.getValue(),
                EPSILON
        );
    }
}