# Lesson and DanceLesson

Every lesson sorts by duration. Dance lessons also have a difficulty rating and a dance style. Difficulty is a separate comparison for dance lessons.

1. Pass common fields to Lesson with `super`. Append the style and difficulty to the inherited display.
2. Complete `easierThan` by comparing two difficulty ratings. Equal ratings leave neither lesson easier. A missing comparison lesson gives false.
3. Sort the mixed array of Lessons. Predict whether a longer dance lesson can be easier than a shorter dance lesson and still appear later in the duration order.

Save the IDE workspace, download its ZIP, and extract the folder. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its checks show which behavior still needs to be implemented.
