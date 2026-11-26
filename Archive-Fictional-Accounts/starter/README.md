# Index Fictional Account Aliases

A directory stores one fictional display alias per service. Use supplied names like library and Luna. Trim surrounding spaces and use case-sensitive keys. Reject blank keys, blank aliases and duplicate services. A missing key returns an empty Optional. Real credentials and password storage are outside this exercise.

Implement Directory.add. Key lookup is supplied. Verify that a rejected duplicate leaves the first alias available. Explain which input rule prevents two differently spaced keys from creating duplicate records.

The supplied example prints `Alias: Luna`.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
