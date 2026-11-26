# Spending Categories

Group fictional charges by category and produce an alphabetically ordered spending summary. Keep the category totals in integer cents.

1. Trim surrounding spaces from category names. Reject a blank name, a nonpositive charge, or a total that would overflow.
2. Add each valid charge to its category. A rejected charge leaves the current totals unchanged. Return a copy for each report.
3. Record supplies at 175 cents and again at 25 cents, then travel at 50 cents. The completed report is `Summary: {supplies=200, travel=50}`. Compare a snapshot before and after another valid charge.

Save the IDE workspace, download and extract its ZIP, and use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter runs with unfinished behavior. The method checks identify what still needs to be implemented.
