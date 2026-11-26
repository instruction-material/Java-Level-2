import java.util.HashSet;
import java.util.Set;

public class ContractTests {
    public static void main(String[] args) {
        Book witches = new Book("The Witches", "Roald Dahl");
        Book second = new Book(new String("The Witches"), new String("Roald Dahl"), 50);
        Book matilda = new Book("Matilda", "Roald Dahl");
        Book differentAuthor = new Book("The Witches", "Someone else");
        check(witches.getPageNum() == 0, "omitted bookmark starts at page zero");
        check(witches.hasSameAuthor(matilda), "same author can write different titles");
        check(!witches.isSameBook(matilda), "a different title changes book identity");
        check(!witches.isSameBook(differentAuthor), "a different author changes book identity");
        check(witches.isSameBook(second) && second.isSameBook(witches), "title and author compare by value");
        check(witches.equals(witches) && !witches.equals("The Witches") && !witches.equals(null), "equality accepts the correct type");
        check(!witches.hasSameAuthor(null) && !witches.isSameBook(null), "comparison helpers handle null");
        check(witches.hashCode() == second.hashCode(), "equal books have equal hashes");
        Set<Book> shelf = new HashSet<>();
        shelf.add(witches);
        witches.changePage(20);
        check(witches.getPageNum() == 20 && second.getPageNum() == 50, "changing one bookmark leaves the other intact");
        check(shelf.contains(second), "a bookmark change preserves equality and hash lookup");
        rejects(() -> witches.changePage(-21), IllegalArgumentException.class);
        rejects(() -> witches.changePage(Integer.MAX_VALUE), ArithmeticException.class);
        check(witches.getPageNum() == 20, "a rejected page change preserves the bookmark");
        rejects(() -> new Book("New Book", "Author", -1), IllegalArgumentException.class);
        witches.changePage(-20);
        check(witches.getPageNum() == 0, "returning to zero is valid");
        System.out.println("Book class checks passed");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new AssertionError(description);
        }
    }

    private static void rejects(Runnable operation, Class<? extends RuntimeException> type) {
        try {
            operation.run();
        } catch (RuntimeException error) {
            check(type.isInstance(error), "page change rejected with the expected exception");
            return;
        }
        throw new AssertionError("invalid page was accepted");
    }
}
