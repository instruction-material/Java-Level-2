# Ticket Redemption

A ticket has a unique number and an unused or redeemed state. The class counts tickets issued and tickets redeemed.

1. Each new ticket receives the next number. Issuing a ticket increases only the issued count.
2. Complete `redeem()`. On the first call, set the receiving ticket's state to redeemed, add one to the shared redeemed count, and return true. On later calls for that ticket, return false. The repeat leaves all state unchanged.
3. Trace the driver and expect both ticket numbers, one successful redemption, a failed repeat, and `Issued: 2, redeemed: 1`. Explain why the other ticket remains usable.

Save and export the IDE workspace. Extract the ZIP and use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The unfinished starter compiles. The checks identify the first difference from the assignment requirements.
