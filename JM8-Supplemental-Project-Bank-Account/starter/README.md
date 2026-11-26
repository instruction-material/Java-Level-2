# Bank Account with File I/O

Save and reopen a fictional balance snapshot. The file starts with BALANCES-1, followed by one DEMO ID and a balance in integer cents on each comma-separated row.

1. Save from the bank’s snapshot in ID order. Write the complete UTF-8 file to a temporary sibling, then replace the destination atomically. An unsupported atomic move is reported as an I/O error.
2. Load and validate every row into a fresh bank, then return it. Reject an unknown format, duplicate ID or negative amount with a line number. A missing file starts an empty bank.
3. Save the supplied two accounts, update the first, and save again. Reopen the file in a new bank. The final report is `Reopened: {DEMO-A=275, DEMO-B=50}`. This format contains only fictional IDs and amounts.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The method checks identify what still needs to be implemented.
