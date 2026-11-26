# Vehicle Inheritance

Put model and year in Vehicle. Add wheels and mileage in LandVehicle. Car adds a door count, and Motorcycle adds sidecar status.

1. Trace the Car constructor through LandVehicle to Vehicle. Each class supplies its parent's arguments with `super(...)`.
2. Append the subclass fields in each `details` override. Start with `super.details()`. Trace a single call through a Vehicle reference to a Car instance.
3. Use the driver to compare the two results. Updating the car's odometer leaves the motorcycle's odometer alone. Nonfinite or negative additions are rejected before any state changes.

Expected output:

```text
2019 Camry, wheels 4, miles 13.5, doors 4
2020 Sportster, wheels 2, miles 4.0, sidecar false
```

Export the workspace and keep both source folders and `sources.txt` in place. Use Java 21 in the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror -d out @sources.txt
java -cp out vehicles.Main
java -cp out vehicles.ContractTests
```
