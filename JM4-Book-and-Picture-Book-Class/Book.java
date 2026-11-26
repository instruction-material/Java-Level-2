public class Book {
    String title;
    String author;
    int pageNum;

    public Book(String t, String a, int p) {
        title = t;
        author = a;
        pageNum = p;
    }

    public Book(String t, String a) {
        title = t;
        author = a;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void changePage(int amt) {
        pageNum += amt;
    }

    public boolean hasSameAuthor(Book b) {
        return b != null && author.equals(b.getAuthor());
    }

    public boolean isSameBook(Book b) {
        return hasSameAuthor(b) && title.equals(b.getTitle());
    }

    public String toString() {
        return "Title: " + title + ", Author: " + author +
            ", Page Number: " + pageNum;
    }
}
