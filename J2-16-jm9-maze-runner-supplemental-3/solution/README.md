# Review a Batch of Maze Files

Validate a batch of maze layouts and summarize the result for each labeled case. An error in one file leaves the other reports available.

1. Use the supplied map of case labels to paths. For each board, create a Maze and report its start coordinates. Catch IllegalArgumentException for that board and report the diagnostic without printing its contents.
2. Use a TreeMap so report order is independent of input order. Keep results for missing, malformed, and ready boards in one table.
3. Run the sample. Its first line is `broken: Maze needs one player start and one exit.`, and its last line is `practice: Ready at (1, 1)`. Change the order of the input labels and compare the result.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

In the starter, the driver runs and the method checks identify unfinished behavior.
