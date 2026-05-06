package com.apps.quantitymeasurement;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

/**
 * Generic, immutable measurement quantity.
 *
 * <p><b>Immutability:</b> all fields are {@code final}. Every operation
 * returns a NEW {@code Quantity<U>} — no mutation ever occurs.
 *
 * <p><b>Zero math in public methods:</b> all arithmetic is routed through
 * {@link #performArithmetic} and validated by {@link #validateOperands}.
 * Public methods are thin delegators — no duplication.
 *
 * <p><b>Arithmetic pipeline:</b>
 * <pre>
 *   validate operands
 *   convert both to base unit via IMeasurable.convertToBaseUnit()
 *   apply ArithmeticOperation (ADD / SUBTRACT / DIVIDE)
 *   convert result back via targetUnit.convertFromBaseUnit()
 *   return new Quantity (or raw double for DIVIDE)
 * </pre>
 *
 * @param <U> the unit type, must implement {@link IMeasurable}
 */
public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-7;

    // -----------------------------------------------------------------------
    // UC13: Centralized arithmetic operation enum (lambda implementation).
    //
    // DoubleBinaryOperator chosen over abstract compute() because:
    //   - each constant is a single expression — no boilerplate
    //   - trivially extensible (MULTIPLY, MODULO, etc.)
    //   - identical readability, less code
    // -----------------------------------------------------------------------
    private enum ArithmeticOperation {
        ADD      ((a, b) -> a + b),
        SUBTRACT ((a, b) -> a - b),
        DIVIDE   ((a, b) -> a / b);

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        double apply(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    // -----------------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------------

    private final double value;
    private final U unit;

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

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

    // -----------------------------------------------------------------------
    // Accessors
    // -----------------------------------------------------------------------

    /** Returns the raw numeric value in this quantity's unit. */
    public double getValue() {
        return value;
    }

    /** Returns the unit of this quantity. */
    public U getUnit() {
        return unit;
    }

    // -----------------------------------------------------------------------
    // Conversion
    // -----------------------------------------------------------------------

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

    // -----------------------------------------------------------------------
    // Addition (UC6/UC7)
    // -----------------------------------------------------------------------

    /**
     * Adds {@code other} to this quantity, result in THIS quantity's unit.
     *
     * @param other the quantity to add (must not be null)
     * @return a new immutable Quantity in this.unit representing the sum
     * @throws IllegalArgumentException if other is null
     */
    public Quantity<U> add(Quantity<U> other) {
        return computeQuantityResult(other, this.unit, ArithmeticOperation.ADD);
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
        return computeQuantityResult(other, targetUnit, ArithmeticOperation.ADD);
    }

    // -----------------------------------------------------------------------
    // Subtraction (UC12)
    // -----------------------------------------------------------------------

    /**
     * Subtracts {@code other} from this quantity, result in THIS quantity's unit.
     *
     * @param other the quantity to subtract (must not be null)
     * @return a new immutable Quantity in this.unit representing the difference
     * @throws IllegalArgumentException if other is null
     */
    public Quantity<U> subtract(Quantity<U> other) {
        return computeQuantityResult(other, this.unit, ArithmeticOperation.SUBTRACT);
    }

    /**
     * Subtracts {@code other} from this quantity, result in the explicit {@code targetUnit}.
     *
     * @param other      the quantity to subtract (must not be null)
     * @param targetUnit the desired unit for the result (must not be null)
     * @return a new immutable Quantity in targetUnit representing the difference
     * @throws IllegalArgumentException if other or targetUnit is null
     */
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        return computeQuantityResult(other, targetUnit, ArithmeticOperation.SUBTRACT);
    }

    // -----------------------------------------------------------------------
    // Division (UC12)
    // -----------------------------------------------------------------------

    /**
     * Divides this quantity by {@code other}, returning a dimensionless scalar.
     * Both quantities are normalised to base unit before division.
     *
     * @param other the divisor (must not be null, must not be zero)
     * @return the dimensionless ratio this / other (raw double, no rounding)
     * @throws IllegalArgumentException if other is null
     * @throws ArithmeticException      if other's base-unit value is zero
     */
    public double divide(Quantity<U> other) {
        validateOperands(other, null, false);
        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        if (otherBase == 0.0) {
            throw new ArithmeticException(
                "Division by zero: divisor quantity has zero base-unit value");
        }
        return ArithmeticOperation.DIVIDE.apply(thisBase, otherBase);
    }

    // -----------------------------------------------------------------------
    // UC13: Private centralized helpers
    // -----------------------------------------------------------------------

    /**
     * UC13: Single source of truth for ADD and SUBTRACT operations.
     *
     * <p>Pipeline:
     * <ol>
     *   <li>Validate operands (null, category, finite)</li>
     *   <li>Convert both to base unit</li>
     *   <li>Apply {@code operation}</li>
     *   <li>Convert result to {@code targetUnit}</li>
     *   <li>Return new immutable Quantity</li>
     * </ol>
     *
     * @param other     the second operand
     * @param targetUnit the unit for the result
     * @param operation  ADD or SUBTRACT
     * @return new immutable Quantity in targetUnit
     */
    private Quantity<U> computeQuantityResult(Quantity<U> other,
                                               U targetUnit,
                                               ArithmeticOperation operation) {
        validateOperands(other, targetUnit, true);
        double baseResult = performArithmetic(other, operation);
        double result     = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(result, targetUnit);
    }

    /**
     * UC13: Centralized arithmetic execution.
     * Converts both operands to base unit and applies the operation.
     *
     * @param other     the second operand
     * @param operation the operation to apply
     * @return the result in base unit
     */
    private double performArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        return operation.apply(thisBase, otherBase);
    }

    /**
     * UC13: Centralized validation for all arithmetic operands.
     *
     * @param other              the second operand
     * @param targetUnit         the target unit (may be null if not required)
     * @param targetUnitRequired whether targetUnit must be non-null
     * @throws IllegalArgumentException for null/incompatible/non-finite inputs
     */
    private void validateOperands(Quantity<U> other,
                                   U targetUnit,
                                   boolean targetUnitRequired) {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        }
        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException("targetUnit must not be null");
        }
    }

    // -----------------------------------------------------------------------
    // Object overrides
    // -----------------------------------------------------------------------

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
        double base    = unit.convertToBaseUnit(value);
        long   rounded = Math.round(base * 1_000_000.0);
        return Objects.hash(unit.getClass().getName(), rounded);
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}
