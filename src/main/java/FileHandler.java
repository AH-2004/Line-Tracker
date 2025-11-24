import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
	File file;
	
	public FileHandler(String name) {
		this.file = new File(name);
	}

	void readFile() {
		try {
			Scanner scan = new Scanner(this.file);
			int i = 1;

			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				Hash lineHash = hashify(line);
				System.out.printf("Hash (%d): %s -> %d\n", i, line, lineHash.getHash());

				++i;
			};

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        };
	}

	private Hash hashify(String line) {
		return new Hash(line);
	}
}
