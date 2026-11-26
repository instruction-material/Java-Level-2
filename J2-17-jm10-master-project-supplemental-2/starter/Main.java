class Main {
    public static void main(String[] args) {
        var review = new CheckReview();
        review.record("win", true);
        review.record("tie", false);
        review.record("restart", false);
        review.record("retry", false);
        review.record("retry", true);
        System.out.println("Open checks: " + review.openChecks());
    }
}
