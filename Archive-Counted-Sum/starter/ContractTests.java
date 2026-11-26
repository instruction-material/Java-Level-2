import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        check(CountedSum.sum(List.of("3","12","-7","4")) == 9, "sample");
        check(CountedSum.sum(List.of("0")) == 0, "empty batch");
        check(CountedSum.sum(List.of("2", "2147483647", "2147483647")) == 4294967294L, "wide result");
        for (var invalid : List.of(List.of("1"), List.of("0","7"), List.of("-1"), List.of("1","bad"))) {
            try {
                CountedSum.sum(invalid);
                throw new AssertionError("invalid batch accepted");
            } catch (IllegalArgumentException expected) {
                check(!expected.getMessage().isEmpty(), "meaningful error");
            }
        }
        System.out.println("Checks passed.");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}
