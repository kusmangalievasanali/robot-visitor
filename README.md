# Visitor Pattern — Robot Parts Inspection

Implementation of the **Visitor** behavioral design pattern in Java.

## Topic

A robot consists of a stable set of parts (battery, motor, sensor, camera). Many unrelated
operations are needed on these parts: diagnostics, cost calculation, maintenance planning.
Instead of adding these operations to every part class, each operation lives in its own
visitor. New operations are added without touching the part classes.

## Pattern Structure

| Role | Class | Description |
|---|---|---|
| Element Interface | `RobotPart` | Declares `accept(RobotPartVisitor visitor)` |
| Concrete Element | `Battery` | Charge, capacity, price |
| Concrete Element | `Motor` | Power, hours worked, price |
| Concrete Element | `Sensor` | Type, accuracy, price |
| Concrete Element | `Camera` | Megapixels, price |
| Visitor Interface | `RobotPartVisitor` | `visitBattery`, `visitMotor`, `visitSensor`, `visitCamera` |
| Concrete Visitor | `DiagnosticsVisitor` | Finds worn or faulty parts |
| Concrete Visitor | `CostVisitor` | Sums the price of all parts |
| Concrete Visitor | `MaintenanceVisitor` | Plans maintenance tasks and hours |
| Object Structure | `Robot` | Holds a list of parts, `accept(visitor)` traverses them |
| Client | `Main` | Builds robots and applies the visitors |

## Double Dispatch

1. The client calls `robot.accept(visitor)`. `Robot` calls `part.accept(visitor)` for each part.
2. **First dispatch:** the runtime type of the **part** decides which `accept()` runs
   (`Battery.accept`, `Motor.accept`, ...).
3. **Second dispatch:** inside `accept()` the part calls the matching method on the visitor
   and passes itself: `visitor.visitBattery(this)`. The runtime type of the **visitor**
   decides which implementation runs (`DiagnosticsVisitor.visitBattery`, ...).

Because the part already knows its own type, no `instanceof` or `switch` is needed anywhere.

## How to Run

1. Open the project in IntelliJ IDEA and wait for Gradle sync.
2. Open `src/main/java/robot_visitor/Main.java`.
3. Click the green Run button next to `main()` (or press `Shift+F10`).

Or from the terminal:

```
.\gradlew.bat build
java -cp build/classes/java/main robot_visitor.Main
```

## Example Output

```
=== Robot: R2-Alpha ===
[Diagnostics] 3 issue(s)
  - Battery charge is low: 15%
  - Motor is worn out: 1200 h
  - Sensor 'ultrasonic' is inaccurate: 85%
[Cost] total price: 890 USD
[Maintenance] 4 task(s), 7 h
  - Recharge battery (1 h)
  - Replace motor (4 h)
  - Recalibrate 'ultrasonic' sensor (1 h)
  - Clean camera lens (1 h)

=== Robot: Scout-2 ===
[Diagnostics] all parts OK
[Cost] total price: 750 USD
[Maintenance] 1 task(s), 1 h
  - Clean camera lens (1 h)
```

## Clean Code Principles Applied

1. **Meaningful names distinguishing Element vs. Visitor** — parts are named after what
   they are (`Battery`, `Motor`), visitors after the operation they perform
   (`DiagnosticsVisitor`, `CostVisitor`). `accept` belongs to elements, `visitX` to visitors,
   so the role of every class is clear from its name.

2. **No `instanceof` or type-checking chains** — double dispatch fully replaces them.
   There is no `instanceof`, no `switch` on part type and no casts in the whole project.

3. **Single Responsibility** — each concrete visitor implements exactly one operation across
   all part types. Part classes only hold their own data and know nothing about diagnostics,
   cost or maintenance.

4. **Minimal, well-justified encapsulation trade-off** — visitors need part data, so every
   part exposes public read-only getters. Fields are `private final` and there are no
   setters, so a visitor can read a part but can never modify it. Constructors validate
   their input (fail fast), and visitors return unmodifiable lists of results.

5. **Demonstrated extensibility** — `MaintenanceVisitor` was added as a third visitor
   without changing `Battery`, `Motor`, `Sensor`, `Camera`, `RobotPart` or `Robot`.
   Any further operation (for example an export visitor) is added the same way.

6. **No magic numbers** — thresholds are named constants (`MIN_BATTERY_CHARGE`,
   `MAX_MOTOR_HOURS`, `MIN_SENSOR_ACCURACY`, ...), so the rules read like sentences and
   can be changed in one place.

7. **Small, focused methods** — every `visitX()` method does one check; the shared
   `addTask()` helper in `MaintenanceVisitor` avoids duplication; `Main` is split into
   `createWornRobot()`, `printDiagnostics()`, `printCost()`, `printMaintenance()`.

## Trade-off of the Pattern

Adding a new **operation** is cheap (one new visitor class). Adding a new **part type**
(for example `Gripper`) is expensive: `RobotPartVisitor` and every concrete visitor must be
updated. Visitor fits best when the set of element types is stable and the operations change.
