# Check a Count Before Summing

The first input line holds a nonnegative count. Exactly that many signed int values follow, one per line. Whitespace around a number is allowed. Use a long for the sum. Reject a missing header, negative count, missing or extra values, and malformed numbers with IllegalArgumentException.

Implement CountedSum.sum. Use 0 as the header for an empty batch. Compare two inputs that differ only by the last line. Predict whether each produces a sum or an error.

The supplied example prints `Sum: 9`.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
