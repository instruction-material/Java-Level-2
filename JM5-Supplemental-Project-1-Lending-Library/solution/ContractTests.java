import java.util.List;

public class ContractTests {
    public static void main(String[] args) {
        Library library = new Library();
        library.add("The Cat in the Hat", "Dr. Seuss");
        library.add("Green Eggs and Ham", "Dr. Seuss");
        library.add("The Hobbit", "J. R. R. Tolkien");
        check("J. R. R. Tolkien".equals(library.findAuthor(" THE HOBBIT ")),
            "title lookup ignores case and outer spaces");
        check(library.findAuthor("Missing title") == null, "missing title returns null");
        check(library.titlesBy(" dr. seuss ").equals(List.of("Green Eggs and Ham", "The Cat in the Hat")),
            "author search returns all matches in title order");
        check(library.titlesBy("Unknown").isEmpty(), "missing author returns an empty list");
        check(library.inventory().size() == 3, "searches preserve the inventory");
        System.out.println("Library checks passed");
    }
    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }
}
