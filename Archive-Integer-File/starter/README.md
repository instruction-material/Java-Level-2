# Read a Counted UTF-8 File

The first row of values.txt is the number of integers that follow. Parse UTF-8 text, keep the integers in file order, and return an immutable list. A count of zero gives an empty list. Reject a missing, invalid, or mismatched count and malformed records. Leave the input file intact. An unreadable path still reports an I/O failure.

Implement IntegerFile.read. Change the last number in a copy of values.txt and predict the resulting list. Then remove a line and check that the program reports a format failure before returning any records.

The supplied example prints `Values: [12, -3, 0, 7]`.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
