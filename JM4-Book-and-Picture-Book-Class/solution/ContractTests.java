public class ContractTests {
    public static void main(String[] args) {
        PictureBook lantern = new PictureBook("The Lantern", "A. Reed", 5, "K. Lin");
        PictureBook differentArt = new PictureBook("The Lantern", "A. Reed", 0, "P. Diaz");
        PictureBook sameArt = new PictureBook("The Lantern", "A. Reed", 0, "K. Lin");
        Book parent = lantern;
        check(parent.isSameBook(differentArt) && lantern.isSameBook(differentArt),
            "the work comparison has one meaning across reference types");
        check(lantern.sameIllustration(sameArt), "matching illustrated edition uses all three fields");
        check(!lantern.sameIllustration(differentArt) && !lantern.sameIllustration(null),
            "different or missing illustrator match");
        check(!lantern.sameIllustration(new PictureBook("Other", "A. Reed", 0, "K. Lin")),
            "an illustrator alone does not identify an edition");
        parent.advancePage(2);
        check(parent.toString().equals("The Lantern by A. Reed, page 7, illustrated by K. Lin"),
            "dynamic display preserves inherited bookmark behavior");
        System.out.println("PictureBook checks passed");
    }

    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }
}
