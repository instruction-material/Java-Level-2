public class ContractTests {
    public static void main(String[] args) {
        Attendance attendance = new Attendance();
        check(attendance.count("Lab A") == 0 && attendance.roomCount() == 0,
            "missing lookup does not add a room");
        check(attendance.arrive("Lab A", "Ada"), "first attendance is added");
        check(!attendance.arrive("Lab A", "Ada"), "duplicate attendance is ignored");
        check(attendance.arrive("Lab B", "Ada"), "separate rooms have separate sets");
        check(attendance.arrive("Lab A", "Jo"), "a second name counts");
        check(attendance.count("Lab A") == 2 && attendance.count("Lab B") == 1,
            "all room counts remain independent");
        System.out.println("Attendance checks passed");
    }
    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }
}
