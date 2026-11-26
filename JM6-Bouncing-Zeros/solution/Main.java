class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(new SimpleAnimation(4, 7, 25, System.out::println));
        worker.start();
        try {
            worker.join();
        } catch (InterruptedException interrupted) {
            worker.interrupt();
            throw interrupted;
        }
        System.out.println("Animation complete.");
    }
}
