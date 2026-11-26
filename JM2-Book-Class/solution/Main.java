public class Main {
    public static void main(String[] args) {
        Book matilda = new Book("Matilda", "Roald Dahl", 100);
        Book redwall = new Book("Redwall", "Brian Jacques");
        Book witches = new Book("The Witches", "Roald Dahl");
        Book anotherCopy = new Book("The Witches", "Roald Dahl", 50);

        redwall.changePage(20);
        System.out.println(redwall);
        System.out.println("Same author: " + matilda.hasSameAuthor(witches));
        System.out.println("Same title and author: " + witches.isSameBook(anotherCopy));
    }
}
