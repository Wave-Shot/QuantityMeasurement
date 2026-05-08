import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;

    @Test
    public void testEquality_LitreToLitre_SameValue() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> q2 =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> ml =
                new Quantity<>(
                        1000.0,
                        VolumeUnit.MILLILITRE
                );

        assertTrue(litre.equals(ml));
    }

    @Test
    public void testEquality_GallonToLitre_EquivalentValue() {

        Quantity<VolumeUnit> gallon =
                new Quantity<>(
                        1.0,
                        VolumeUnit.GALLON
                );

        Quantity<VolumeUnit> litre =
                new Quantity<>(
                        3.78541,
                        VolumeUnit.LITRE
                );

        assertTrue(gallon.equals(litre));
    }

    @Test
    public void testConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> result =
                litre.convertTo(
                        VolumeUnit.MILLILITRE
                );

        assertEquals(
                1000.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testConversion_GallonToLitre() {

        Quantity<VolumeUnit> gallon =
                new Quantity<>(
                        1.0,
                        VolumeUnit.GALLON
                );

        Quantity<VolumeUnit> result =
                gallon.convertTo(
                        VolumeUnit.LITRE
                );

        assertEquals(
                3.79,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testConversion_MillilitreToGallon() {

        Quantity<VolumeUnit> ml =
                new Quantity<>(
                        1000.0,
                        VolumeUnit.MILLILITRE
                );

        Quantity<VolumeUnit> result =
                ml.convertTo(
                        VolumeUnit.GALLON
                );

        assertEquals(
                0.26,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_LitrePlusMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> ml =
                new Quantity<>(
                        1000.0,
                        VolumeUnit.MILLILITRE
                );

        Quantity<VolumeUnit> result =
                litre.add(
                        ml,
                        VolumeUnit.LITRE
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_GallonPlusLitre() {

        Quantity<VolumeUnit> gallon =
                new Quantity<>(
                        1.0,
                        VolumeUnit.GALLON
                );

        Quantity<VolumeUnit> litre =
                new Quantity<>(
                        3.78541,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> result =
                gallon.add(
                        litre,
                        VolumeUnit.GALLON
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testVolumeVsLength_Incompatible() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        assertFalse(litre.equals(feet));
    }

    @Test
    public void testVolumeVsWeight_Incompatible() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<WeightUnit> kg =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertFalse(litre.equals(kg));
    }

    @Test
    public void testEquality_SameReference() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        assertTrue(litre.equals(litre));
    }

    @Test
    public void testEquality_NullComparison() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        assertFalse(litre.equals(null));
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
                        VolumeUnit.LITRE
                )
        );
    }

    @Test
    public void testConversion_RoundTrip() {

        Quantity<VolumeUnit> original =
                new Quantity<>(
                        1.5,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> converted =
                original.convertTo(
                        VolumeUnit.MILLILITRE
                );

        Quantity<VolumeUnit> roundTrip =
                converted.convertTo(
                        VolumeUnit.LITRE
                );

        assertEquals(
                original.getValue(),
                roundTrip.getValue(),
                EPSILON
        );
    }
}