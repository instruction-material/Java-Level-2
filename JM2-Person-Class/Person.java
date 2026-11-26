public class Person {
    private String name;
    private int age;
    private int grade;

    public Person(String n, int a, int gr) {
        name = n;
        age = a;
        grade = gr;
    }

    // overloaded constructors
    public Person(String n, int a, String g) {
        this(n, a, Integer.parseInt(g));
    }

    // OPTIONAL: Overloaded constructor using 'this' keyword to call the main constructor
    public Person(String n, int a) {
        this(n, a, 0); // Calls the main constructor and sets grade to 0
    }

    public Person() {
        this("Unknown", 0);
    }

    public int getAge() {
        return age;
    }

    public void hasBirthday() {
        age++;
    }

    public String toString() {
        return "Name: " + name + ", Grade: " + grade + ", Age: " + age;
    }
}
