import java.util.function.Consumer;

class SimpleAnimation implements Runnable {
    private final int width;
    private final int frames;
    private final long pauseMillis;
    private final Consumer<String> display;
    private int position;
    private int direction = 1;

    SimpleAnimation(int width, int frames, long pauseMillis, Consumer<String> display) {
        if (width < 2 || frames < 1 || pauseMillis < 0) {
            throw new IllegalArgumentException("Use width ≥ 2, positive frames and a nonnegative pause.");
        }
        this.width = width;
        this.frames = frames;
        this.pauseMillis = pauseMillis;
        this.display = display;
    }

    @Override
    public void run() {
        for (int frame = 0; frame < frames && !Thread.currentThread().isInterrupted(); frame++) {
            display.accept(".".repeat(position) + "0" + ".".repeat(width - position - 1));
            if (frame + 1 == frames) {
                return;
            }
            advance();
            try {
                Thread.sleep(pauseMillis);
            } catch (InterruptedException interrupted) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private void advance() {
        if (position == width - 1) {
            direction = -1;
        } else if (position == 0) {
            direction = 1;
        }
        position += direction;
    }
}
