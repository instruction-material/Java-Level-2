import java.util.Arrays;

public class ContractTests {
    public static void main(String[] args) {
        if (args.length == 0) {
            core();
            System.out.println("Car checks passed");
        } else if (args.length == 1 && args[0].equals("extension")) {
            extension();
            System.out.println("Car extension checks passed");
        } else {
            throw new IllegalArgumentException("Use no argument or extension");
        }
    }

    private static void core() {
        check(Car.getNumCars() == 0, "fresh program starts at zero");
        Car sedan = new Car("sedan", "red");
        Car coupe = new Car("coupe", "yellow", 1500);
        Car sports = new Car("sports car", "blue", 60000);
        check(Car.getNumCars() == 3, "each construction counts once");
        check(sedan.getType().equals("sedan") && sedan.getColor().equals("red"), "getters report the assigned fields");
        check(sedan.getMileage() == 0, "the shorter constructor uses mileage zero");
        sedan.drive(1500);
        check(sedan.sameMileage(coupe) && !sedan.sameMileage(null), "read two matching mileages safely");
        check(sedan.compareTo(coupe) == 0, "equal mileage ties without merging cars");
        sedan.drive(18500);
        check(coupe.getMileage() == 1500, "driving one car changes only that car");
        sedan.drive(0);
        rejects(() -> sedan.drive(-1), IllegalArgumentException.class);
        rejects(() -> sedan.drive(Integer.MAX_VALUE), ArithmeticException.class);
        rejects(() -> new Car("invalid", "red", -1), IllegalArgumentException.class);
        check(Car.getNumCars() == 3 && sedan.getMileage() == 20000, "rejections preserve count and state");
        Car[] cars = {sedan, sports, coupe};
        Arrays.sort(cars);
        check(Arrays.equals(cars, new Car[]{coupe, sedan, sports}), "cars sort by increasing mileage");
        check("A red sedan that has driven 20000 miles.".equals(sedan.toString()), "summary includes the current state");
    }

    private static void extension() {
        Car sedan = new Car("sedan", "red", 10);
        sedan.driveAll(3, 0, 7);
        check(sedan.getMileage() == 20, "apply all valid distances");
        sedan.driveAll();
        check(sedan.getMileage() == 20, "empty batch leaves mileage alone");
        rejects(() -> sedan.driveAll(4, -1, 5), IllegalArgumentException.class);
        check(sedan.getMileage() == 20, "negative distance leaves all state unchanged");
        rejects(() -> sedan.driveAll(4, Integer.MAX_VALUE), ArithmeticException.class);
        check(sedan.getMileage() == 20, "overflow leaves all state unchanged");
    }

    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }

    private static void rejects(Runnable action, Class<? extends RuntimeException> type) {
        try {
            action.run();
        } catch (RuntimeException error) {
            check(type.isInstance(error), "invalid mileage produces the expected exception");
            return;
        }
        throw new AssertionError("invalid mileage accepted");
    }
}
