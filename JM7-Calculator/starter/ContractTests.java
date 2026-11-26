class ContractTests {
    public static void main(String[] args) {
        if (Calculator.factorial(5) != 120 || Calculator.factorial(0) != 1) {
            throw new AssertionError("factorial includes every factor");
        }
        check(Calculator.factorial(20) == 2432902008176640000L, "largest factorial");
        check(Calculator.arithmetic("power", 2, 4) == 16, "power");
        check(Calculator.arithmetic("remainder", 9, 4) == 1, "remainder");
        reject(() -> Calculator.arithmetic("divide", 7, 0));
        reject(() -> Calculator.arithmetic("multiply", Double.MAX_VALUE, 2));
        reject(() -> Calculator.arithmetic("add", Double.NaN, 2));
        reject(() -> Calculator.factorial(21));
        System.out.println("Calculator checks passed");
    }

    private static void reject(Runnable operation) {
        try {
            operation.run();
            throw new AssertionError("Accepted an invalid operation");
        } catch (IllegalArgumentException expected) {
            check(!expected.getMessage().isBlank(), "specific error");
        }
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}
