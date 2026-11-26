# Reading from a File

Read one signed integer per line from the supplied UTF-8 file numbers.txt. A blank or nonnumeric row is a format error. An empty file has zero total.

1. Open a buffered reader with try-with-resources. Number lines from 1, trim each row, and parse it. Report the line number if parsing fails.
2. Add each parsed value to a long total. Check the file containing 4, −2, and 13. The sample prints `Total: 15`.
3. Compare a missing file, an empty file, and a file with an invalid row. A missing file gives a missing-file message. An invalid row gives its line number and leaves no partial total to report.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The method checks identify what still needs to be implemented.
