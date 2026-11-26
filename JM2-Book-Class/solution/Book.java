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
        int nextPage = Math.addExact(pageNum, amount);
        if (nextPage < 0) {
            throw new IllegalArgumentException("Page number must be nonnegative");
        }
        pageNum = nextPage;
    }

    public boolean hasSameAuthor(Book other) {
        return other != null && author.equals(other.author);
    }

    public boolean isSameBook(Book other) {
        return equals(other);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Book book && hasSameAuthor(book) && title.equals(book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Page Number: " + pageNum;
    }
}
