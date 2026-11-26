# Barnyard Orchestra

Create a small orchestra with three independent workers. Each worker records two occurrences of one sound. Its optional pause separates the two events.

1. Enter three lines with a known animal and an integer delay from 0 to 1,000 milliseconds, for example `cow 0`, `pig 0`, and `frog 0`. Main validates all three lines before starting workers and handles incomplete input.
2. Implement the two sound events in AnimalPlayer. After an interrupted delay, restore the interrupted status and end the worker without emitting any further sounds.
3. Each worker records into a separate list. Main joins all three workers before printing their individual lists in input order. In the example, the first line is `cow: [moo, moo]`. Workers can complete in any order.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its method checks pass after the missing behavior is implemented.
