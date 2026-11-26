# Reading Journal Counts

Count repeated entries in a fictional reading journal. An entry occupies a whole line. Empty lines are ignored.

1. Trim each UTF-8 row and convert it to lowercase with Locale.ROOT. Leave spaces inside an entry intact.
2. Increment the count when an entry is repeated. Return an alphabetically ordered report. For Cedar, Fern, a blank row, and another cedar, the result is `Counts: {cedar=2, fern=1}`.
3. Verify that an empty journal gives an empty report. Compare a missing file with an empty file.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The method checks identify what still needs to be implemented.
