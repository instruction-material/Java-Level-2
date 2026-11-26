# Try-Catch This

Use one method for integer division and one method for looking up a number by array index. Each recovers from one specific failure.

1. Keep only the operation that can fail and its success result inside each try block. Catch `ArithmeticException` around division and `ArrayIndexOutOfBoundsException` around the array lookup. Use the documented response when the operation fails.
2. Trace the fixed cases in Main. In order, the division cases are 12 ÷ 3, 12 ÷ 0, then 12 ÷ 2. The array cases use indices 1, −1, 3, then 2 with the array [4, 9, 15].
3. Check that the failed operations leave the array unchanged and allow a corrected request. The driver prints `Quotient: 4`, `Cannot divide by zero.`, and `Quotient: 6` before the index checks. An index outside the array prints `Index outside array.`.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its method checks pass after the missing behavior is implemented.
