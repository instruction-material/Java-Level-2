# Attendance by Room

Store a set of attending names for each room. The map keeps rooms separate. The set ignores repeated entries in the same room.

1. Use `computeIfAbsent` to create the first set for a room. Add the name to that set and return whether the attendance changed.
2. Read a missing room as count zero. Reading it leaves the map unchanged.
3. Enter Ada twice in Lab A and once in Lab B. The duplicate in Lab A returns false and each room has an independent count.

Save the IDE workspace, download its ZIP, and extract the folder. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its checks show which behavior still needs to be implemented.
