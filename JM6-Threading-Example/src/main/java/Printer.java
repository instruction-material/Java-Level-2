public class Printer implements Runnable {
    private final String phrase;

    public Printer(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public void run() {
        for (int index = 0; index < 3 && !Thread.currentThread().isInterrupted(); index++) {
            System.out.println(phrase);
            if (index == 2) {
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interrupted) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
