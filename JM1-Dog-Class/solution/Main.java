public class Main {
    public static void main(String[] args) {
        Dog fido = new Dog("Fido", 4, 15.5, true);
        Dog scout = new Dog("Scout", 2, 11, true);

        System.out.println(fido.getAge());
        fido.setAge(5);
        System.out.println(fido);
        System.out.println(scout.getAge());
    }
}
