class ContractTests {
    public static void main(String[] args) {
        var book = new Spending();
        check(book.charge("supplies", 175), "record a charge");
        var before = book.snapshot();
        check(book.charge(" supplies ", 25), "same trimmed category");
        check(book.charge("travel", 50) && !book.charge("travel", -1), "separate categories");
        check(book.snapshot().toString().equals("{supplies=200, travel=50}"), "ordered amounts");
        check(before.get("supplies") == 175 && !before.containsKey("travel"), "snapshot is separate");
        check(!book.charge(" ", 7) && !book.charge("travel", Long.MAX_VALUE), "reject blank and overflow");
        check(book.snapshot().get("travel") == 50, "preserve rejected category");
        System.out.println("Spending checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}
