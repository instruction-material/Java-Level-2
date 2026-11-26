# All-or-Nothing Reservations

Use one map for available units and another for requested units. A reservation succeeds only when every requested item exists and has enough stock.

1. Check each requested quantity before changing the stock map. Reject unknown keys, nonpositive requests, and requests above the available units.
2. After the whole request passes validation, subtract the requested units. A rejected request leaves every count unchanged.
3. Reserve two pens and one folder. Next, request one pen together with too many folders. Confirm that the second request leaves both counts unchanged.

Save the IDE workspace, download its ZIP, and extract the folder. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its checks show which behavior still needs to be implemented.
