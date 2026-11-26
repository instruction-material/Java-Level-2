# Release or Cancel a Worker

Use two CountDownLatch objects as one-time signals. The first lets the caller know a worker is ready. The second lets the caller release that worker. A deadline keeps the wait bounded.

1. Signal readiness, then wait for release with a time limit. Record `Released` when the release arrives or `Timed out` when the deadline passes first.
2. Catch InterruptedException, record `Cancelled`, and restore the current thread's interrupted status before returning. The caller joins the worker before reading its outcome.
3. Run Main to release the first worker and cancel the second. The resulting lines are `First job: Released` and `Second job: Cancelled`. Predict what the outcome would be if the caller issued neither signal and kept the release closed.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its method checks pass after the missing behavior is implemented.
