public final class Square extends Rectangle {
    public Square(int side) {
        super(side, side);
    }

    @Override
    public String shapeName() {
        return "Square";
    }
}
