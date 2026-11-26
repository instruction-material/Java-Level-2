public class ContractTests {
    public static void main(String[] args) {
        Boba plain = new Boba("brown sugar milk tea", true);
        Boba alsoPlain = new Boba("brown sugar milk tea", true, "");
        Boba pearls = new Boba(new String("brown sugar milk tea"), true, new String("pearls"));
        check("".equals(plain.getTopping()), "an omitted topping uses the empty string");
        check(plain.isSameOrder(alsoPlain), "two topping-free orders can match");
        check(!plain.isSameOrder(pearls), "different toppings distinguish orders");
        plain.addTopping("pearls");
        check(alsoPlain.getTopping().isEmpty(), "adding a topping affects one order");
        check(plain.isSameOrder(pearls) && pearls.isSameOrder(plain), "equal order text compares by value");
        check(!plain.isSameOrder(new Boba("taro milk tea", true, "pearls")), "different flavor");
        check(!plain.isSameOrder(new Boba("brown sugar milk tea", false, "pearls")), "different ice choice");
        check(!plain.isSameOrder(null), "no other order is unequal");
        check("Flavor: brown sugar milk tea, Iced: true, Topping: none".equals(alsoPlain.toString()),
            "the driver shows an absent topping as none");
        System.out.println("Boba class checks passed");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new AssertionError(description);
        }
    }
}
