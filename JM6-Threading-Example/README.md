# Threading Example

Main calls start to run the Printer on a worker. The worker displays the same
message three times, with a pause between messages. Main joins the worker before
it prints the final confirmation. An interrupted pause stops further printing
and restores the worker's interrupted status.

From src/main/java, use Java 21.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
```

Expect three lines reading `A message from the worker`, followed by
`Printing complete.`.
