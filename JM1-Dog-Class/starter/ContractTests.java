public class ContractTests {
    public static void main(String[] args) {
        Dog fido = new Dog("Fido", 4, 15.5, true);
        Dog scout = new Dog("Scout", 2, 11, false);
        check(fido.getAge() == 4, "constructor initializes Fido's age");
        check(scout.getAge() == 2, "constructor initializes Scout's age");
        fido.setAge(5);
        check(fido.getAge() == 5, "Fido's setter stores the new age");
        check(scout.getAge() == 2, "updating Fido preserves Scout's age");
        check("Name: Fido, Age: 5, Weight: 15.5, Alive: true".equals(fido.toString()),
            "summary includes fractional weight and alive status");
        check("Name: Scout, Age: 2, Weight: 11.0, Alive: false".equals(scout.toString()),
            "summary includes a false alive status");
        rejects(() -> fido.setAge(-1));
        check(fido.getAge() == 5, "invalid update preserves the original age");
        rejects(() -> new Dog("Fido", -1, 15.5, true));
        scout.setAge(0);
        check(scout.getAge() == 0, "zero is allowed");
        System.out.println("Dog class checks passed");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new AssertionError(description);
        }
    }

    private static void rejects(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("negative age should throw IllegalArgumentException");
    }
}
