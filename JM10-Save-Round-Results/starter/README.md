# Save Round Results

Extend the tic-tac-toe capstone with a versioned UTF-8 results file. Save completed rounds only. Each line after ROUNDS-1 records X_WINS, O_WINS, or TIE.

1. Validate all rounds before replacing a file. Write to a temporary file beside the destination, then move it into place. A rejected write leaves the earlier results available.
2. Load and validate every record into a new list. A missing file starts with no rounds. A malformed file reports the line that failed.
3. Reopen the three sample rounds. The report is `Reopened: [X_WINS, TIE, O_WINS]`. Check an invalid record and explain why the original board behavior can remain unchanged.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
