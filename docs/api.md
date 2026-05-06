# API Reference

## `Quantity<U extends IMeasurable>`

The primary API. All operations return new immutable instances.

### Constructor

```java
new Quantity<>(double value, U unit)
```

Throws `IllegalArgumentException` if `value` is not finite or `unit` is null.

### Accessors

```java
double getValue()   // raw numeric value in this quantity's unit
U      getUnit()    // the unit
```

### Conversion

```java
Quantity<U> convertTo(U targetUnit)
```

Converts to `targetUnit` via base-unit normalization. Throws `IllegalArgumentException` if `targetUnit` is null.

### Arithmetic

```java
// Result in caller's unit
Quantity<U> add(Quantity<U> other)
Quantity<U> subtract(Quantity<U> other)

// Result in explicit target unit
Quantity<U> add(Quantity<U> other, U targetUnit)
Quantity<U> subtract(Quantity<U> other, U targetUnit)

// Dimensionless scalar
double divide(Quantity<U> other)
```

**Exceptions:**
- `IllegalArgumentException` — null operand or null target unit
- `ArithmeticException` — division by zero (divisor's base-unit value is 0)

### Object Contract

```java
boolean equals(Object obj)   // epsilon-based base-unit comparison (ε = 1e-7)
int     hashCode()           // consistent with equals
String  toString()           // "1.0 FEET"
```

---

## `IMeasurable`

```java
double getConversionFactor()
double convertToBaseUnit(double value)      // throws on NaN/Infinity
double convertFromBaseUnit(double baseValue) // throws on NaN/Infinity
String getUnitName()
```

---

## `QuantityMeasurementApp` — Static Facade

### Generic (UC10+)

```java
static <U extends IMeasurable> Quantity<U> convert(Quantity<U> q, U targetUnit)
static <U extends IMeasurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2)
static <U extends IMeasurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2, U target)
static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2)
static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2, U target)
static <U extends IMeasurable> double      demonstrateDivision(Quantity<U> q1, Quantity<U> q2)
```

### Legacy Length (UC5–UC7)

```java
static double convert(double value, LengthUnit from, LengthUnit to)
static Length demonstrateLengthAddition(Length l1, Length l2)
static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit target)
```

### Legacy Weight (UC9)

```java
static double convertWeight(double value, WeightUnit from, WeightUnit to)
static Weight demonstrateWeightAddition(Weight w1, Weight w2)
static Weight demonstrateWeightAddition(Weight w1, Weight w2, WeightUnit target)
```

---

## Error Handling Summary

| Condition | Exception |
|-----------|-----------|
| Null operand | `IllegalArgumentException` |
| Null target unit | `IllegalArgumentException` |
| NaN or Infinite value | `IllegalArgumentException` |
| Division by zero | `ArithmeticException` |
