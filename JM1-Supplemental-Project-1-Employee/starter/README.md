# Employee Class

Extend the private-instance-field pattern from Dog Class. Keep the employee's name and ID fixed after construction. Keep the position and years worked mutable.

1. Read the starter constructor and getters. Write down the initial title and years for Jacky and Tina.
2. Implement `setPosition(String)` and `setYearsWorked(int)`. Reject a negative number of years with `IllegalArgumentException` before changing the field. Apply the same rule in the constructor.
3. Complete `toString()` using the fields and labels from the expected output.
4. In the driver, identify the receiver of each setter. Check which value changes and which value stays the same for each object.

Save and export the workspace, extract it, then use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

Expected output from the completed driver:

```text
Name: Jacky, Position: Engineer, Years worked: 1, ID Number: 556614
Name: Tina, Position: Manager, Years worked: 4, ID Number: 76544
```

Write the numeric ID as `76544`. Java interprets a leading zero on an integer literal as octal. To preserve leading zeroes as part of an identifier, store the identifier as text in a separate extension.
