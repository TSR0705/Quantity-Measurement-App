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

        // Step 1: convert to base unit (inches)
        double baseValue = value * fromUnit.getConversionFactor();

        // Step 2: convert from base unit to target unit
        return baseValue / toUnit.getConversionFactor();
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
}
