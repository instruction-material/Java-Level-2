# List Legal Maze Moves

Give a player the legal moves from a single coordinate. A hint reads the board and leaves the starting position in place.

1. Check candidate cells in W, A, S, D order. Compare each cell to the board bounds and walls.
2. Return the matching directions. A wall or out-of-bounds starting point gives an empty list.
3. The practice board prints `From start: [D]` and `From middle: [A, D]`. Explain why the same lookup can be used before a move or as a hint after a rejected move.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
