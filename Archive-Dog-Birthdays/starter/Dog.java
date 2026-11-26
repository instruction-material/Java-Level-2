final class Dog {
    private final String name;
    private int age;

    Dog(String name, int age) {
        if (age < 0) throw new IllegalArgumentException("Age must be nonnegative.");
        this.name = name;
        this.age = age;
    }

    void birthday() {
        // TODO: Implement the contract described in README.md.
        return;
    }

    int age() {
        return age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}
