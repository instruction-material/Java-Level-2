# Replay a Saved Route

Replay one movement command per line from a UTF-8 file. Report which actions succeeded without asking for live input. Use a fresh player position for every replay.

1. Read the supplied commands.txt. Trim each row, map W, A, S, or D to the next cell, and count unknown input and blocked cells as rejected moves.
2. Move only on a legal candidate. Once a move reaches the exit, end the replay and ignore the remaining commands.
3. For W, D, d, W on the practice board, the result is `Accepted: 2, rejected: 1, escaped: true`. Compare the report with a route that ends early or has no commands.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

In the starter, the driver runs and the method checks identify unfinished behavior.
