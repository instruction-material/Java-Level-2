import java.util.Objects;

public final class PictureBook extends Book {
    private final String illustrator;

    public PictureBook(String title, String author, int page, String illustrator) {
        super(title, author, page);
        this.illustrator = Objects.requireNonNull(illustrator);
    }

    public boolean sameIllustration(PictureBook other) {
        return isSameBook(other) && illustrator.equals(other.illustrator);
    }

    @Override
    public String toString() {
        return super.toString() + ", illustrated by " + illustrator;
    }
}
