package com.apps.quantitymeasurement;

/**
 * UC9: Immutable weight measurement.
 *
 * <p><b>Immutability:</b> all fields are {@code final}. Every operation
 * returns a NEW {@code Weight} object — no mutation ever occurs.
 *
 * <p><b>Zero math:</b> this class contains NO conversion arithmetic.
 * All conversion is delegated to {@link WeightUnit#convertToBaseUnit(double)}
 * and {@link WeightUnit#convertFromBaseUnit(double)} — Single Responsibility enforced.
 *
 * <p><b>Equality contract:</b> two weights are equal if and only if their
 * base-unit (gram) representations are equal within a floating-point epsilon.
 * The contract is reflexive, symmetric, and transitive.
 *
 * <p><b>Addition pipeline:</b>
 * <pre>
 *   this  ──convertToBaseUnit()──▶  grams
 *   other ──convertToBaseUnit()──▶  grams
 *                                   sum (grams)
 *   sum   ──convertFromBaseUnit()──▶  result in targetUnit
 * </pre>
 */
public class Weight {

    private final double value;
    private final WeightUnit unit;

    /**
     * Constructs an immutable Weight.
     *
     * @param value the numeric quantity
     * @param unit  the unit of measurement (must not be null)
     */
    public Weight(double value, WeightUnit unit) {
        this.value = value;
        this.unit  = unit;
    }

    /**
     * Returns the raw numeric value in this weight's unit.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * Converts this weight to the given target unit.
     *
     * @param targetUnit the desired unit (must not be null)
     * @return a new immutable Weight in targetUnit
     * @throws IllegalArgumentException if targetUnit is null
     */
    public Weight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("targetUnit must not be null");
        }
        double base   = this.unit.convertToBaseUnit(this.value);
        double result = targetUnit.convertFromBaseUnit(base);
        return new Weight(result, targetUnit);
    }

    /**
     * UC9: Adds {@code other} to this weight, result in THIS object's unit.
     * Delegates entirely to {@link #addAndConvert(Weight, WeightUnit)}.
     *
     * @param other the weight to add (must not be null)
     * @return a new immutable Weight in this.unit representing the sum
     * @throws IllegalArgumentException if other is null
     */
    public Weight add(Weight other) {
        return addAndConvert(other, this.unit);
    }

    /**
     * UC9: Adds {@code other} to this weight, result in the explicit {@code targetUnit}.
     * Delegates entirely to {@link #addAndConvert(Weight, WeightUnit)}.
     *
     * @param other      the weight to add (must not be null)
     * @param targetUnit the desired unit for the result (must not be null)
     * @return a new immutable Weight in targetUnit representing the sum
     * @throws IllegalArgumentException if other or targetUnit is null
     */
    public Weight add(Weight other, WeightUnit targetUnit) {
        return addAndConvert(other, targetUnit);
    }

    /**
     * SINGLE SOURCE OF TRUTH for all addition logic.
     * Normalises both weights to base unit (grams), sums them,
     * then converts the result to {@code targetUnit}.
     * Returns a NEW immutable Weight — no mutation.
     *
     * @param other      the weight to add (must not be null)
     * @param targetUnit the unit for the result (must not be null)
     * @return a new immutable Weight in targetUnit representing the sum
     * @throws IllegalArgumentException if other or targetUnit is null
     */
    private Weight addAndConvert(Weight other, WeightUnit targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("targetUnit must not be null");
        }

        double thisBase   = this.unit.convertToBaseUnit(this.value);
        double otherBase  = other.unit.convertToBaseUnit(other.value);
        double sumBase    = thisBase + otherBase;
        double result     = targetUnit.convertFromBaseUnit(sumBase);

        return new Weight(result, targetUnit);
    }

    /**
     * Equality via base-unit (gram) comparison with epsilon tolerance.
     * Delegates to WeightUnit — no math in Weight.
     *
     * <p>Contract: reflexive, symmetric, transitive.
     * Returns {@code false} for null or different class.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Weight other     = (Weight) obj;
        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        return Math.abs(thisBase - otherBase) < 1e-4;
    }
}
