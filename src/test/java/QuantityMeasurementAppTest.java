import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testGenericQuantity_LengthEquality() {

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> inches =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                );

        assertTrue(feet.equals(inches));
    }

    @Test
    public void testGenericQuantity_WeightEquality() {

        Quantity<WeightUnit> kg =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Quantity<WeightUnit> gram =
                new Quantity<>(
                        1000.0,
                        WeightUnit.GRAM
                );

        assertTrue(kg.equals(gram));
    }

    @Test
    public void testGenericQuantity_LengthConversion() {

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> result =
                feet.convertTo(
                        LengthUnit.INCHES
                );

        assertEquals(
                12.0,
                result.getValue()
        );
    }

    @Test
    public void testGenericQuantity_WeightConversion() {

        Quantity<WeightUnit> kg =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Quantity<WeightUnit> result =
                kg.convertTo(
                        WeightUnit.GRAM
                );

        assertEquals(
                1000.0,
                result.getValue()
        );
    }

    @Test
    public void testGenericQuantity_LengthAddition() {

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> inches =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                );

        Quantity<LengthUnit> result =
                feet.add(
                        inches,
                        LengthUnit.FEET
                );

        assertEquals(
                2.0,
                result.getValue()
        );
    }

    @Test
    public void testGenericQuantity_WeightAddition() {

        Quantity<WeightUnit> kg =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Quantity<WeightUnit> gram =
                new Quantity<>(
                        1000.0,
                        WeightUnit.GRAM
                );

        Quantity<WeightUnit> result =
                kg.add(
                        gram,
                        WeightUnit.KILOGRAM
                );

        assertEquals(
                2.0,
                result.getValue()
        );
    }

    @Test
    public void testCrossCategoryPrevention() {

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<WeightUnit> kg =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertFalse(feet.equals(kg));
    }

    @Test
    public void testConstructorValidation_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(
                        1.0,
                        null
                )
        );
    }

    @Test
    public void testConstructorValidation_InvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(
                        Double.NaN,
                        LengthUnit.FEET
                )
        );
    }

    @Test
    public void testHashCodeConsistency() {

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> inches =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                );

        assertEquals(
                feet.hashCode(),
                inches.hashCode()
        );
    }
}