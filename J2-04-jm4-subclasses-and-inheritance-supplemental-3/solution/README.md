# Delivery Fees and Address Composition

A priority delivery is a kind of delivery with a different fee. A delivery has a destination address. Keep the address as a field rather than making it a parent class.

1. Trace how both delivery types send the address to Delivery. The receipt uses the destination and calls `feeCents()` on the actual delivery object.
2. Complete PriorityDelivery so it adds a fixed surcharge of 300 cents to the base fee. Keep prices in integer cents.
3. Predict what happens when two delivery types share one Address and the driver treats them both as Delivery.

Save the IDE workspace, download its ZIP, and extract the folder. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its checks show which behavior still needs to be implemented.
