public final class Employee {
    private final String name;
    private String position;
    private int yearsWorked;
    private final int idNumber;

    public Employee(String name, String position, int yearsWorked, int idNumber) {
        this.name = name;
        this.position = position;
        setYearsWorked(yearsWorked);
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
        this.position = position;
    }

    public void setYearsWorked(int yearsWorked) {
        if (yearsWorked < 0) {
            throw new IllegalArgumentException("Years worked must be nonnegative");
        }
        this.yearsWorked = yearsWorked;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Position: " + position
            + ", Years worked: " + yearsWorked + ", ID Number: " + idNumber;
    }
}
