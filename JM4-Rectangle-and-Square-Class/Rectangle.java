public class Rectangle {
    private final int width;
    private final int length;
    private final String color;

    public Rectangle(int w, int l, String c) {
        if (w <= 0 || l <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        width = w;
        length = l;
        color = c;
    }

    public Rectangle(int w, int l) {
        this(w, l, "unpainted");
    }

    public int getArea() {
        return Math.multiplyExact(width, length);
    }

    public int getPerimeter() {
        return Math.multiplyExact(2, Math.addExact(width, length));
    }

    public int getWidth() {
        return width;
    }

    public String toString() {
        return "Area: " + getArea() + ", Perimeter: " + getPerimeter();
    }
}
