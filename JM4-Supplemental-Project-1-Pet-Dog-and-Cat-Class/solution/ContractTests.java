public class ContractTests {
    public static void main(String[] args) {
        Dog dog = new Dog("Cookie", true);
        Cat cat = new Cat("Cheeto", 5);
        Pet[] pets = {dog, cat};
        check(pets[0].introduce().equals("Cookie says Bark"), "dog sound dispatches through Pet");
        check(pets[1].introduce().equals("Cheeto says Meow"), "cat sound dispatches through Pet");
        check(dog.isServiceDog() && !new Dog("Scout", false).isServiceDog(), "dog state stays local");
        check(cat.getJumpDistance() == 5 && new Cat("Pip", 3).getJumpDistance() == 3, "cat state stays local");
        System.out.println("Pet checks passed");
    }
    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }
}
