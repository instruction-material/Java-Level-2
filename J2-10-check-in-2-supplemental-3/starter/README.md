# Station Hour Reports

Convert one shared hour to each station’s local hour using a table of fictional integer offsets. Work in a 24-hour range from 0 to 23. Use the same rule for positive and negative offsets.

1. Validate that the shared hour is within range before creating a report. An out-of-range hour throws IllegalArgumentException.
2. Use `Math.floorMod((long) sharedHour + offset, 24)` to wrap an adjusted hour into the supported range. A TreeMap puts the station names in order. Preserve the supplied offsets so the next report starts from the same table.
3. For Harbor with offset +4 and Ridge with offset −3, hour 23 produces `At 23: {Harbor=3, Ridge=20}`. Hour 1 produces `At 1: {Harbor=5, Ridge=22}`. Explain both crossings of midnight.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs and reports the unfinished behavior. Use the method checks to confirm the result after implementing it.
