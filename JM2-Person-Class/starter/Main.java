public class Main {
    public static void main(String[] args) {
        Person abby = new Person("Abby", 8, 2);
        Person jack = new Person("Jack", 12, "6");
        abby.hasBirthday();
        System.out.println(abby);
        System.out.println(jack);
        System.out.println(new Person("Tiffany", 10));
        System.out.println(new Person());
    }
}
