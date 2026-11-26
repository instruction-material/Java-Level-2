# Bank Account

Build a classroom bank that keeps fictional accounts in a map. Use integer cents throughout. A balance of 250 means 2.50.

1. Open each DEMO account once. Reject a duplicate ID, an invalid ID, or a negative opening amount. A missing lookup returns null.
2. Deposit only positive amounts that fit in a long. Withdraw only a positive amount at or below the current balance. Decide whether the request is allowed before changing the balance.
3. Trace the supplied deposit, overdraft, and corrected withdrawal. The final lines include `Overdraft accepted: false`, `DEMO-A: 250 cents`, and `DEMO-B: 50 cents`. Compare the two accounts after the rejected operation.

The exercise uses fictional IDs and amounts. The program accepts no names or login data.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror -d out src/main/java/*.java
java -cp out Main
```
