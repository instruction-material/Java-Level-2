# Dealership Database

Use a local map from vehicle ID to Car. The workbook reuses the Vehicle, LandVehicle, and Car types from the inheritance project. The sample IDs are fictional.

1. Normalize a vehicle ID by trimming spaces and converting letters to uppercase. Reject a blank ID.
2. Use conditional insertion so a duplicate never replaces the original car. A missing lookup or removal returns null.
3. Enter two vehicles out of order and report the inventory by ID. Try the same key with different spaces and case. Confirm that the first car remains.

The driver prints `Duplicate accepted: false`, `Missing VIN: null`, and both vehicle descriptions, starting with `DEMO-01`.

Save and extract the workspace with its source directories and `sources.txt` intact. Use Java 21 from the extracted folder.

```sh
javac --release 21 -Xlint:all -Werror -d out @sources.txt
java -cp out vehicles.Main
java -cp out vehicles.ContractTests
```
