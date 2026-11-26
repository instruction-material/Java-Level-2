# Tic-Tac-Toe Capstone

Build the original 3-by-3 tic-tac-toe project as a console application. X and O take turns. Three marks in a row, column, or diagonal win. A full board without a winner is a tie.

1. Draw the 3-by-3 board and choose its representation. In this workbook a map stores at most nine marks, indexed by row times three plus column. A missing index marks an empty cell. The board owns legal moves and outcomes. GameSession owns input and output.
2. Implement the winning-line checks and trace all three line shapes. Check the win before a tie. The same GameSession reads live lines through MoveSource or uses a scripted sequence for a repeatable test.
3. Enter 1 1, 2 1, 1 2, 2 2, and 1 3 on separate lines. The run ends with `Winner: X`. Try an occupied cell and a nonnumeric move. Both leave the current turn in place. Q or closed input ends an unfinished round.
4. For an original capstone, adapt this scaffold or choose another bounded project. Identify its classes and input interface, document one collection invariant and one rejected input, and record a repeatable successful run. Explain which file owns each decision in a short walkthrough or recording.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
