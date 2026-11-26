# File I/O and Maps

Load alternating lines from a UTF-8 file. Lines 1 and 2 form the first key and value, lines 3 and 4 the second pair, and so on.

1. Trim keys and values. Reject blank entries, an unpaired final key, and duplicate keys. Identify a failing record by its line number without printing the record itself.
2. Keep input order with a LinkedHashMap. Return the loaded table only after every pair passes. A rejected file never replaces a prior result.
3. Read the supplied input.txt and verify `Pairs: {pen=blue, folder=green}`. Compare an empty file, a two-pair file, and a repeated key.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror -d out src/main/java/*.java
java -cp out Main
```
