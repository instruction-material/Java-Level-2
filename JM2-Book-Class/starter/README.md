# Book Class

The title and author identify a book in this exercise. The page number is a bookmark belonging to one instance. Two copies can refer to the same book while holding different bookmarks.

1. Delegate the two-argument constructor to the three-argument constructor with page 0. Require nonnegative pages.
2. In `changePage(int)`, compute the candidate with `Math.addExact`, reject negative results, then store the candidate. A failed change leaves the previous value intact.
3. Compare authors with `String.equals`. Derive `equals` and `hashCode` from title and author. Delegate `isSameBook` to `equals`. Null or a different type is unequal.
4. Compare different titles from one author, then two distinct copies of one title. Explain why a `HashSet` finds the same book after one copy's bookmark moves.

Use Java 21 in the extracted workspace.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

Completed driver output:

```text
Title: Redwall, Author: Brian Jacques, Page Number: 20
Same author: true
Same title and author: true
```
