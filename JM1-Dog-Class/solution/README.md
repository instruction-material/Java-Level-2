# Dog Class

Model two dogs with independent ages, then demonstrate that calling a method on one object changes only that object.

1. Keep the instance fields private. The constructor receives a name, age, weight, and alive status. The type of weight is `double`, so `15.5` is a valid argument.
2. Return the current instance age from `getAge()`. Implement `setAge(int)` so it throws `IllegalArgumentException` for a negative age before assigning the field. Accept zero.
3. Use the setter in the constructor to enforce the same age rule.
4. Return a string from `toString()` in the exact format shown below.

Save and export the workspace. From the extracted folder, use Java 21.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

Expected output from the completed driver:

```text
4
Name: Fido, Age: 5, Weight: 15.5, Alive: true
2
```

The starter compiles and demonstrates the incomplete methods. Complete the three methods and constructor validation before expecting the checks to pass. A check throws `AssertionError` with a description of the first incorrect observation.
