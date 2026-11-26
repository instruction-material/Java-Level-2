import java.util.ArrayList;

class Main {
    public static void main(String[] args) throws InterruptedException {
        var hours = new ArrayList<Integer>();
        var worker = new Thread(new ClockPlayback(11, 4, 0, hours::add));
        worker.start();
        try {
            worker.join();
        } catch (InterruptedException interrupted) {
            worker.interrupt();
            throw interrupted;
        }
        System.out.println("Hours: " + hours);
    }
}
