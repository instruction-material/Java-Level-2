# Letter Frequencies

Count Unicode letters using a map from code point to count. Normalize each letter using Java's single-code-point lowercase mapping. Ignore other characters.

1. For each input code point, identify whether it is a letter, then update the matching count.
2. Use numeric code-point order for the report. If counts tie for the maximum, keep the first key in that order. Report an empty result when the input has no letters.
3. Enter `B a A b!`. Expect `a: 2`, `b: 2`, and `Most frequent: a (2)`. Then enter `42?` to check the empty case.

Save the IDE workspace, download its ZIP, and extract the folder. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its checks show which behavior still needs to be implemented.
