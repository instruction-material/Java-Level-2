# Lending Library

Use a map from book title to author. A title lookup retrieves one author. An author search may find several titles.

1. Use case-insensitive title lookups and trim spaces at input. When searching by author, compare author values and report the matching title keys in alphabetical order.
2. Print the stock list in title order. Keep missing titles distinct from existing titles.
3. Run the driver with a known title, then try the author search. Type a menu choice and a value on separate lines. Use `1` for title, `2` for author, or `3` for all entries. Invalid or incomplete input produces a message rather than an exception.

For input `2` and `Dr. Seuss`, expect `Green Eggs and Ham` and `The Cat in the Hat`.

Save the IDE workspace, download its ZIP, and extract the folder. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its checks show which behavior still needs to be implemented.
