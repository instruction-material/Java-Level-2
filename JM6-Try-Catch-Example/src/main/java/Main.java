class Main {
    public static void main(String[] args) {
        int numerator = 10;
        for (int divisor : new int[] {2, 0}) {
            divide(numerator, divisor);
        }
        System.out.println("Both examples completed.");
    }

    private static void divide(int numerator, int divisor) {
        try {
            System.out.println(numerator + " / " + divisor + " = " + numerator / divisor);
        } catch (ArithmeticException exception) {
            System.out.println("Cannot divide by zero.");
        }
    }
}
