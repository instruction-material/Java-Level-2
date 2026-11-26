# Sensor Reading Recovery

Maintain one latest integer reading per named sensor. A command supplies a sensor name and one reading. Use the supplied valid, malformed and corrected commands to check how a failed parse affects the map.

1. Split one command into a name and a number. Reject blank names, missing readings, and extra fields.
2. Parse the number before writing to the map. Catch NumberFormatException at that boundary. A rejected attempt leaves the last good reading in place.
3. Apply `east 7`, `east broken`, and `east -2`. The driver shows `After rejection: 7` and `After retry: -2`. Confirm that changing east does not change a separate west reading.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs and reports the unfinished behavior. Use the method checks to confirm the result after implementing it.
