import java.util.function.Consumer;

class AnimalPlayer implements Runnable {
    private final String sound;
    private final int delayMillis;
    private final Consumer<String> record;

    AnimalPlayer(String sound, int delayMillis, Consumer<String> record) {
        this.sound = sound;
        this.delayMillis = delayMillis;
        this.record = record;
    }

    @Override
    public void run() {
        if (Thread.currentThread().isInterrupted()) {
            return;
        }
        record.accept(sound);
        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
            return;
        }
        if (!Thread.currentThread().isInterrupted()) {
            record.accept(sound);
        }
    }
}
