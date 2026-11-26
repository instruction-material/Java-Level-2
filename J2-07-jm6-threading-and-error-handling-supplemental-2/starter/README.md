# Weather Batch Report

Summarize two sets of fictional temperature readings. Give each set to a separate worker. Copy the input when constructing a worker so a later edit to the original array cannot affect the report.

1. For a nonempty batch, initialize the minimum and maximum from its first reading. Scan the remaining readings, then publish one summary string.
2. Return `No readings.` for an empty batch. Use the same result format for one reading and for negative readings.
3. Main starts both workers and waits with join before reading either result. The sample prints `Hill: min -3, max 5` and `Lake: min 7, max 7`. Predict which result prints first even if the other worker finishes first.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its method checks pass after the missing behavior is implemented.
