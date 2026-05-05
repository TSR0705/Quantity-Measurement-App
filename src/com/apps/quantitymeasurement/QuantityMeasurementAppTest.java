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

    // ==================== UC5: Unit-to-Unit Conversion Tests ====================

    // TC24: 1 foot → 12 inches
    @Test
    public void testConvert_OneFoot_ToInches() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(12.0, result, 0.0001);
    }

    // TC25: 24 inches → 2 feet
    @Test
    public void testConvert_TwentyFourInches_ToFeet() {
        double result = QuantityMeasurementApp.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET);
        assertEquals(2.0, result, 0.0001);
    }

    // TC26: 1 yard → 36 inches
    @Test
    public void testConvert_OneYard_ToInches() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES);
        assertEquals(36.0, result, 0.0001);
    }

    // TC27: 6 feet → 2 yards
    @Test
    public void testConvert_SixFeet_ToYards() {
        double result = QuantityMeasurementApp.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS);
        assertEquals(2.0, result, 0.0001);
    }

    // TC28: 2.54 cm → 1 inch
    @Test
    public void testConvert_TwoPointFiveFourCm_ToInches() {
        double result = QuantityMeasurementApp.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        assertEquals(1.0, result, 0.0001);
    }

    // TC29: Round-trip — convert A→B→A should return original value
    @Test
    public void testConvert_RoundTrip_FeetToInchesAndBack() {
        double original = 5.0;
        double toInches = QuantityMeasurementApp.convert(original, LengthUnit.FEET, LengthUnit.INCHES);
        double backToFeet = QuantityMeasurementApp.convert(toInches, LengthUnit.INCHES, LengthUnit.FEET);
        assertEquals(original, backToFeet, 0.0001);
    }

    // TC30: Zero value always converts to zero
    @Test
    public void testConvert_ZeroValue_AlwaysZero() {
        double result = QuantityMeasurementApp.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(0.0, result, 0.0001);
    }

    // TC31: Negative value — -1 foot → -12 inches
    @Test
    public void testConvert_NegativeValue_OneFoot_ToInches() {
        double result = QuantityMeasurementApp.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(-12.0, result, 0.0001);
    }

    // TC32: Same unit — convert(5, FEET, FEET) == 5
    @Test
    public void testConvert_SameUnit_ReturnsSameValue() {
        double result = QuantityMeasurementApp.convert(5.0, LengthUnit.FEET, LengthUnit.FEET);
        assertEquals(5.0, result, 0.0001);
    }

    // TC33: Null fromUnit → IllegalArgumentException
    @Test
    public void testConvert_NullFromUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            QuantityMeasurementApp.convert(1.0, null, LengthUnit.INCHES));
    }

    // TC34: Null toUnit → IllegalArgumentException
    @Test
    public void testConvert_NullToUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, null));
    }

    // TC35: NaN value → IllegalArgumentException
    @Test
    public void testConvert_NaNValue_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            QuantityMeasurementApp.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES));
    }

    // TC36: Infinite value → IllegalArgumentException
    @Test
    public void testConvert_InfiniteValue_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES));
    }

    // ==================== UC6: Length Addition Tests ====================

    // TC37: Same unit — 1 ft + 2 ft = 3 ft
    @Test
    public void testAdd_SameUnit_FeetPlusFeet() {
        Length a      = new Length(1.0, LengthUnit.FEET);
        Length b      = new Length(2.0, LengthUnit.FEET);
        Length result = a.add(b);
        assertEquals(3.0, result.getValue(), 0.0001);
    }

    // TC38: Cross unit — 1 ft + 12 in = 2 ft (result in caller's unit: FEET)
    @Test
    public void testAdd_CrossUnit_FootPlusTwelveInches_ResultInFeet() {
        Length oneFoot   = new Length(1.0, LengthUnit.FEET);
        Length twelveIn  = new Length(12.0, LengthUnit.INCHES);
        Length result    = oneFoot.add(twelveIn);
        assertEquals(2.0, result.getValue(), 0.0001);
    }

    // TC39: Cross unit — 12 in + 1 ft = 24 in (result in caller's unit: INCHES)
    @Test
    public void testAdd_CrossUnit_TwelveInchesPlusFoot_ResultInInches() {
        Length twelveIn = new Length(12.0, LengthUnit.INCHES);
        Length oneFoot  = new Length(1.0, LengthUnit.FEET);
        Length result   = twelveIn.add(oneFoot);
        assertEquals(24.0, result.getValue(), 0.0001);
    }

    // TC40: Yard — 1 yd + 3 ft = 2 yd
    @Test
    public void testAdd_CrossUnit_OneYardPlusThreeFeet_ResultInYards() {
        Length oneYard   = new Length(1.0, LengthUnit.YARDS);
        Length threeFeet = new Length(3.0, LengthUnit.FEET);
        Length result    = oneYard.add(threeFeet);
        assertEquals(2.0, result.getValue(), 0.0001);
    }

    // TC41: CM — 2.54 cm + 1 in ≈ 5.08 cm
    @Test
    public void testAdd_CrossUnit_CmPlusInch_ResultInCm() {
        Length twoPtFiveFourCm = new Length(2.54, LengthUnit.CENTIMETERS);
        Length oneInch         = new Length(1.0, LengthUnit.INCHES);
        Length result          = twoPtFiveFourCm.add(oneInch);
        assertEquals(5.08, result.getValue(), 0.0001);
    }

    // TC42: Commutativity — A + B == B + A (compare in base unit via equals)
    @Test
    public void testAdd_Commutativity_FeetAndInches() {
        Length oneFoot  = new Length(1.0, LengthUnit.FEET);
        Length sixInch  = new Length(6.0, LengthUnit.INCHES);
        Length ab = oneFoot.add(sixInch);   // result in FEET
        Length ba = sixInch.add(oneFoot);   // result in INCHES
        assertTrue(ab.equals(ba));
    }

    // TC43: Zero — 5 ft + 0 in = 5 ft
    @Test
    public void testAdd_ZeroLength_NoChange() {
        Length fiveFeet = new Length(5.0, LengthUnit.FEET);
        Length zeroIn   = new Length(0.0, LengthUnit.INCHES);
        Length result   = fiveFeet.add(zeroIn);
        assertEquals(5.0, result.getValue(), 0.0001);
    }

    // TC44: Negative — 5 ft + (-2 ft) = 3 ft
    @Test
    public void testAdd_NegativeLength_Subtraction() {
        Length fiveFeet = new Length(5.0, LengthUnit.FEET);
        Length negTwo   = new Length(-2.0, LengthUnit.FEET);
        Length result   = fiveFeet.add(negTwo);
        assertEquals(3.0, result.getValue(), 0.0001);
    }

    // TC45: Null argument → IllegalArgumentException
    @Test
    public void testAdd_NullArgument_ThrowsException() {
        Length oneFoot = new Length(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> oneFoot.add(null));
    }
}
