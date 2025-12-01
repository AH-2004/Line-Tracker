public class Main {
    public static void main(String[] args) {
        FileHandler file = new FileHandler("./test.txt");
        FileHandler file2 = new FileHandler("./test_2.txt");


        file.hashFile(true);
        System.out.printf("\n");
        file2.hashFile(true);

        Hash[] fileHashes = file.getHashes();
        Hash[] file2Hashes = file2.getHashes();

        int hashLen = Math.min(fileHashes.length, file2Hashes.length);
        for (int i = 0; i < hashLen; ++i) {
            System.out.printf("%d <-> %d: %d\n",
							  fileHashes[i].getHash(),
							  file2Hashes[i].getHash(),
							  fileHashes[i].getHash()-file2Hashes[i].getHash());
        };
    }
}
