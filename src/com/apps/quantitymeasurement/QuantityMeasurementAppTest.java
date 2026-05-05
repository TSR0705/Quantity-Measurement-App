package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ==================== UC1: Feet Equality Tests ====================

    // TC1: Same feet value should be equal
    @Test
    public void testFeetEquality_SameValue() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(1.0, LengthUnit.FEET);
        assertTrue(feet1.equals(feet2));
    }

    // TC2: Different feet values should not be equal
    @Test
    public void testFeetEquality_DifferentValue() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(2.0, LengthUnit.FEET);
        assertFalse(feet1.equals(feet2));
    }

    // TC3: Feet comparison with null should return false
    @Test
    public void testFeetEquality_NullComparison() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        assertFalse(feet1.equals(null));
    }

    // TC4: Feet comparison with different class should return false
    @Test
    public void testFeetEquality_DifferentClass() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        assertFalse(feet1.equals("not a length object"));
    }

    // TC5: Same feet reference should be equal
    @Test
    public void testFeetEquality_SameReference() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        assertTrue(feet1.equals(feet1));
    }

    // ==================== UC2: Inches Equality Tests ====================

    // TC6: Same inch value should be equal
    @Test
    public void testInchesEquality_SameValue() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(1.0, LengthUnit.INCHES);
        assertTrue(inches1.equals(inches2));
    }

    // TC7: Different inch values should not be equal
    @Test
    public void testInchesEquality_DifferentValue() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(2.0, LengthUnit.INCHES);
        assertFalse(inches1.equals(inches2));
    }

    // TC8: Inches comparison with null should return false
    @Test
    public void testInchesEquality_NullComparison() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        assertFalse(inches1.equals(null));
    }

    // TC9: Inches comparison with different class should return false
    @Test
    public void testInchesEquality_DifferentClass() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        assertFalse(inches1.equals("not a length object"));
    }

    // TC10: Same inches reference should be equal
    @Test
    public void testInchesEquality_SameReference() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        assertTrue(inches1.equals(inches1));
    }

    // ==================== UC3: Cross-Unit Equality Tests ====================

    // TC11: 1 foot == 12 inches
    @Test
    public void testLengthEquality_OneFoot_EqualTo_TwelveInches() {
        Length oneFoot   = new Length(1.0, LengthUnit.FEET);
        Length twelveIn  = new Length(12.0, LengthUnit.INCHES);
        assertTrue(oneFoot.equals(twelveIn));
    }

    // TC12: 12 inches == 1 foot (symmetric)
    @Test
    public void testLengthEquality_TwelveInches_EqualTo_OneFoot() {
        Length twelveIn = new Length(12.0, LengthUnit.INCHES);
        Length oneFoot  = new Length(1.0, LengthUnit.FEET);
        assertTrue(twelveIn.equals(oneFoot));
    }

    // TC13: 1 foot != 1 inch
    @Test
    public void testLengthInequality_OneFoot_NotEqualTo_OneInch() {
        Length oneFoot = new Length(1.0, LengthUnit.FEET);
        Length oneInch = new Length(1.0, LengthUnit.INCHES);
        assertFalse(oneFoot.equals(oneInch));
    }
}
