class ContractTests {
    public static void main(String[] args) {
        int[] values = {4, 9, 15};
        check(Recovery.divide(12, 3).equals("Quotient: 4"), "ordinary division");
        check(Recovery.divide(12, 0).equals("Cannot divide by zero."), "division recovery");
        check(Recovery.divide(12, 2).equals("Quotient: 6"), "division retry");
        check(Recovery.lookup(values, 1).equals("Value: 9"), "ordinary indexing");
        check(Recovery.lookup(values, -1).equals("Index outside array."), "negative index");
        check(Recovery.lookup(values, 3).equals("Index outside array."), "upper index");
        check(Recovery.lookup(values, 2).equals("Value: 15"), "index retry");
        check(java.util.Arrays.equals(values, new int[] {4, 9, 15}), "array unchanged");
        System.out.println("Exception recovery checks passed");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
