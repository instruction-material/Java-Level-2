class ContractTests {
    public static void main(String[] args) throws Exception {
        var fido = new Dog("Fido", 5);
        var luna = new Dog("Luna", 5);
        fido.birthday();
        check(fido.age() == 6, "sample");
        check(luna.age() == 5, "independent state");
        check(fido.toString().equals("Fido (6)"), "visible state");
        var oldest = new Dog("Sage", Integer.MAX_VALUE);
        try {
            oldest.birthday();
            throw new AssertionError("overflow accepted");
        } catch (ArithmeticException expected) {
            check(oldest.age() == Integer.MAX_VALUE, "overflow preserves state");
        }
        System.out.println("Checks passed.");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}
