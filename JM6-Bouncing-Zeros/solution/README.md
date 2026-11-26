# Bouncing Zeros

Animate a zero across one row using a single worker. The worker owns its position and direction. Main starts the worker and joins it before announcing completion.

1. Start at column zero, moving right. Print the current frame before moving. Reverse direction at either endpoint, with no repeated edge frame.
2. Stop after the configured frame count. If a pause is interrupted, restore the thread's interrupted status and exit. The console pause changes the display pace.
3. Trace seven frames with a width of four. The positions are 0, 1, 2, 3, 2, 1, 0. The two middle frames around the right edge include `...0` and `..0.`. The final line is `Animation complete.`. The checks also interrupt a worker during its first frame without using elapsed time as the correctness check.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its method checks pass after the missing behavior is implemented.
