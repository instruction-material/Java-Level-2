public final class Dog {
    private final String name;
    private int age;
    private final double weight;
    private final boolean alive;

    public Dog(String name, int age, double weight, boolean alive) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.alive = alive;
        // TODO: Reuse the setter so age validation also applies at construction.
    }

    public int getAge() {
        // TODO: Read this dog's age.
        return 0;
    }

    public void setAge(int age) {
        // TODO: Reject negative ages before changing this dog's state.
    }

    @Override
    public String toString() {
        // TODO: Return the four fields in the order shown in README.md.
        return "Dog summary pending";
    }
}
