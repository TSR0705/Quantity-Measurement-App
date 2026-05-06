# Contributing

## Branch Naming

| Type | Pattern | Example |
|------|---------|---------|
| Feature | `Feature/UC{n}` | `Feature/UC14` |
| Refactor | `Refactor/UC{n}-{description}` | `Refactor/UC13-DRY-Arithmetic` |
| Bugfix | `fix/{description}` | `fix/epsilon-precision` |

## Commit Convention

Each commit should represent one logical step. The project uses this pattern:

```
UC{n}: <what was done>
```

Examples:
```
UC11: Add VolumeUnit enum implementing IMeasurable
UC13: Centralize arithmetic validation and execution logic
```

## TDD Workflow

This project follows strict TDD. Every change must follow this sequence:

1. **Write failing tests first** — commit them before any implementation
2. **Implement minimal code** to make tests pass
3. **Commit implementation** separately from tests

```
git add src/.../QuantityMeasurementAppTest.java
git commit -m "UC{n}: Add failing tests for ..."

# implement

git add src/.../Quantity.java   # or whichever file changed
git commit -m "UC{n}: Implement ..."
```

## Adding a New Unit Category

1. Create `XUnit.java` implementing `IMeasurable`:
   - Choose a base unit (conversionFactor = 1.0)
   - Express all other units relative to the base
   - Implement `convertToBaseUnit`, `convertFromBaseUnit`, `validate`, `getUnitName`

2. Write tests in `QuantityMeasurementAppTest.java`:
   - Enum validation (factors, NaN, Infinity)
   - Equality (same unit, cross-unit, null, reference)
   - Conversion (round-trip)
   - Addition, subtraction, division
   - Cross-category safety

3. Do **not** modify `Quantity.java`, `IMeasurable.java`, or any existing enum.

## Running Tests

```bash
# Compile
javac -cp "lib/junit-platform-console-standalone-1.10.2.jar" \
      -d out/test \
      src/com/apps/quantitymeasurement/*.java

# Run
java -jar lib/junit-platform-console-standalone-1.10.2.jar \
     --class-path out/test \
     --select-class com.apps.quantitymeasurement.QuantityMeasurementAppTest
```

All 177 tests must pass before pushing.

## Merge Flow

```
Feature/UC{n} → dev → main
```

PowerShell:
```powershell
git checkout dev; git merge Feature/UC{n} --no-edit; git push origin dev
git checkout main; git merge dev --no-edit; git push origin main
```
