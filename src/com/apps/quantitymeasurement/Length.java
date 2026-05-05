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

    // Converts this length to base unit (inches) — used by equals() and add()
    private double toBaseUnit() {
        return value * unit.getConversionFactor();
    }

    // Converts a base-unit value back to the given target unit — used by add()
    private double convertFromBaseToTargetUnit(double baseValue, LengthUnit targetUnit) {
        return baseValue / targetUnit.getConversionFactor();
    }

    /**
     * UC6: Adds thatLength to this length.
     * Both are normalised to base unit (inches), summed, then converted back
     * to THIS object's unit. Returns a NEW immutable Length — no mutation.
     *
     * @param thatLength the length to add (must not be null)
     * @return a new Length in this.unit representing the sum
     * @throws IllegalArgumentException if thatLength is null
     */
    public Length add(Length thatLength) {
        if (thatLength == null) {
            throw new IllegalArgumentException("thatLength must not be null");
        }

        double thisBase  = this.toBaseUnit();
        double thatBase  = thatLength.toBaseUnit();
        double sumBase   = thisBase + thatBase;

        double resultValue = convertFromBaseToTargetUnit(sumBase, this.unit);
        return new Length(resultValue, this.unit);
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
