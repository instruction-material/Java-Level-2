class Main {
    public static void main(String[] args) {
        var directory = new Directory();
        directory.add(" library ", "Luna");
        directory.add("arcade", "Orion");
        System.out.println("Alias: " + directory.find("library").orElse("missing"));
    }
}
