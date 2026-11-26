# Optional Clock Playback

Compose the existing 12-hour clock with a separate Runnable. The worker owns its clock. It reports a fixed number of hours, then exits. The required check-in uses direct method calls.

1. In run, report the current hour before ticking. Wait between records only. Exit after the configured count.
2. If sleep throws InterruptedException, restore the interrupt flag and return. Use the separate cancellation check to verify that it records only the first hour when the caller cancels.
3. Start the worker in Main and join it before reading its records. Starting at 11 for four records prints `Hours: [11, 12, 1, 2]`. Change the start hour and predict the complete trace before running again.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs and reports the unfinished behavior. Use the method checks to confirm the result after implementing it.
