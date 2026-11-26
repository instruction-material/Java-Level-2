# Check Dog State After a Birthday

Each Dog owns its own age and name. The constructor rejects a negative age. birthday increases only the receiver's age by one. At the largest int, it reports overflow before changing that age. A formatted name and age let the caller read the result without accessing the fields.

Implement Dog.birthday. Create two dogs with the same starting age. Call birthday on one and trace the two objects separately. Compare a state check with a check of the formatted text.

The supplied example prints `Dog: Fido (6)`.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
