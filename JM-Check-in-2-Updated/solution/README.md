# Roman Clock Check-In

Trace a 12-hour clock and a 24-hour clock, then use a subclass to display a clock hour with Roman numerals. The supplied array gives the symbols, including VIII for eight.

1. Read the HourClock constructor and tick method. Predict what follows 12 on a 12-hour clock and 23 on a 24-hour clock. Explain why the RomanClock constructor calls `super(false, hour)`.
2. Implement `numeralForHour()` and the `displayTime()` override in RomanClock. A variable declared as HourClock can hold a RomanClock. Calling displayTime on it still uses the Roman display.
3. In `updateMap()`, pair each Roman symbol with its decimal value using the array index. Store L as 50 if it is absent. Return a read-only snapshot in insertion order, so a second call retains the same values and order.
4. Trace the rejected request to set hour 13 on the 12-hour clock. Main catches the specific IllegalArgumentException, proves that the previous hour remains, then retries with 9.

The sample includes `Roman Time: VIII`, `VIII maps to: 8`, `Still at: 8`, and `Roman Time: IX`. The finite required run uses direct method calls.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs and reports the unfinished behavior. Use the method checks to confirm the result after implementing it.
