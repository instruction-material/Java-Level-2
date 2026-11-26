public class Main {
    public static void main(String[] args) {
        PictureBook first = new PictureBook("The Lantern", "A. Reed", 5, "K. Lin");
        PictureBook second = new PictureBook("The Lantern", "A. Reed", 0, "P. Diaz");
        Book work = first;
        System.out.println(work);
        System.out.println("Same work: " + work.isSameBook(second));
        System.out.println("Same illustrated edition: " + first.sameIllustration(second));
    }
}
