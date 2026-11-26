# House Class

Each house has an address, an integer price in dollars, and a positive area in square feet. The class counts successful constructions.

1. Increment the shared counter after constructor validation.
2. In `compareTo`, use `Integer.compare` for price first. When prices are equal, compare square footage. Sort from smallest to largest on each key.
3. Predict the order before calling `Arrays.sort`. Two houses at the same price and area tie. They remain separate objects even if both sorting keys match.

Expected driver output:

```text
Elm Lane | $250000 | 2000 sq ft
Cedar Lane | $500000 | 1200 sq ft
Oak Lane | $500000 | 1800 sq ft
Houses created: 3
```

Save the IDE workspace, download its ZIP, and extract it. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its behavior checks identify the first unfinished requirement.
