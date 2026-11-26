# Using a Java Map

Each item name is a key and has one price in integer cents. Inserting the same key again replaces its value without adding another entry.

1. Implement `priceFor` with `getOrDefault`. A missing lookup returns the caller's default and leaves the map unchanged.
2. Implement `addNew` with `putIfAbsent`. Try adding bread a second time and confirm that its first value remains.
3. Trace one replacement, one new value, and one removal. The driver reports prices and size, and uses the map's entries to print its final inventory.

Save the IDE workspace, download its ZIP, and extract the folder. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its checks show which behavior still needs to be implemented.
