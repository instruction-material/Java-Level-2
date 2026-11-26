public abstract class Pet {
    private final String name;

    protected Pet(String name) {
        this.name = name;
    }

    public abstract String sound();

    public String introduce() {
        return name + " says " + sound();
    }
}
