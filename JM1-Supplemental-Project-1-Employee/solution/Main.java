public class Main {
    public static void main(String[] args) {
        Employee jacky = new Employee("Jacky", "Intern", 1, 556614);
        Employee tina = new Employee("Tina", "Manager", 3, 76544);
        jacky.setPosition("Engineer");
        tina.setYearsWorked(4);
        System.out.println(jacky);
        System.out.println(tina);
    }
}
