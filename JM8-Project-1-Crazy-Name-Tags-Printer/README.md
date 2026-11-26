# Crazy Name Tags Printer

Use the fictional label Zoë to produce three lines in one UTF-8 file. The output shows an uppercase version, every other character starting with the first, and the reverse.

1. Trim surrounding spaces and reject an empty label before writing. Apply uppercase with Locale.ROOT.
2. Count positions by Unicode code point. With Zoë, selecting positions 0 and 2 yields Zë. Reversing yields ëoZ.
3. From the temporary file, verify `Tags: [ZOË, Zë, ëoZ]`. Try `java Main 'A🌼B'` to check that selection and reversal keep the flower intact.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror -d out src/main/java/*.java
java -cp out Main
```
