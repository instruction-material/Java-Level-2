public class ContractTests {
    public static void main(String[] args) {
        Rectangle wide = new Rectangle(3, 5);
        Rectangle equalSides = new Square(4);
        check(wide.area() == 15 && wide.perimeter() == 16, "rectangle uses both dimensions");
        check(equalSides.area() == 16 && equalSides.perimeter() == 16, "square uses its side twice");
        check(equalSides.shapeName().equals("Square"), "parent reference dispatches to Square");
        check(new Square(Integer.MAX_VALUE).perimeter() == 8589934588L, "widen before adding dimensions");
        rejects(() -> new Rectangle(0, 1));
        rejects(() -> new Square(-1));
        System.out.println("Rectangle checks passed");
    }

    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }

    private static void rejects(Runnable operation) {
        try {
            operation.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("nonpositive dimension accepted");
    }
}
