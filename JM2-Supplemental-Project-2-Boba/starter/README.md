# Boba Shop

Use one value for a missing topping. The two-argument constructor delegates to the full constructor with an empty string. The display renders that value as `none`.

1. Complete the short constructor and the topping setter. Compare two orders using flavor, iced status, and topping. Compare text by value, using exact case for flavor and topping. Return false if there is no other order.
2. Verify two drinks without toppings, then add pearls to only one. Predict how each comparison changes. The other order's topping stays empty.
3. Run the driver with a flavor, `Y` or `N`, and a topping on separate lines. Enter a blank third line for no topping.

Use Java 21 in the extracted workspace.

```sh
javac --release 21 -Xlint:all -Werror *.java
java ContractTests
java Main
```

Enter `brown sugar milk tea`, `Y`, and `pearls`. The completed driver prints:

```text
Flavor: brown sugar milk tea, Iced: true, Topping: pearls
Matches the most popular order.
```

A short input ends with `Order incomplete.`. The driver handles input and output, while the model stores and compares orders.
