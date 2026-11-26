public class Main {
    public static void main(String[] args) {
        Dog cookie = new Dog("Cookie", true);
        Cat cheeto = new Cat("Cheeto", 5);
        for (Pet pet : new Pet[]{cookie, cheeto}) {
            System.out.println(pet.introduce());
        }
        System.out.println("Service dog: " + cookie.isServiceDog());
        System.out.println("Jump distance: " + cheeto.getJumpDistance());
    }
}
