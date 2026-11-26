import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Car sedan = new Car("sedan", "red");
        Car coupe = new Car("coupe", "yellow", 1500);
        Car sportsCar = new Car("sports car", "blue", 60000);
        sedan.drive(20000);
        Car[] cars = {sedan, sportsCar, coupe};
        Arrays.sort(cars);
        for (Car car : cars) {
            System.out.println(car);
        }
        System.out.println("Cars created: " + Car.getNumCars());
    }
}
