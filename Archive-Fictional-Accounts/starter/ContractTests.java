class ContractTests {
    public static void main(String[] args) throws Exception {
        var map = new Directory();
        map.add(" library ", "Luna");
        check("Luna".equals(map.find("library").orElse(null)), "sample");
        check(map.find("absent").isEmpty(), "missing service");
        for (String rejected : new String[] {"  ", "library"}) {
            try {
                map.add(rejected, "Remy");
                throw new AssertionError("invalid key accepted");
            } catch (IllegalArgumentException expected) {
                check("Luna".equals(map.find("library").orElse(null)), "prior entry preserved");
            }
        }
        System.out.println("Checks passed.");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}
