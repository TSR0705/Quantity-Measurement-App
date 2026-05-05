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

    // ==================== UC4: Yards and Centimeters Tests ====================

    // TC14: Same yard value should be equal
    @Test
    public void testYardEquality_SameValue() {
        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        Length yard2 = new Length(1.0, LengthUnit.YARDS);
        assertTrue(yard1.equals(yard2));
    }

    // TC15: Different yard values should not be equal
    @Test
    public void testYardEquality_DifferentValue() {
        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        Length yard2 = new Length(2.0, LengthUnit.YARDS);
        assertFalse(yard1.equals(yard2));
    }

    // TC16: 1 yard == 3 feet (36 inches == 36 inches)
    @Test
    public void testYardConversion_OneYard_EqualTo_ThreeFeet() {
        Length oneYard   = new Length(1.0, LengthUnit.YARDS);
        Length threeFeet = new Length(3.0, LengthUnit.FEET);
        assertTrue(oneYard.equals(threeFeet));
    }

    // TC17: 1 yard == 36 inches
    @Test
    public void testYardConversion_OneYard_EqualTo_ThirtySixInches() {
        Length oneYard      = new Length(1.0, LengthUnit.YARDS);
        Length thirtySixIn  = new Length(36.0, LengthUnit.INCHES);
        assertTrue(oneYard.equals(thirtySixIn));
    }

    // TC18: Same centimeter value should be equal
    @Test
    public void testCentimeterEquality_SameValue() {
        Length cm1 = new Length(1.0, LengthUnit.CENTIMETERS);
        Length cm2 = new Length(1.0, LengthUnit.CENTIMETERS);
        assertTrue(cm1.equals(cm2));
    }

    // TC19: 1 cm == 0.393701 inches
    @Test
    public void testCentimeterConversion_OneCm_EqualTo_PointThreeNineInches() {
        Length oneCm        = new Length(1.0, LengthUnit.CENTIMETERS);
        Length equivalentIn = new Length(0.393701, LengthUnit.INCHES);
        assertTrue(oneCm.equals(equivalentIn));
    }

    // TC20: 1 cm != 1 foot (cross-unit inequality)
    @Test
    public void testCentimeterInequality_OneCm_NotEqualTo_OneFoot() {
        Length oneCm   = new Length(1.0, LengthUnit.CENTIMETERS);
        Length oneFoot = new Length(1.0, LengthUnit.FEET);
        assertFalse(oneCm.equals(oneFoot));
    }

    // TC21: Transitive — 1 yd == 3 ft AND 3 ft == 36 in → 1 yd == 36 in
    @Test
    public void testTransitiveProperty_OneYard_ThreeFeet_ThirtySixInches() {
        Length oneYard      = new Length(1.0, LengthUnit.YARDS);
        Length threeFeet    = new Length(3.0, LengthUnit.FEET);
        Length thirtySixIn  = new Length(36.0, LengthUnit.INCHES);
        assertTrue(oneYard.equals(threeFeet));
        assertTrue(threeFeet.equals(thirtySixIn));
        assertTrue(oneYard.equals(thirtySixIn));
    }

    // TC22: Yards null comparison should return false
    @Test
    public void testYardEquality_NullComparison() {
        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        assertFalse(yard1.equals(null));
    }

    // TC23: Same yard reference should be equal
    @Test
    public void testYardEquality_SameReference() {
        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        assertTrue(yard1.equals(yard1));
    }
}
