# Find a Shortest Maze Route

Find a route from P to E using the same legal moves as the maze runner. Each move has equal cost. This optional activity introduces a queue that explores nearby cells before farther ones.

1. Add the start to the queue. For each next cell, add each legal, previously unseen neighbor. Record its previous cell and move. That record also marks it as seen.
2. When reaching E, trace previous cells back to the start and reverse the directions. If the queue empties first, return an empty list. The board has one distinct start and exit.
3. The sample prints `Shortest route: [D, D]` on the practice board and `Blocked route: []` on the enclosed-board fixture. Trace the queue at each stage and explain why no longer alternative is returned.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
