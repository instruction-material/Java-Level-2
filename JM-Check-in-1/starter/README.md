# Car Check-In

Keep type, color, and mileage on each object. Keep the number of successful constructions on the class.

1. Trace both constructors. The short form passes mileage zero to the full constructor. Increase the counter once after validating the initial mileage.
2. Compare the odometers in `sameMileage`. Return false for a null argument. The supplied `drive` method accepts zero and uses `Math.addExact` to detect overflow before updating the field.
3. Implement `compareTo` using `Integer.compare` on mileage. Equal mileage is a sorting tie. Predict the sorted order and the value of `Car.getNumCars()`.

The driver prints these lines:

```text
A yellow coupe that has driven 1500 miles.
A red sedan that has driven 20000 miles.
A blue sports car that has driven 60000 miles.
Cars created: 3
```

## Optional batch challenge

Continue in the same workbook. Finish `driveAll(int... distances)`. Start with a local candidate mileage. Validate each distance and use `Math.addExact` to add it. Assign the new mileage once after the entire batch succeeds. Invalid distances and overflow leave the old value in place. An empty batch preserves the original value.

Run `java ContractTests extension` after the core checks. The starter compiles and both stages identify unfinished behavior.

Save and export the IDE workspace. Extract the ZIP and use Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```
