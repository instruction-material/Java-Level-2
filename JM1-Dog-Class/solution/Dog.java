public final class Dog {
    private final String name;
    private int age;
    private final double weight;
    private final boolean alive;

    public Dog(String name, int age, double weight, boolean alive) {
        this.name = name;
        setAge(age);
        this.weight = weight;
        this.alive = alive;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be nonnegative");
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age
            + ", Weight: " + weight + ", Alive: " + alive;
    }
}
