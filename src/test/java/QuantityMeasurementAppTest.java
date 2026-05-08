import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testEquality_SameValue() {

        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void testEquality_DifferentValue() {

        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(feet1.equals(feet2));
    }

    @Test
    public void testEquality_NullComparison() {

        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(feet1.equals(null));
    }

    @Test
    public void testEquality_NonNumericInput() {

        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(feet1.equals("abc"));
    }

    @Test
    public void testEquality_SameReference() {

        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(feet1.equals(feet1));
    }
}