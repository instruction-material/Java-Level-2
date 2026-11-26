# Maze Reader

Load a rectangular UTF-8 grid with spaces for open cells, # for walls, one P for the start, and one E for the exit. Coordinates are zero-based, with x across and y down.

1. Trace the loader from the first row through the recorded start position. Add the check that the number of exits is exactly one. Reject missing, empty, uneven, and otherwise malformed boards before displaying a grid.
2. Complete isValidMove. A move can reach a space or the exit. Check the coordinate bounds before looking up a cell, and reject walls.
3. Run practice.txt. Its start is `Start: (1, 1)`. The exit is (3, 1). Predict the result for a wall, a space, the exit, and a coordinate beyond the board. Try `java Main medium.txt` to see a larger supplied layout.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

In the starter, the driver runs and the method checks identify unfinished behavior.
