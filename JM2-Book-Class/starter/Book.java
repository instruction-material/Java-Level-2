import java.util.Objects;

public final class Book {
    private final String title;
    private final String author;
    private int pageNum;

    public Book(String title, String author, int pageNum) {
        this.title = Objects.requireNonNull(title);
        this.author = Objects.requireNonNull(author);
        if (pageNum < 0) {
            throw new IllegalArgumentException("Page number must be nonnegative");
        }
        this.pageNum = pageNum;
    }

    public Book(String title, String author) {
        this(title, author, 0);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void changePage(int amount) {
        // TODO: Validate the candidate page before assigning it.
    }

    public boolean hasSameAuthor(Book other) {
        // TODO: Compare author text by value.
        return false;
    }

    public boolean isSameBook(Book other) {
        return equals(other);
    }

    @Override
    public boolean equals(Object other) {
        // TODO: Compare type, title, and author.
        return this == other;
    }

    @Override
    public int hashCode() {
        // TODO: Hash exactly the fields used in equals.
        return System.identityHashCode(this);
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Page Number: " + pageNum;
    }
}
