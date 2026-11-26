# Calculator

Use methods for arithmetic, remainder, powers, and factorial. The caller chooses one operation and provides its operands. Invalid input produces a specific message.

1. Choose an arithmetic operation with two operands. Reject division or remainder by zero and reject nonfinite inputs or results. For factorial use one whole number from 0 to 20 and return an exact long.
2. The default run demonstrates `add 10 2`, `factorial 5`, and `divide 5 0`. It prints `Sum: 12.0`, `5!: 120`, and `Divide by zero.`.
3. Run `java Main power 2 4`, `java Main remainder 9 4`, or `java Main factorial 0`. Compare the exact factorial result with the arithmetic output. Try missing, extra, and malformed operands.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror -d out src/main/java/*.java
java -cp out Main
```
