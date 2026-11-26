public class ContractTests {
    public static void main(String[] args) {
        Person integerGrade = new Person("Jack", 12, 6);
        Person textGrade = new Person("Jack", 12, "6");
        check(textGrade.getGrade() == 6, "the text grade becomes a numeric grade");
        check(integerGrade.toString().equals(textGrade.toString()), "equivalent overloads store equivalent state");
        check("Name: Tiffany, Grade: 0, Age: 10".equals(new Person("Tiffany", 10).toString()),
            "omitting grade uses zero");
        check("Name: Unknown, Grade: 0, Age: 0".equals(new Person().toString()), "explicit no-arg defaults");
        textGrade.hasBirthday();
        check(textGrade.getAge() == 13 && integerGrade.getAge() == 12, "one birthday changes one object");
        rejects(() -> new Person("Jack", -1, 6));
        rejects(() -> new Person("Jack", 12, -1));
        rejects(() -> new Person("Jack", 12, "-1"));
        rejects(() -> new Person("Jack", 12, "six"));
        System.out.println("Person class checks passed");
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
        throw new AssertionError("invalid age or grade was accepted");
    }
}
