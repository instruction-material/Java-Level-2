# Writing to a File

Write text rows in UTF-8. Replace starts a new file or discards the old contents. Append adds a row while preserving the rows already stored.

1. Use the supplied temporary folder and write the two lines A and Café. Use Files.write with an explicit UTF-8 charset.
2. Append End with CREATE and APPEND, then read back the lines. Expect `After append: [A, Café, End]`.
3. Replace the file with New and expect `After replace: [New]`. Compare the old and new line counts to verify the operation.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror -d out src/main/java/*.java
java -cp out Main
```
