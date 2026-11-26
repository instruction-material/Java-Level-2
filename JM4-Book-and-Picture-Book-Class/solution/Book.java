import java.util.Objects;

public class Book {
    private final String title;
    private final String author;
    private int page;

    public Book(String title, String author, int page) {
        if (page < 0) throw new IllegalArgumentException("Bookmark must be nonnegative");
        this.title = Objects.requireNonNull(title);
        this.author = Objects.requireNonNull(author);
        this.page = page;
    }

    public void advancePage(int delta) {
        int next = Math.addExact(page, delta);
        if (next < 0) throw new IllegalArgumentException("Bookmark must be nonnegative");
        page = next;
    }

    public final boolean isSameBook(Book other) {
        return other != null && title.equals(other.title) && author.equals(other.author);
    }

    @Override
    public String toString() {
        return title + " by " + author + ", page " + page;
    }
}
