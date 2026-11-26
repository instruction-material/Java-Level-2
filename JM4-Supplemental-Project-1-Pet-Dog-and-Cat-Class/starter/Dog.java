public final class Dog extends Pet {
    private final boolean serviceDog;

    public Dog(String name, boolean serviceDog) {
        super(name);
        this.serviceDog = serviceDog;
    }

    @Override
    public String sound() {
        return "Bark";
    }

    public boolean isServiceDog() {
        return serviceDog;
    }
}
