public class ContractTests {
    public static void main(String[] args) {
        Employee jacky = new Employee("Jacky", "Intern", 1, 556614);
        Employee tina = new Employee("Tina", "Manager", 3, 76544);
        check("Jacky".equals(jacky.getName()) && jacky.getIdNumber() == 556614,
            "constructor stores name and ID");
        check(tina.getYearsWorked() == 3 && tina.getIdNumber() == 76544,
            "second employee has the intended decimal ID and years");
        jacky.setPosition("Engineer");
        tina.setYearsWorked(4);
        check("Engineer".equals(jacky.getPosition()), "position changes on Jacky");
        check("Manager".equals(tina.getPosition()), "Tina's title stays the same");
        check(jacky.getYearsWorked() == 1, "Jacky's years stay the same");
        check(tina.getYearsWorked() == 4, "years change on Tina");
        check("Name: Tina, Position: Manager, Years worked: 4, ID Number: 76544"
            .equals(tina.toString()), "summary combines unchanged and updated fields");
        rejects(() -> tina.setYearsWorked(-1));
        check(tina.getYearsWorked() == 4, "invalid update preserves her years");
        rejects(() -> new Employee("Tina", "Manager", -1, 76544));
        tina.setYearsWorked(0);
        check(tina.getYearsWorked() == 0, "zero is allowed");
        System.out.println("Employee class checks passed");
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
        throw new AssertionError("negative years should throw IllegalArgumentException");
    }
}
