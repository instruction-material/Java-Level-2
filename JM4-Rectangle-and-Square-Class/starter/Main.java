public class Main {
    public static void main(String[] args) {
        Rectangle[] shapes = {new Rectangle(3, 5), new Square(4)};
        for (Rectangle shape : shapes) {
            System.out.println(shape);
        }
    }
}
