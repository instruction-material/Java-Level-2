public final class Car implements Comparable<Car> {
    private static int numCars;

    private final String type;
    private final String color;
    private int mileage;

    public Car(String type, String color, int mileage) {
        requireNonnegative(mileage);
        this.type = type;
        this.color = color;
        this.mileage = mileage;
        numCars++;
    }

    public Car(String type, String color) {
        this(type, color, 0);
    }

    public static int getNumCars() {
        return numCars;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public int getMileage() {
        return mileage;
    }

    public void drive(int miles) {
        requireNonnegative(miles);
        mileage = Math.addExact(mileage, miles);
    }

    public void driveAll(int... distances) {
        int nextMileage = mileage;
        for (int distance : distances) {
            requireNonnegative(distance);
            nextMileage = Math.addExact(nextMileage, distance);
        }
        mileage = nextMileage;
    }

    private static void requireNonnegative(int miles) {
        if (miles < 0) throw new IllegalArgumentException("Mileage must be nonnegative");
    }

    public boolean sameMileage(Car other) {
        return other != null && mileage == other.mileage;
    }

    @Override
    public int compareTo(Car other) {
        return Integer.compare(mileage, other.mileage);
    }

    @Override
    public String toString() {
        return "A " + color + " " + type + " that has driven " + mileage + " miles.";
    }
}
