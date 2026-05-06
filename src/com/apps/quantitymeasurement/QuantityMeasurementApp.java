package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    /**
     * UC5: Converts a value from one LengthUnit to another.
     *
     * Strategy: normalise to base unit (inches) via fromUnit.conversionFactor,
     * then scale to target unit via toUnit.conversionFactor.
     * All conversion logic is delegated to the enum — no hardcoded formulas.
     *
     * @param value    the numeric quantity to convert
     * @param fromUnit the source unit (must not be null)
     * @param toUnit   the target unit (must not be null)
     * @return the converted value in toUnit
     * @throws IllegalArgumentException if value is not finite, or either unit is null
     */
    public static double convert(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite: " + value);
        }
        if (fromUnit == null) {
            throw new IllegalArgumentException("fromUnit must not be null");
        }
        if (toUnit == null) {
            throw new IllegalArgumentException("toUnit must not be null");
        }

        // Delegate entirely to LengthUnit — no math in this class
        double baseValue = fromUnit.convertToBaseUnit(value);
        return toUnit.convertFromBaseUnit(baseValue);
    }

    /**
     * UC6: Demonstrates addition of two Length objects.
     * Delegates entirely to Length.add() — no conversion logic here.
     *
     * @param length1 the base length
     * @param length2 the length to add
     * @return a new Length representing the sum, in length1's unit
     */
    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        return length1.add(length2);
    }

    /**
     * UC7: Demonstrates addition of two Length objects with explicit target unit.
     * Delegates entirely to Length.add(Length, LengthUnit) — no conversion logic here.
     *
     * @param length1    the base length
     * @param length2    the length to add
     * @param targetUnit the desired unit for the result
     * @return a new Length representing the sum, in targetUnit
     */
    public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit) {
        return length1.add(length2, targetUnit);
    }

    /**
     * UC9: Converts a weight value from one WeightUnit to another.
     * Delegates entirely to WeightUnit — no math here.
     *
     * @param value    the numeric quantity to convert
     * @param fromUnit the source unit (must not be null)
     * @param toUnit   the target unit (must not be null)
     * @return the converted value in toUnit, rounded to 2 decimal places
     * @throws IllegalArgumentException if value is not finite, or either unit is null
     */
    public static double convertWeight(double value, WeightUnit fromUnit, WeightUnit toUnit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite: " + value);
        }
        if (fromUnit == null) {
            throw new IllegalArgumentException("fromUnit must not be null");
        }
        if (toUnit == null) {
            throw new IllegalArgumentException("toUnit must not be null");
        }
        double baseValue = fromUnit.convertToBaseUnit(value);
        return toUnit.convertFromBaseUnit(baseValue);
    }

    /**
     * UC9: Demonstrates addition of two Weight objects.
     * Delegates entirely to Weight.add() — no conversion logic here.
     *
     * @param weight1 the base weight
     * @param weight2 the weight to add
     * @return a new Weight representing the sum, in weight1's unit
     */
    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2) {
        return weight1.add(weight2);
    }

    /**
     * UC9: Demonstrates addition of two Weight objects with explicit target unit.
     * Delegates entirely to Weight.add(Weight, WeightUnit) — no conversion logic here.
     *
     * @param weight1    the base weight
     * @param weight2    the weight to add
     * @param targetUnit the desired unit for the result
     * @return a new Weight representing the sum, in targetUnit
     */
    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2, WeightUnit targetUnit) {
        return weight1.add(weight2, targetUnit);
    }

    /**
     * UC10: Generic conversion using Quantity<U>.
     * Delegates entirely to Quantity.convertTo() — no math here.
     *
     * @param quantity   the source quantity
     * @param targetUnit the desired unit
     * @param <U>        the unit type
     * @return a new Quantity in targetUnit
     */
    public static <U extends IMeasurable> Quantity<U> convert(Quantity<U> quantity, U targetUnit) {
        return quantity.convertTo(targetUnit);
    }

    /**
     * UC10: Generic addition using Quantity<U>.
     * Delegates entirely to Quantity.add() — no math here.
     *
     * @param q1  the first quantity
     * @param q2  the second quantity
     * @param <U> the unit type
     * @return a new Quantity in q1's unit representing the sum
     */
    public static <U extends IMeasurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2) {
        return q1.add(q2);
    }

    /**
     * UC10: Generic addition with explicit target unit using Quantity<U>.
     * Delegates entirely to Quantity.add(Quantity, U) — no math here.
     *
     * @param q1         the first quantity
     * @param q2         the second quantity
     * @param targetUnit the desired unit for the result
     * @param <U>        the unit type
     * @return a new Quantity in targetUnit representing the sum
     */
    public static <U extends IMeasurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        return q1.add(q2, targetUnit);
    }
}
