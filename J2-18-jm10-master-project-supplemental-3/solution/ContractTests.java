class ContractTests {
    public static void main(String[] args) throws Exception {
        var commands = new Commands();
        check(commands.register("double", input -> input * 2), "add a valid handler");
        check(commands.run(" Double ", 7) == 14, "run the selected handler");
        check(!commands.register("DOUBLE", input -> 0) && commands.run("double", 4) == 8, "duplicate keeps original");
        check(!commands.register(" ", input -> 0), "reject blank command");
        try {
            commands.run("missing", 9);
            throw new AssertionError("ran a missing command");
        } catch (IllegalArgumentException rejected) {
            check(rejected.getMessage().equals("Unknown command."), "use a specific diagnostic");
        }
        System.out.println("Command dispatch checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}
