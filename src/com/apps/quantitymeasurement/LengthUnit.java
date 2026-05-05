package com.apps.quantitymeasurement;

/**
 * UC8: LengthUnit owns ALL conversion logic.
 * Base unit is FEET (conversionFactor = 1.0).
 * Every other unit's conversionFactor expresses how many feet one unit equals.
 *
 * Single Responsibility: this enum is the single source of truth for
 * unit-to-base and base-to-unit conversion. Length contains NO math.
 */
public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Converts a value in THIS unit to the base unit (feet).
     *
     * @param value the quantity in this unit
     * @return equivalent quantity in feet
     * @throws IllegalArgumentException if value is not finite
     */
    public double convertToBaseUnit(double value) {
        validate(value);
        return value * conversionFactor;
    }

    /**
     * Converts a value in the base unit (feet) to THIS unit.
     *
     * @param baseValue the quantity in feet
     * @return equivalent quantity in this unit
     * @throws IllegalArgumentException if baseValue is not finite
     */
    public double convertFromBaseUnit(double baseValue) {
        validate(baseValue);
        return baseValue / conversionFactor;
    }

    private void validate(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                "Value must be finite, got: " + value);
        }
    }
}
