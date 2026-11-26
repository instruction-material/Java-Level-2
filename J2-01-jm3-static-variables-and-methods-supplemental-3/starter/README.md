# Static Scoring Practice

Score an even integer by halving it. Score an odd integer by doubling it. The rules include zero and negative values.

1. Use the static constants in `scoreOf(int)`. In the odd branch, cast to `long` before multiplying.
2. Trace the supplied list as contributions `1 + 10 + 4 + 26`. The completed driver prints `41`.
3. Explain why `computeScore` starts a new local total on every call. Try the same list twice and an empty list in between, expecting `41`, `0`, and `41`.

Compare a shared constant with a local accumulator. The rule can be shared while each call starts with its own result.

Save the IDE workspace, download its ZIP, and extract it. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its behavior checks identify the first unfinished requirement.
