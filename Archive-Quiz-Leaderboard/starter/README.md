# Order Quiz Results With Ties

Each complete result has a fictional alias and a nonnegative score. Return at most the requested number of entries. Highest scores come first. An equal score stays in original arrival order. Keep the supplied result list unchanged. A zero limit gives an empty list, and a negative limit is invalid.

Implement Leaderboard.top. Lee and Ari tie in the sample. The earlier entry appears first. Try a limit of one and explain why sorting by name would change the result.

The supplied example prints `Top: [Lee (3), Ari (3)]`.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
