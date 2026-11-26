class Main {
    public static void main(String[] args) throws InterruptedException {
        var worker = new Thread(new Printer("A message from the worker"));
        worker.start();
        try {
            worker.join();
        } catch (InterruptedException interrupted) {
            worker.interrupt();
            throw interrupted;
        }
        System.out.println("Printing complete.");
    }
}
