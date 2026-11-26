# Review Capstone Checks

Turn a project’s stated behaviors into an ordered list of checks. A check has a short label and either passes or stays open.

1. Record at most one current result per label. Reject a blank label and trim surrounding spaces.
2. Sort the open labels without changing the stored results. Updating a check from open to passed removes it from the report.
3. In the sample, the win and retry checks pass. The report is `Open checks: [restart, tie]`. Replace those labels with checks from an original capstone and explain what evidence would close each one.

Save the IDE workspace, download and extract its ZIP, and run the workbook with Java 21 from that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The checks identify which contract still needs to be implemented.
