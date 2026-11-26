# Handling Division by Zero

From this folder, compile and run with Java 21.

```sh
mkdir -p out
javac --release 21 -d out src/main/java/Main.java
java -cp out Main
```

The ordinary calculation prints `10 / 2 = 5`. The zero divisor reaches the `ArithmeticException` handler and prints `Cannot divide by zero.` The final line confirms that the program continued after the handler. Add a third divisor and predict which line it prints.
