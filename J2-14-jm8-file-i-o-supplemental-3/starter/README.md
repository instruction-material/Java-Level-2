# Compare Two Snapshots

Compare two fictional lists of room names in UTF-8 text files. Each list is a snapshot taken at one point in time.

1. Trim each line and ignore blank rows. Treat repeated names as one entry. Each sorted result set uses complete names.
2. Build additions from the newer set minus the older set. Build removals from the older set minus the newer set. Use copies so the original sets can be compared again.
3. The original set contains Storage and Studio. The newer set contains Studio and Workshop. The program prints `Added: [Workshop]` and `Removed: [Storage]`.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The method checks identify what still needs to be implemented.
