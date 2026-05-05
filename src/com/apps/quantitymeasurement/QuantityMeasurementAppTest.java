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

    // ==================== UC7: Addition with Explicit Target Unit ====================

    // TC46: 1 ft + 12 in → explicit FEET = 2 ft
    @Test
    public void testAdd_ExplicitTarget_FootPlusInches_InFeet() {
        Length oneFoot  = new Length(1.0, LengthUnit.FEET);
        Length twelveIn = new Length(12.0, LengthUnit.INCHES);
        Length result   = oneFoot.add(twelveIn, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), 0.0001);
    }

    // TC47: 1 ft + 12 in → explicit INCHES = 24 in
    @Test
    public void testAdd_ExplicitTarget_FootPlusInches_InInches() {
        Length oneFoot  = new Length(1.0, LengthUnit.FEET);
        Length twelveIn = new Length(12.0, LengthUnit.INCHES);
        Length result   = oneFoot.add(twelveIn, LengthUnit.INCHES);
        assertEquals(24.0, result.getValue(), 0.0001);
    }

    // TC48: 1 ft + 12 in → explicit YARDS ≈ 0.6667 yd
    @Test
    public void testAdd_ExplicitTarget_FootPlusInches_InYards() {
        Length oneFoot  = new Length(1.0, LengthUnit.FEET);
        Length twelveIn = new Length(12.0, LengthUnit.INCHES);
        Length result   = oneFoot.add(twelveIn, LengthUnit.YARDS);
        assertEquals(2.0 / 3.0, result.getValue(), 0.0001);
    }

    // TC49: 1 in + 1 in → explicit CENTIMETERS ≈ 5.08 cm
    @Test
    public void testAdd_ExplicitTarget_InchPlusInch_InCentimeters() {
        Length oneInch1 = new Length(1.0, LengthUnit.INCHES);
        Length oneInch2 = new Length(1.0, LengthUnit.INCHES);
        Length result   = oneInch1.add(oneInch2, LengthUnit.CENTIMETERS);
        assertEquals(5.08, result.getValue(), 0.0001);
    }

    // TC50: Commutativity — A+B == B+A with same explicit target unit
    @Test
    public void testAdd_ExplicitTarget_Commutativity() {
        Length oneFoot  = new Length(1.0, LengthUnit.FEET);
        Length sixInch  = new Length(6.0, LengthUnit.INCHES);
        Length ab = oneFoot.add(sixInch, LengthUnit.INCHES);
        Length ba = sixInch.add(oneFoot, LengthUnit.INCHES);
        assertEquals(ab.getValue(), ba.getValue(), 0.0001);
    }

    // TC51: Zero — 5 ft + 0 in → in YARDS
    @Test
    public void testAdd_ExplicitTarget_ZeroLength_InYards() {
        Length fiveFeet = new Length(5.0, LengthUnit.FEET);
        Length zeroIn   = new Length(0.0, LengthUnit.INCHES);
        Length result   = fiveFeet.add(zeroIn, LengthUnit.YARDS);
        // 5 ft = 60 in = 60/36 yd ≈ 1.6667 yd
        assertEquals(60.0 / 36.0, result.getValue(), 0.0001);
    }

    // TC52: Negative — 5 ft + (-2 ft) → in INCHES = 36 in
    @Test
    public void testAdd_ExplicitTarget_NegativeLength_InInches() {
        Length fiveFeet = new Length(5.0, LengthUnit.FEET);
        Length negTwo   = new Length(-2.0, LengthUnit.FEET);
        Length result   = fiveFeet.add(negTwo, LengthUnit.INCHES);
        assertEquals(36.0, result.getValue(), 0.0001);
    }

    // TC53: Null targetUnit → IllegalArgumentException
    @Test
    public void testAdd_ExplicitTarget_NullTargetUnit_ThrowsException() {
        Length oneFoot  = new Length(1.0, LengthUnit.FEET);
        Length twelveIn = new Length(12.0, LengthUnit.INCHES);
        assertThrows(IllegalArgumentException.class, () ->
            oneFoot.add(twelveIn, null));
    }

    // TC54: Null thatLength with explicit target → IllegalArgumentException
    @Test
    public void testAdd_ExplicitTarget_NullLength_ThrowsException() {
        Length oneFoot = new Length(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () ->
            oneFoot.add(null, LengthUnit.INCHES));
    }

    // TC55: Large values — 1000 ft + 500 ft → in INCHES = 18000 in
    @Test
    public void testAdd_ExplicitTarget_LargeValues_InInches() {
        Length thousandFt  = new Length(1000.0, LengthUnit.FEET);
        Length fiveHundFt  = new Length(500.0, LengthUnit.FEET);
        Length result      = thousandFt.add(fiveHundFt, LengthUnit.INCHES);
        assertEquals(18000.0, result.getValue(), 0.0001);
    }

    // TC56: Small values — precision check with epsilon
    @Test
    public void testAdd_ExplicitTarget_SmallValues_Precision() {
        Length pt1Inch = new Length(0.1, LengthUnit.INCHES);
        Length pt2Inch = new Length(0.2, LengthUnit.INCHES);
        Length result  = pt1Inch.add(pt2Inch, LengthUnit.INCHES);
        assertEquals(0.3, result.getValue(), 0.0001);
    }

    // ==================== UC8: LengthUnit Conversion Method Tests ====================

    // TC57: FEET.convertToBaseUnit(5) → 5.0 (feet is base unit)
    @Test
    public void testLengthUnit_Feet_ConvertToBaseUnit() {
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), 0.0001);
    }

    // TC58: INCHES.convertToBaseUnit(12) → 1.0 foot
    @Test
    public void testLengthUnit_Inches_ConvertToBaseUnit_TwelveInches_IsOneFoot() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), 0.0001);
    }

    // TC59: YARDS.convertToBaseUnit(1) → 3.0 feet
    @Test
    public void testLengthUnit_Yards_ConvertToBaseUnit_OneYard_IsThreeFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), 0.0001);
    }

    // TC60: CENTIMETERS.convertToBaseUnit(30.48) → 1.0 foot
    @Test
    public void testLengthUnit_Centimeters_ConvertToBaseUnit_ThirtyPointFourEight_IsOneFoot() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), 0.0001);
    }

    // TC61: FEET.convertFromBaseUnit(3) → 3.0 feet
    @Test
    public void testLengthUnit_Feet_ConvertFromBaseUnit() {
        assertEquals(3.0, LengthUnit.FEET.convertFromBaseUnit(3.0), 0.0001);
    }

    // TC62: INCHES.convertFromBaseUnit(1) → 12.0 inches
    @Test
    public void testLengthUnit_Inches_ConvertFromBaseUnit_OneFoot_IsTwelveInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), 0.0001);
    }

    // TC63: YARDS.convertFromBaseUnit(3) → 1.0 yard
    @Test
    public void testLengthUnit_Yards_ConvertFromBaseUnit_ThreeFeet_IsOneYard() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), 0.0001);
    }

    // TC64: CENTIMETERS.convertFromBaseUnit(1) → 30.48 cm
    @Test
    public void testLengthUnit_Centimeters_ConvertFromBaseUnit_OneFoot_IsThirtyPointFourEight() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), 0.0001);
    }

    // TC65: NaN → IllegalArgumentException from convertToBaseUnit
    @Test
    public void testLengthUnit_ConvertToBaseUnit_NaN_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            LengthUnit.FEET.convertToBaseUnit(Double.NaN));
    }

    // TC66: Infinity → IllegalArgumentException from convertFromBaseUnit
    @Test
    public void testLengthUnit_ConvertFromBaseUnit_Infinity_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            LengthUnit.INCHES.convertFromBaseUnit(Double.POSITIVE_INFINITY));
    }

    // TC67: Cross-unit equality still works after UC8 refactor
    @Test
    public void testUC8_BackwardCompat_CrossUnitEquality() {
        Length oneFoot   = new Length(1.0, LengthUnit.FEET);
        Length twelveIn  = new Length(12.0, LengthUnit.INCHES);
        assertTrue(oneFoot.equals(twelveIn));
    }

    // TC68: Addition still works after UC8 refactor
    @Test
    public void testUC8_BackwardCompat_Addition_WithExplicitTarget() {
        Length oneFoot  = new Length(1.0, LengthUnit.FEET);
        Length twelveIn = new Length(12.0, LengthUnit.INCHES);
        Length result   = oneFoot.add(twelveIn, LengthUnit.INCHES);
        assertEquals(24.0, result.getValue(), 0.0001);
    }

    // ==================== UC9: Weight Measurement Tests ====================

    // --- Equality ---

    // TC69: Same weight same unit → equal
    @Test
    public void testWeightEquality_SameValue_SameUnit() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);
        assertTrue(w1.equals(w2));
    }

    // TC70: Different values same unit → not equal
    @Test
    public void testWeightEquality_DifferentValue_SameUnit() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);
        assertFalse(w1.equals(w2));
    }

    // TC71: 1 kg == 1000 g
    @Test
    public void testWeightEquality_OneKilogram_EqualTo_ThousandGrams() {
        Weight oneKg      = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight thousandG  = new Weight(1000.0, WeightUnit.GRAM);
        assertTrue(oneKg.equals(thousandG));
    }

    // TC72: 1 pound == 453.592 g
    @Test
    public void testWeightEquality_OnePound_EqualTo_FourFiftyThreePointFiveNineTwo_Grams() {
        Weight onePound = new Weight(1.0, WeightUnit.POUND);
        Weight grams    = new Weight(453.592, WeightUnit.GRAM);
        assertTrue(onePound.equals(grams));
    }

    // TC73: 1 tonne == 1,000,000 g
    @Test
    public void testWeightEquality_OneTonne_EqualTo_OneMillion_Grams() {
        Weight oneTonne  = new Weight(1.0, WeightUnit.TONNE);
        Weight millionG  = new Weight(1_000_000.0, WeightUnit.GRAM);
        assertTrue(oneTonne.equals(millionG));
    }

    // TC74: kg != pound (inequality)
    @Test
    public void testWeightInequality_Kilogram_NotEqualTo_Pound() {
        Weight oneKg    = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight onePound = new Weight(1.0, WeightUnit.POUND);
        assertFalse(oneKg.equals(onePound));
    }

    // TC75: null comparison → false
    @Test
    public void testWeightEquality_NullComparison() {
        Weight w = new Weight(1.0, WeightUnit.GRAM);
        assertFalse(w.equals(null));
    }

    // TC76: same reference → true
    @Test
    public void testWeightEquality_SameReference() {
        Weight w = new Weight(1.0, WeightUnit.KILOGRAM);
        assertTrue(w.equals(w));
    }

    // TC77: different class → false
    @Test
    public void testWeightEquality_DifferentClass() {
        Weight w = new Weight(1.0, WeightUnit.GRAM);
        assertFalse(w.equals("not a weight"));
    }

    // --- Addition ---

    // TC78: Same unit addition — 200 g + 300 g = 500 g
    @Test
    public void testWeightAddition_SameUnit() {
        Weight w1     = new Weight(200.0, WeightUnit.GRAM);
        Weight w2     = new Weight(300.0, WeightUnit.GRAM);
        Weight result = w1.add(w2);
        assertEquals(500.0, result.getValue(), 0.01);
    }

    // TC79: Cross unit — 1 kg + 500 g = 1.5 kg
    @Test
    public void testWeightAddition_CrossUnit_KgPlusGrams_ResultInKg() {
        Weight oneKg    = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight fiveHundG = new Weight(500.0, WeightUnit.GRAM);
        Weight result   = oneKg.add(fiveHundG);
        assertEquals(1.5, result.getValue(), 0.01);
    }

    // TC80: Explicit target — 1 kg + 500 g → in GRAMS = 1500 g
    @Test
    public void testWeightAddition_ExplicitTarget_KgPlusGrams_InGrams() {
        Weight oneKg     = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight fiveHundG = new Weight(500.0, WeightUnit.GRAM);
        Weight result    = oneKg.add(fiveHundG, WeightUnit.GRAM);
        assertEquals(1500.0, result.getValue(), 0.01);
    }

    // TC81: Null addition → IllegalArgumentException
    @Test
    public void testWeightAddition_NullArgument_ThrowsException() {
        Weight w = new Weight(1.0, WeightUnit.GRAM);
        assertThrows(IllegalArgumentException.class, () -> w.add(null));
    }

    // TC82: Null target unit → IllegalArgumentException
    @Test
    public void testWeightAddition_NullTargetUnit_ThrowsException() {
        Weight w1 = new Weight(1.0, WeightUnit.GRAM);
        Weight w2 = new Weight(1.0, WeightUnit.GRAM);
        assertThrows(IllegalArgumentException.class, () -> w1.add(w2, null));
    }

    // --- WeightUnit conversion methods ---

    // TC83: KILOGRAM.convertToBaseUnit(1) → 1000 g
    @Test
    public void testWeightUnit_Kilogram_ConvertToBaseUnit() {
        assertEquals(1000.0, WeightUnit.KILOGRAM.convertToBaseUnit(1.0), 0.01);
    }

    // TC84: GRAM.convertToBaseUnit(1) → 1 g
    @Test
    public void testWeightUnit_Gram_ConvertToBaseUnit() {
        assertEquals(1.0, WeightUnit.GRAM.convertToBaseUnit(1.0), 0.01);
    }

    // TC85: MILLIGRAM.convertToBaseUnit(1000) → 1 g
    @Test
    public void testWeightUnit_Milligram_ConvertToBaseUnit_ThousandMg_IsOneGram() {
        assertEquals(1.0, WeightUnit.MILLIGRAM.convertToBaseUnit(1000.0), 0.01);
    }

    // TC86: POUND.convertToBaseUnit(1) → 453.592 g
    @Test
    public void testWeightUnit_Pound_ConvertToBaseUnit() {
        assertEquals(453.592, WeightUnit.POUND.convertToBaseUnit(1.0), 0.01);
    }

    // TC87: TONNE.convertToBaseUnit(1) → 1,000,000 g
    @Test
    public void testWeightUnit_Tonne_ConvertToBaseUnit() {
        assertEquals(1_000_000.0, WeightUnit.TONNE.convertToBaseUnit(1.0), 0.01);
    }

    // TC88: KILOGRAM.convertFromBaseUnit(1000) → 1 kg
    @Test
    public void testWeightUnit_Kilogram_ConvertFromBaseUnit() {
        assertEquals(1.0, WeightUnit.KILOGRAM.convertFromBaseUnit(1000.0), 0.01);
    }

    // TC89: NaN → IllegalArgumentException
    @Test
    public void testWeightUnit_ConvertToBaseUnit_NaN_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            WeightUnit.GRAM.convertToBaseUnit(Double.NaN));
    }

    // TC90: Infinity → IllegalArgumentException
    @Test
    public void testWeightUnit_ConvertFromBaseUnit_Infinity_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            WeightUnit.KILOGRAM.convertFromBaseUnit(Double.POSITIVE_INFINITY));
    }
}
