public final class Employee {
    private final String name;
    private String position;
    private int yearsWorked;
    private final int idNumber;

    public Employee(String name, String position, int yearsWorked, int idNumber) {
        this.name = name;
        this.position = position;
        this.yearsWorked = yearsWorked;
        // TODO: Apply the same years-worked rule as the setter.
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getYearsWorked() {
        return yearsWorked;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setPosition(String position) {
        // TODO: Change the position of this employee.
    }

    public void setYearsWorked(int yearsWorked) {
        // TODO: Validate and store the new number of years.
    }

    @Override
    public String toString() {
        // TODO: Return the four fields using the format from README.md.
        return "Employee summary pending";
    }
}
