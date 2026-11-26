public final class Person {
    private final String name;
    private int age;
    private final int grade;

    public Person(String name, int age, int grade) {
        if (age < 0 || grade < 0) {
            throw new IllegalArgumentException("Age and grade must be nonnegative");
        }
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public Person(String name, int age, String grade) {
        this(name, age, 0); // TODO: Carry the supplied text grade into the canonical constructor.
    }

    public Person(String name, int age) {
        this(name, age, 0);
    }

    public Person() {
        this("", 0); // TODO: Supply the documented default name.
    }

    public int getAge() {
        return age;
    }

    public int getGrade() {
        return grade;
    }

    public void hasBirthday() {
        age = Math.incrementExact(age);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Grade: " + grade + ", Age: " + age;
    }
}
