public class Main {
    public static void main(String[] args) {
        Prices prices = new Prices();
        prices.change("bread", 375);
        prices.change("apple", 99);
        System.out.println("Bread: " + prices.priceFor("bread", 0));
        System.out.println("Missing: " + prices.priceFor("milk", 0));
        System.out.println("Bread already present: " + !prices.addNew("bread", 799));
        prices.change("bread", 400);
        prices.addNew("banana", 79);
        prices.remove("apple");
        System.out.println("Inventory: " + prices.inventory());
    }
}
