# Timed Maze Runner

Add a thread that counts down while the main thread owns the board, player, and input. Start with the untimed runner and keep movement rules the same.

1. Complete Timer.run so it decreases the shared countdown once per tick and ends at zero. If its wait is interrupted, restore the interrupted flag and return.
2. Keep the owner’s cleanup path around the game loop. Closing the timer interrupts and joins its worker after a win, quit, closed input, or timeout.
3. Try Q and expect `Game ended.`. Close input and expect `Input ended.`. The checks use a short countdown to verify the expiry message `Time's up! You failed to reach the exit.`. If the console is waiting when time expires, Enter lets the loop report expiry. The late move is rejected.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

In the starter, the driver runs and the method checks identify unfinished behavior.
