public class Main {
    public static void main(String[] args) {
        Attendance attendance = new Attendance();
        attendance.arrive("Lab A", "Ada");
        System.out.println("Repeat accepted: " + attendance.arrive("Lab A", "Ada"));
        attendance.arrive("Lab A", "Jo");
        attendance.arrive("Lab B", "Ada");
        System.out.println("Lab A: " + attendance.count("Lab A"));
        System.out.println("Lab B: " + attendance.count("Lab B"));
        System.out.println("Missing room: " + attendance.count("Lab C"));
    }
}
