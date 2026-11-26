public class PictureBook extends Book {
    private String illustrator;

    public PictureBook(String t, String a, int p, String i) {
        super(t, a, p);
        illustrator = i;
    }

    public String getIllustrator() {
        return illustrator;
    }

    public void setIllustrator(String i) {
        illustrator = i;
    }

    public boolean hasSameIllustrator(PictureBook other) {
        return other != null && illustrator.equals(other.getIllustrator());
    }

    public boolean isSameIllustratedEdition(PictureBook other) {
        return isSameBook(other) && hasSameIllustrator(other);
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author +
            ", Illustrator: " + illustrator + " ,Page Number: " + pageNum;
    }
}
