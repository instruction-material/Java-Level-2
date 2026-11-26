package vehicles;

public class ContractTests {
    public static void main(String[] args) {
        Car car = new Car("Camry", 2019, 12.5, 4);
        Motorcycle motorcycle = new Motorcycle("Sportster", 2020, 4, false);
        Vehicle[] fleet = {car, motorcycle};
        check(fleet[0].details().equals("2019 Camry, wheels 4, miles 12.5, doors 4"),
            "car details through vehicle reference");
        check(fleet[1].details().equals("2020 Sportster, wheels 2, miles 4.0, sidecar false"),
            "motorcycle details through vehicle reference");
        LandVehicle roadVehicle = car;
        roadVehicle.addMiles(1.5);
        check(car.getMiles() == 14 && motorcycle.getMiles() == 4, "mileage belongs to one vehicle");
        rejects(() -> roadVehicle.addMiles(-1));
        rejects(() -> roadVehicle.addMiles(Double.NaN));
        rejects(() -> roadVehicle.addMiles(Double.POSITIVE_INFINITY));
        check(car.getMiles() == 14, "rejections leave mileage intact");
        LandVehicle large = new Car("Large reading", 2020, Double.MAX_VALUE, 4);
        rejects(() -> large.addMiles(Double.MAX_VALUE));
        check(large.getMiles() == Double.MAX_VALUE, "overflow leaves mileage intact");
        System.out.println("Vehicle checks passed");
    }

    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }

    private static void rejects(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("invalid mileage accepted");
    }
}
