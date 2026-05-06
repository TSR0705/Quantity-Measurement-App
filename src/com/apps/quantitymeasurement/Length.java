package com.apps.quantitymeasurement;

/**
 * UC8: Length contains ZERO conversion math.
 * All arithmetic is delegated to LengthUnit.convertToBaseUnit()
 * and LengthUnit.convertFromBaseUnit() — Single Responsibility enforced.
 */
public class Length {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    /**
     * SINGLE SOURCE OF TRUTH for all addition logic.
     * Delegates to LengthUnit for every conversion step — no math here.
     *
     * @param thatLength the length to add (must not be null)
     * @param targetUnit the unit for the result (must not be null)
     * @return a new immutable Length in targetUnit representing the sum
     * @throws IllegalArgumentException if thatLength or targetUnit is null
     */
    private Length addAndConvert(Length thatLength, LengthUnit targetUnit) {
        if (thatLength == null) {
            throw new IllegalArgumentException("thatLength must not be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("targetUnit must not be null");
        }

        double thisBase    = this.unit.convertToBaseUnit(this.value);
        double thatBase    = thatLength.unit.convertToBaseUnit(thatLength.value);
        double sumBase     = thisBase + thatBase;
        double resultValue = targetUnit.convertFromBaseUnit(sumBase);

        return new Length(resultValue, targetUnit);
    }

    /**
     * UC6: Adds thatLength to this length, result in THIS object's unit.
     */
    public Length add(Length thatLength) {
        return addAndConvert(thatLength, this.unit);
    }

    /**
     * UC7: Adds thatLength to this length, result in the explicit targetUnit.
     */
    public Length add(Length thatLength, LengthUnit targetUnit) {
        return addAndConvert(thatLength, targetUnit);
    }

    /**
     * UC3: Equality via base-unit comparison with epsilon tolerance.
     * Delegates to LengthUnit — no math in Length.
     * Uses epsilon tolerance to handle floating-point precision across unit conversions.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Length other = (Length) obj;
        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        return Math.abs(thisBase - otherBase) < 1e-7;
    }
}
