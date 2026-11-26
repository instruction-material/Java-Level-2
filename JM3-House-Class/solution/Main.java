import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        House[] listings = {
            new House("Oak Lane", 500000, 1800),
            new House("Cedar Lane", 500000, 1200),
            new House("Elm Lane", 250000, 2000)
        };
        Arrays.sort(listings);
        for (House listing : listings) {
            System.out.println(listing);
        }
        System.out.println("Houses created: " + House.getNumHouses());
    }
}
