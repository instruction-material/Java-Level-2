# Person Class

Constructor overloads give callers different ways to provide the same state. Use the three-argument integer constructor as the single place that assigns the fields and rejects negative ages or grades.

1. Delegate the text-grade overload with `this(name, age, Integer.parseInt(grade))`. Decimal text such as `"6"` becomes 6. Invalid number text throws `NumberFormatException`, which is a kind of `IllegalArgumentException`.
2. The two-argument form delegates with grade 0. The explicit no-argument form supplies name `"Unknown"`, age 0, and grade 0.
3. Have a birthday on one person. Keep the other person's age and both grades unchanged.

Use Java 21 in the extracted workspace.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles and prints a trace with missing behavior. The completed driver prints:

```text
Name: Abby, Grade: 2, Age: 9
Name: Jack, Grade: 6, Age: 12
Name: Tiffany, Grade: 0, Age: 10
Name: Unknown, Grade: 0, Age: 0
```

Trace where each constructor's arguments go before running the commands. Then remove the explicit no-argument constructor in a temporary copy and predict why the unchanged driver fails to compile.
