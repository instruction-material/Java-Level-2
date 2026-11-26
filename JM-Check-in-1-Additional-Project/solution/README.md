# Card Practice

Each card has rank 1–13 and suit 1–4. The suits sort in this order: clubs, diamonds, hearts, spades. Smaller ranks come first within a suit.

1. Inspect the constructor and the getters. Invalid ranks or suits are rejected before any card is usable.
2. Complete `compareTo`. First use `Integer.compare` on the suit. When the suits tie, compare ranks. Two cards with matching suit and rank return zero.
3. Trace the fixed cards before running the driver. The sorted hand is `[13 of clubs, 1 of hearts, 2 of hearts, 1 of spades]`.

Run `java Main 7` for a reproducible hand of five cards. The preset shuffle draws from a deck with one card per suit and rank, so no card is dealt twice.

Save and export the IDE workspace. Extract the ZIP and use Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```
