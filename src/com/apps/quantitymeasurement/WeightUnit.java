package com.apps.quantitymeasurement;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * UC9: WeightUnit owns ALL weight conversion logic.
 *
 * <p><b>Base unit:</b> GRAM (conversionFactor = 1.0).
 * Every unit's {@code conversionFactor} expresses how many grams one unit equals.
 *
 * <p><b>Conversion pipeline:</b>
 * <pre>
 *   value  ──convertToBaseUnit()──▶  grams
 *   grams  ──convertFromBaseUnit()──▶  target value
 * </pre>
 *
 * <p><b>Rounding:</b> all results are rounded to 2 decimal places using
 * {@link RoundingMode#HALF_UP} to ensure consistent, predictable output.
 *
 * <p><b>Single Responsibility:</b> this enum is the single source of truth
 * for unit-to-base and base-to-unit conversion. {@link Weight} contains NO math.
 */
public enum WeightUnit implements IMeasurable {

    /** 1 milligram = 0.001 grams */
    MILLIGRAM(0.001),

    /** 1 gram = 1 gram (base unit) */
    GRAM(1.0),

    /** 1 kilogram = 1000 grams */
    KILOGRAM(1000.0),

    /** 1 pound = 453.592 grams */
    POUND(453.592),

    /** 1 tonne = 1,000,000 grams */
    TONNE(1_000_000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    /**
     * Returns the number of grams that one unit of this type equals.
     *
     * @return conversion factor relative to GRAM (base unit)
     */
    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Converts a value in THIS unit to the base unit (grams).
     *
     * @param value the quantity in this unit
     * @return equivalent quantity in grams, rounded to 2 decimal places
     * @throws IllegalArgumentException if value is not finite
     */
    public double convertToBaseUnit(double value) {
        validate(value);
        return round(value * conversionFactor);
    }

    /**
     * Converts a value in the base unit (grams) to THIS unit.
     *
     * @param baseValue the quantity in grams
     * @return equivalent quantity in this unit, rounded to 2 decimal places
     * @throws IllegalArgumentException if baseValue is not finite
     */
    public double convertFromBaseUnit(double baseValue) {
        validate(baseValue);
        return round(baseValue / conversionFactor);
    }

    /**
     * Rounds a double to 2 decimal places using HALF_UP rounding.
     */
    private double round(double value) {
        return BigDecimal.valueOf(value)
                         .setScale(2, RoundingMode.HALF_UP)
                         .doubleValue();
    }

    private void validate(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                "Value must be finite, got: " + value);
        }
    }

    @Override
    public String getUnitName() {
        return name();
    }
}
