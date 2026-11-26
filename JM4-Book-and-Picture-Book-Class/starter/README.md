# Book and PictureBook

Title and author identify a work in both classes. Compare the illustrator separately when the question is whether two picture books are the same illustrated edition.

1. Call the parent constructor with title, author, and bookmark. Keep those fields in Book. Implement `sameIllustration` for the additional edition comparison.
2. Override `toString()` and reuse `super.toString()` to display the parent fields before adding the illustrator.
3. Trace a `Book` reference pointing to a PictureBook. Its display includes the illustrator. Its inherited `isSameBook` comparison still uses title and author.

The sample compares two editions of The Lantern. The work comparison is true and the illustrated edition comparison is false.

Save the IDE workspace, download its ZIP, and extract the folder. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its checks show which behavior still needs to be implemented.
