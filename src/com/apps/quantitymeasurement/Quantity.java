package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * UC10: Generic, immutable measurement quantity.
 *
 * <p>Works with any unit type that implements {@link IMeasurable} — currently
 * {@link LengthUnit} and {@link WeightUnit}, but extensible to any future unit.
 *
 * <p><b>Immutability:</b> all fields are {@code final}. Every operation
 * returns a NEW {@code Quantity<U>} — no mutation ever occurs.
 *
 * <p><b>Zero math:</b> all conversion arithmetic is delegated to
 * {@link IMeasurable#convertToBaseUnit(double)} and
 * {@link IMeasurable#convertFromBaseUnit(double)}.
 *
 * <p><b>Equality:</b> two quantities are equal if and only if their
 * base-unit representations are equal. Cross-category comparison
 * (e.g., {@code Quantity<LengthUnit>} vs {@code Quantity<WeightUnit>})
 * always returns {@code false} because the runtime types differ.
 *
 * <p><b>hashCode:</b> consistent with {@code equals} — based on the
 * rounded base-unit value so that equal quantities share the same hash.
 *
 * @param <U> the unit type, must implement {@link IMeasurable}
 */
public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-7;

    private final double value;
    private final U unit;

    /**
     * Constructs an immutable Quantity.
     *
     * @param value the numeric quantity (must be finite)
     * @param unit  the unit of measurement (must not be null)
     * @throws IllegalArgumentException if value is not finite or unit is null
     */
    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("unit must not be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("value must be finite, got: " + value);
        }
        this.value = value;
        this.unit  = unit;
    }

    /** Returns the raw numeric value in this quantity's unit. */
    public double getValue() {
        return value;
    }

    /** Returns the unit of this quantity. */
    public U getUnit() {
        return unit;
    }

    /**
     * Converts this quantity to the given target unit.
     *
     * @param targetUnit the desired unit (must not be null)
     * @return a new immutable Quantity in targetUnit
     * @throws IllegalArgumentException if targetUnit is null
     */
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("targetUnit must not be null");
        }
        double base   = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(result, targetUnit);
    }

    /**
     * Adds {@code other} to this quantity, result in THIS quantity's unit.
     *
     * @param other the quantity to add (must not be null)
     * @return a new immutable Quantity in this.unit representing the sum
     * @throws IllegalArgumentException if other is null
     */
    public Quantity<U> add(Quantity<U> other) {
        return addAndConvert(other, this.unit);
    }

    /**
     * Adds {@code other} to this quantity, result in the explicit {@code targetUnit}.
     *
     * @param other      the quantity to add (must not be null)
     * @param targetUnit the desired unit for the result (must not be null)
     * @return a new immutable Quantity in targetUnit representing the sum
     * @throws IllegalArgumentException if other or targetUnit is null
     */
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        return addAndConvert(other, targetUnit);
    }

    /**
     * Single source of truth for all addition logic.
     * Normalises both quantities to base unit, sums, converts to targetUnit.
     */
    private Quantity<U> addAndConvert(Quantity<U> other, U targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("targetUnit must not be null");
        }
        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        double sumBase   = thisBase + otherBase;
        double result    = targetUnit.convertFromBaseUnit(sumBase);
        return new Quantity<>(result, targetUnit);
    }

    /**
     * Equality via base-unit comparison with epsilon tolerance.
     * Cross-category quantities (different runtime class of U) are never equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Quantity<?> other = (Quantity<?>) obj;
        // Cross-category safety: unit types must be the same class
        if (!this.unit.getClass().equals(other.unit.getClass())) return false;
        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    /**
     * hashCode consistent with equals — based on rounded base-unit value
     * so that equal quantities (e.g., 1 ft and 12 in) share the same hash.
     */
    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        // Round to 6 decimal places to match epsilon tolerance
        long rounded = Math.round(base * 1_000_000.0);
        return Objects.hash(unit.getClass().getName(), rounded);
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}
