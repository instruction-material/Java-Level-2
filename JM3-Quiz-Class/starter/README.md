# Quiz Class

Use one shared counter for successful quiz constructions. Keep each quiz's name, score, and questions on its own instance.

1. Follow the no-argument constructor through its overloads to the three-argument constructor. That constructor validates the data and assigns it.
2. Increment `numQuizzes` once at the end of the three-argument constructor. Read it with `Quiz.getNumQuizzes()`. A rejected score leaves the count unchanged.
3. Give `toString()` a deterministic summary. Print the first question when the list contains one and `(none)` when the list is empty. The constructor copies the array, so editing the input array afterward leaves the quiz unchanged.

Expected driver output:

```text
Review: 92 (A), first question: Which values belong to an instance?
Practice: 80 (B), first question: (none)
Quizzes created: 2
```

Save the IDE workspace, download its ZIP, and extract it. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its behavior checks identify the first unfinished requirement.
