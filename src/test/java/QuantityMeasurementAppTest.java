import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-4;

    @Test
    public void testEquality_KilogramToKilogram_SameValue() {

        QuantityMeasurementApp.QuantityWeight q1 =
                new QuantityMeasurementApp.QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        QuantityMeasurementApp.QuantityWeight q2 =
                new QuantityMeasurementApp.QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {

        QuantityMeasurementApp.QuantityWeight kg =
                new QuantityMeasurementApp.QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        QuantityMeasurementApp.QuantityWeight gram =
                new QuantityMeasurementApp.QuantityWeight(
                        1000.0,
                        WeightUnit.GRAM
                );

        assertTrue(kg.equals(gram));
    }

    @Test
    public void testEquality_KilogramToPound_EquivalentValue() {

        QuantityMeasurementApp.QuantityWeight kg =
                new QuantityMeasurementApp.QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        QuantityMeasurementApp.QuantityWeight pound =
                new QuantityMeasurementApp.QuantityWeight(
                        2.20462,
                        WeightUnit.POUND
                );

        assertTrue(kg.equals(pound));
    }

    @Test
    public void testEquality_WeightVsLength_Incompatible() {

        QuantityMeasurementApp.QuantityWeight weight =
                new QuantityMeasurementApp.QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        QuantityMeasurementApp.QuantityLength length =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        LengthUnit.FEET
                );

        assertFalse(weight.equals(length));
    }

    @Test
    public void testConversion_KilogramToGram() {

        QuantityMeasurementApp.QuantityWeight kg =
                new QuantityMeasurementApp.QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        QuantityMeasurementApp.QuantityWeight result =
                kg.convertTo(
                        WeightUnit.GRAM
                );

        assertEquals(
                1000.0,
                result.convertTo(
                        WeightUnit.GRAM
                ).convertTo(
                        WeightUnit.GRAM
                ).hashCode(),
                result.hashCode()
        );
    }

    @Test
    public void testConversion_PoundToKilogram() {

        QuantityMeasurementApp.QuantityWeight pound =
                new QuantityMeasurementApp.QuantityWeight(
                        2.20462,
                        WeightUnit.POUND
                );

        QuantityMeasurementApp.QuantityWeight result =
                pound.convertTo(
                        WeightUnit.KILOGRAM
                );

        assertEquals(
                1.0,
                result.convertTo(
                        WeightUnit.KILOGRAM
                ).convertTo(
                        WeightUnit.KILOGRAM
                ).hashCode(),
                result.hashCode()
        );
    }

    @Test
    public void testAddition_SameUnit() {

        QuantityMeasurementApp.QuantityWeight q1 =
                new QuantityMeasurementApp.QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        QuantityMeasurementApp.QuantityWeight q2 =
                new QuantityMeasurementApp.QuantityWeight(
                        2.0,
                        WeightUnit.KILOGRAM
                );

        QuantityMeasurementApp.QuantityWeight result =
                q1.add(q2);

        assertTrue(
                result.equals(
                        new QuantityMeasurementApp
                                .QuantityWeight(
                                3.0,
                                WeightUnit.KILOGRAM
                        )
                )
        );
    }

    @Test
    public void testAddition_CrossUnit() {

        QuantityMeasurementApp.QuantityWeight kg =
                new QuantityMeasurementApp.QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        QuantityMeasurementApp.QuantityWeight gram =
                new QuantityMeasurementApp.QuantityWeight(
                        1000.0,
                        WeightUnit.GRAM
                );

        QuantityMeasurementApp.QuantityWeight result =
                kg.add(gram);

        assertTrue(
                result.equals(
                        new QuantityMeasurementApp
                                .QuantityWeight(
                                2.0,
                                WeightUnit.KILOGRAM
                        )
                )
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit() {

        QuantityMeasurementApp.QuantityWeight kg =
                new QuantityMeasurementApp.QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        QuantityMeasurementApp.QuantityWeight gram =
                new QuantityMeasurementApp.QuantityWeight(
                        1000.0,
                        WeightUnit.GRAM
                );

        QuantityMeasurementApp.QuantityWeight result =
                kg.add(
                        gram,
                        WeightUnit.GRAM
                );

        assertTrue(
                result.equals(
                        new QuantityMeasurementApp
                                .QuantityWeight(
                                2000.0,
                                WeightUnit.GRAM
                        )
                )
        );
    }

    @Test
    public void testEquality_NullComparison() {

        QuantityMeasurementApp.QuantityWeight weight =
                new QuantityMeasurementApp.QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertFalse(weight.equals(null));
    }

    @Test
    public void testEquality_SameReference() {

        QuantityMeasurementApp.QuantityWeight weight =
                new QuantityMeasurementApp.QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertTrue(weight.equals(weight));
    }

    @Test
    public void testEquality_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityMeasurementApp
                        .QuantityWeight(
                        1.0,
                        null
                )
        );
    }

    @Test
    public void testConversion_RoundTrip() {

        QuantityMeasurementApp.QuantityWeight original =
                new QuantityMeasurementApp.QuantityWeight(
                        1.5,
                        WeightUnit.KILOGRAM
                );

        QuantityMeasurementApp.QuantityWeight converted =
                original.convertTo(
                        WeightUnit.GRAM
                );

        QuantityMeasurementApp.QuantityWeight roundTrip =
                converted.convertTo(
                        WeightUnit.KILOGRAM
                );

        assertTrue(original.equals(roundTrip));
    }

    @Test
    public void testAddition_NegativeValues() {

        QuantityMeasurementApp.QuantityWeight q1 =
                new QuantityMeasurementApp.QuantityWeight(
                        5.0,
                        WeightUnit.KILOGRAM
                );

        QuantityMeasurementApp.QuantityWeight q2 =
                new QuantityMeasurementApp.QuantityWeight(
                        -2000.0,
                        WeightUnit.GRAM
                );

        QuantityMeasurementApp.QuantityWeight result =
                q1.add(q2);

        assertTrue(
                result.equals(
                        new QuantityMeasurementApp
                                .QuantityWeight(
                                3.0,
                                WeightUnit.KILOGRAM
                        )
                )
        );
    }
}