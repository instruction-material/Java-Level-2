# Read One Complete Console Line

A console tool sees a stream of lines. One request uses a single whole line. Preserve spaces, allow an empty line, and distinguish end of input from an empty line. Read at most once so the same reader can supply another request.

Implement ConsoleLine.read. Trace the two successive reads in ContractTests. Compare a scripted reader with a reader wrapped around System.in. Both provide the same method contract.

The supplied example prints `Echo: [  red fox  ]`.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
