# Untimed Maze Runner

Use Maze for the fixed layout, Player for the position, and MazeGame for the console. The practice board puts the exit two steps right of the start.

1. Translate W, A, S, and D into one candidate coordinate. Check that cell against the maze before changing the player’s position. A wall or invalid command leaves the position alone.
2. After a valid move, check the exit. A win prints `Congratulations! You've reached the exit!` and ends the game. Q and closed input also end it.
3. Run the supplied fixture and enter W, D, D on separate lines. W hits the upper wall. The following moves advance to (2, 1) and (3, 1). Predict the positions and compare each printed board.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

In the starter, the driver runs and the method checks identify unfinished behavior.
