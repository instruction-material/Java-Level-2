public final class Cat extends Pet {
    private final int jumpDistance;

    public Cat(String name, int jumpDistance) {
        super(name);
        this.jumpDistance = jumpDistance;
    }

    @Override
    public String sound() {
        return "Meow";
    }

    public int getJumpDistance() {
        return jumpDistance;
    }
}
