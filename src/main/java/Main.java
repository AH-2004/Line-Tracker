public class Main {
    public static void main(String[] args) {
        FileHandler file = new FileHandler("./test.txt");
        FileHandler file2 = new FileHandler("./test2.txt");

        file.readFile();
        System.out.printf("\n");
        file2.readFile();
    }
}
