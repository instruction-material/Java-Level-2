# Account Transfers

Move fictional cents between two accounts. A transfer succeeds only when the source can pay and the destination can receive the full amount.

1. Reject a missing account, a transfer to the same account, an invalid amount, and destination overflow. Check both sides before changing either balance.
2. Withdraw and deposit the same amount after validation. The transfer runs on one thread, so the checks and updates occur in order.
3. Move 30 cents from A to B, then try moving 90 cents out of the remaining 70. The failed attempt leaves `Balances: {DEMO-A=70, DEMO-B=80}`. Show that the two balances still add to 150.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The method checks identify what still needs to be implemented.
