# Two Scoreboard Views

Show the same attempts in two orders. The points view uses highest points first and shortest time to break ties. The time view uses shortest time first and highest points to break ties.

1. A `Comparator<Attempt>` supplies an ordering rule to a sort. Start with `Comparator.comparingInt` on the first getter. Apply `reversed()` to an individual key that needs descending order, then use `thenComparing` or `thenComparingInt` for a tie breaker.
2. Pass each rule to `sortedCopy`. Sorting a new list leaves the input sequence intact. A stable sort keeps the input order of attempts tied on both keys.
3. Predict both outputs. The driver shows `[West, East, North, South]` by points and `[North, South, West, East]` by time. The original remains `[North, East, West, South]`.

Save and export the IDE workspace. Extract the ZIP and use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The unfinished starter compiles. The checks identify the first difference from the assignment requirements.
