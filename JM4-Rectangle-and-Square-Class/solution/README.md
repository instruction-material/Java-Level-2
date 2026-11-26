# Rectangle and Square

A rectangle has immutable positive dimensions. A square can safely share that contract because it supplies the same side twice and the dimensions cannot be resized separately.

1. Follow `super(side, side)` into the parent constructor. Compare a rectangle with width 3 and length 5 to a square with side 4.
2. Give Square its own `shapeName()`. Keep the area and perimeter formulas in Rectangle so both types use the same calculation.
3. Trace the two `Rectangle` references in the driver. The shape name comes from the actual object. Its area and perimeter come from the common formulas.

The expected lines are `Rectangle: area 15, perimeter 16` and `Square: area 16, perimeter 16`.

Save the IDE workspace, download its ZIP, and extract the folder. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its checks show which behavior still needs to be implemented.
