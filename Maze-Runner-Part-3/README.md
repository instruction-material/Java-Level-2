# Timed Maze Runner

The main thread owns the board, player, and console input. A worker updates a
30-second countdown. The game stops and joins that worker when the player wins,
quits, reaches the deadline, or closes the input.

Use Java 21 from this folder.

```sh
javac --release 21 -Xlint:all -Werror -d out src/main/java/*.java
java -cp out Main easy.txt
```

Enter one of W, A, S, or D on a line to move, or Q to quit. The countdown runs
while the terminal waits for a line. Press Enter if the deadline has already
passed. A move submitted after the countdown reaches zero is rejected.

The supplied maps are easy.txt, medium.txt, and hard.txt. A map is a rectangular
UTF-8 grid with one P, one E, walls marked #, and spaces for open cells.

Trace an invalid move, a successful move, and a quit from the first position.
Then close input and confirm the Java process returns immediately. Compare the
state read by the input loop with the state written by the timer worker.
