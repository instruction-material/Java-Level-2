public class Rectangle {
    private final int width;
    private final int length;

    public Rectangle(int width, int length) {
        if (width <= 0 || length <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        this.width = width;
        this.length = length;
    }

    public long area() {
        return (long) width * length;
    }

    public long perimeter() {
        return 2L * ((long) width + length);
    }

    public String shapeName() {
        return "Rectangle";
    }

    @Override
    public String toString() {
        return shapeName() + ": area " + area() + ", perimeter " + perimeter();
    }
}
