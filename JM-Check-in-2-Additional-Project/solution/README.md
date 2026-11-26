# Minute Clock

Reuse HourClock and add a MinuteClock subclass. The subclass owns minutes, while HourClock owns the hour and selects the rollover rule.

1. Keep each minute from 0 to 59. Reject a bad initial minute before reporting a time.
2. Override tick to add one minute. At 59, set the minute to zero and call `super.tick()` exactly once. At any other minute, leave the hour alone.
3. Override displayTime to print two digits for the minute. Predict the two changes in Main from 12:58 to 12:59, then to 1:00. A 24-hour clock moves from 23:59 to 0:00.

The output includes `Time: 12:59`, `Time: 1:00`, and `Time: 0:00`. Compare the transition in both modes with the inherited hour-only behavior.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs and reports the unfinished behavior. Use the method checks to confirm the result after implementing it.
