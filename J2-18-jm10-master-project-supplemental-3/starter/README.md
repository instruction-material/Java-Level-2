# Register Console Commands

Separate command names from handlers. A map can hold functions under the names that users type. This sample uses simple numeric handlers so the lookup behavior stays visible.

1. Trim surrounding spaces and lowercase each command name. Reject blank or repeated names. A repeated name leaves the first handler installed.
2. Look up the chosen handler and pass it the argument. A missing handler gives a specific error.
3. The sample prints `Double: 14` and `Next: 8`. Add a command and explain how the dispatcher can run it without adding another branch.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
