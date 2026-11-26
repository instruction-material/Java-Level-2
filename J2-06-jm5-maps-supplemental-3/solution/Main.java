import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Stock stock = new Stock(Map.of("pen", 5, "folder", 3));
        System.out.println("First request: " + stock.reserve(Map.of("pen", 2, "folder", 1)));
        System.out.println("After first: " + stock.inventory());
        System.out.println("Second request: " + stock.reserve(Map.of("pen", 1, "folder", 3)));
        System.out.println("After second: " + stock.inventory());
    }
}
