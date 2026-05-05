package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    // Accessor needed by tests to inspect the result of add()
    public double getValue() {
        return value;
    }

    // Converts this length to base unit (inches) — used by equals() and addAndConvert()
    private double toBaseUnit() {
        return value * unit.getConversionFactor();
    }

    // Converts a base-unit value to the given target unit — single source of truth
    private double convertFromBaseToTargetUnit(double baseValue, LengthUnit targetUnit) {
        return baseValue / targetUnit.getConversionFactor();
    }

    /**
     * SINGLE SOURCE OF TRUTH for all addition logic.
     * Normalises both lengths to base unit (inches), sums them,
     * then converts the result to the explicit targetUnit.
     * Returns a NEW immutable Length — no mutation.
     *
     * @param thatLength the length to add (must not be null)
     * @param targetUnit the unit for the result (must not be null)
     * @return a new Length in targetUnit representing the sum
     * @throws IllegalArgumentException if thatLength or targetUnit is null
     */
    private Length addAndConvert(Length thatLength, LengthUnit targetUnit) {
        if (thatLength == null) {
            throw new IllegalArgumentException("thatLength must not be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("targetUnit must not be null");
        }

        double thisBase    = this.toBaseUnit();
        double thatBase    = thatLength.toBaseUnit();
        double sumBase     = thisBase + thatBase;
        double resultValue = convertFromBaseToTargetUnit(sumBase, targetUnit);

        return new Length(resultValue, targetUnit);
    }

    /**
     * UC6: Adds thatLength to this length, result in THIS object's unit.
     * Delegates entirely to addAndConvert() — no logic duplication.
     *
     * @param thatLength the length to add (must not be null)
     * @return a new Length in this.unit representing the sum
     */
    public Length add(Length thatLength) {
        return addAndConvert(thatLength, this.unit);
    }

    /**
     * UC7: Adds thatLength to this length, result in the explicit targetUnit.
     * Delegates entirely to addAndConvert() — no logic duplication.
     *
     * @param thatLength the length to add (must not be null)
     * @param targetUnit the desired unit for the result (must not be null)
     * @return a new Length in targetUnit representing the sum
     */
    public Length add(Length thatLength, LengthUnit targetUnit) {
        return addAndConvert(thatLength, targetUnit);
    }

    // equals() — untouched from UC3
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Length other = (Length) obj;
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }
}
