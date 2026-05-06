package com.apps.quantitymeasurement;

/**
 * UC11: Volume units implementing {@link IMeasurable}.
 *
 * <p><b>Base unit:</b> LITRE (conversionFactor = 1.0).
 * Every unit's {@code conversionFactor} expresses how many litres one unit equals.
 *
 * <p><b>Conversion pipeline:</b>
 * <pre>
 *   value  ──convertToBaseUnit()──▶  litres
 *   litres ──convertFromBaseUnit()──▶  target value
 * </pre>
 *
 * <p>Works directly with {@link Quantity}{@code <VolumeUnit>} — no changes
 * to the generic architecture required.
 */
public enum VolumeUnit implements IMeasurable {

    /** 1 millilitre = 0.001 litres */
    MILLILITRE(0.001),

    /** 1 litre = 1 litre (base unit) */
    LITRE(1.0),

    /** 1 US gallon ≈ 3.78541 litres */
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Converts a value in THIS unit to the base unit (litres).
     *
     * @param value the quantity in this unit
     * @return equivalent quantity in litres
     * @throws IllegalArgumentException if value is not finite
     */
    @Override
    public double convertToBaseUnit(double value) {
        validate(value);
        return value * conversionFactor;
    }

    /**
     * Converts a value in the base unit (litres) to THIS unit.
     *
     * @param baseValue the quantity in litres
     * @return equivalent quantity in this unit
     * @throws IllegalArgumentException if baseValue is not finite
     */
    @Override
    public double convertFromBaseUnit(double baseValue) {
        validate(baseValue);
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    private void validate(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                "Value must be finite, got: " + value);
        }
    }
}
