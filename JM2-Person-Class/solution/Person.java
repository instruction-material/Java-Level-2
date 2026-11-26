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
        this(name, age, Integer.parseInt(grade));
    }

    public Person(String name, int age) {
        this(name, age, 0);
    }

    public Person() {
        this("Unknown", 0);
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
