import java.util.Objects;

public final class PictureBook extends Book {
    private final String illustrator;

    public PictureBook(String title, String author, int page, String illustrator) {
        super(title, author, page);
        this.illustrator = Objects.requireNonNull(illustrator);
    }

    public boolean sameIllustration(PictureBook other) {
        return false; // TODO: Compare the work and the illustrator.
    }

    @Override
    public String toString() {
        return super.toString(); // TODO: Append the illustrator.
    }
}
