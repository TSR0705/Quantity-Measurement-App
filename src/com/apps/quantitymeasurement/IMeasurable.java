package com.apps.quantitymeasurement;

/**
 * UC10: Contract for all measurement units.
 *
 * <p>Any enum implementing this interface can be used with {@link Quantity}
 * to create type-safe, immutable measurements with automatic conversion.
 *
 * <p><b>Conversion pipeline:</b>
 * <pre>
 *   value  ──convertToBaseUnit()──▶  base unit
 *   base   ──convertFromBaseUnit()──▶  target value
 * </pre>
 *
 * <p><b>Single Responsibility:</b> implementations own ALL conversion logic.
 * {@link Quantity} contains NO math — it delegates everything to this interface.
 */
public interface IMeasurable {

    /**
     * Returns the conversion factor relative to the base unit.
     *
     * @return conversion factor
     */
    double getConversionFactor();

    /**
     * Converts a value in THIS unit to the base unit.
     *
     * @param value the quantity in this unit
     * @return equivalent quantity in base unit
     * @throws IllegalArgumentException if value is not finite
     */
    double convertToBaseUnit(double value);

    /**
     * Converts a value in the base unit to THIS unit.
     *
     * @param baseValue the quantity in base unit
     * @return equivalent quantity in this unit
     * @throws IllegalArgumentException if baseValue is not finite
     */
    double convertFromBaseUnit(double baseValue);

    /**
     * Returns a human-readable name for this unit.
     *
     * @return unit name (e.g., "FEET", "KILOGRAM")
     */
    String getUnitName();
}
