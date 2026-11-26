# Basketball Statistics

The score for sorting is points plus rebounds. The foul limit is a class constant shared by every player.

1. Convert points to `long` before adding rebounds so large inputs fit in the sum. Compare the resulting totals with `Long.compare`.
2. Implement `isDisqualified()` using the shared limit of six fouls. Verify the difference between five fouls and six.
3. Sort the team by increasing combined score. A disqualified player still appears in the output with a status.

Expected driver output:

```text
Casey | total 16 | fouls 5 | eligible
Riley | total 18 | fouls 6 | disqualified
Jordan | total 20 | fouls 2 | eligible
```

Save the IDE workspace, download its ZIP, and extract it. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its behavior checks identify the first unfinished requirement.
