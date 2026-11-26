# Score a Complete Quiz Round

The answer key and responses are in question order. Compare a response with the key at the same position. Ignore surrounding spaces and differences in letter case. Each exact normalized match earns one point. A blank response counts as incorrect. Reject an incomplete or oversized response list so a partial round never receives a score.

Implement QuizRound.score. The supplied answers are Saturn, oxygen and Pacific. Explain which two sample responses match. Check how a blank response differs from a missing response.

The supplied example prints `Correct: 2/3`.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
