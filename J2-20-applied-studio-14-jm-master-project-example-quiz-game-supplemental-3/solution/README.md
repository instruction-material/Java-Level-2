# Build a Quiz Practice Set

Choose questions from a named topic for a repeatable practice set. Keep the original order and count a repeated prompt once.

1. Walk the supplied question list. Keep only cards matching the chosen topic and preserve the first occurrence of each prompt.
2. Stop when the set reaches the positive limit. A topic with fewer questions returns all available prompts. A missing topic returns an empty list.
3. The sample asks for two Science prompts and prints `Practice: [What orbits Earth?, What is a solid?]`. Try a limit of one and compare with the same question list.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
