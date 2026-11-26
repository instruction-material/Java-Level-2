class Main {
    public static void main(String[] args) {
        var book = new Spending();
        book.charge("supplies", 175);
        book.charge(" supplies ", 25);
        book.charge("travel", 50);
        book.charge("travel", -1);
        System.out.println("Summary: " + book.snapshot());
    }
}
