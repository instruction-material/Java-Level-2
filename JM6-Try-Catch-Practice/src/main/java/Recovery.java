class Recovery {
    static String divide(int numerator, int divisor) {
        try {
            return "Quotient: " + (numerator / divisor);
        } catch (ArithmeticException failure) {
            return "Cannot divide by zero.";
        }
    }

    static String lookup(int[] values, int index) {
        try {
            return "Value: " + values[index];
        } catch (ArrayIndexOutOfBoundsException failure) {
            return "Index outside array.";
        }
    }
}
