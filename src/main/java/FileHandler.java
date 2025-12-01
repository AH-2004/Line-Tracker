import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
	File file;
	Hash[] lineHashes;
	
	public FileHandler(String name) {
		this.file = new File(name);
		this.lineHashes = new Hash[this.getLineCount()];
	}

	void hashFile(Boolean output) {
		try {
			Scanner scan = new Scanner(this.file);
			int i = 0;

			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				this.lineHashes[i] = hashify(line);
				if (output) {
					System.out.printf("Hash (%d): %s -> %d\n", i, line, lineHashes[i].getHash());
				}
				++i;
			}

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
	}

	private Hash hashify(String line) {
		return new Hash(line);
	}

	public int getLineCount() {
		int i = 0;
		try {
			Scanner scan = new Scanner(this.file);
			while (scan.hasNextLine()) {
				scan.nextLine();
				++i;
			}
		} catch (FileNotFoundException e) {
            return -1;
        }
		return i;
	}

	public Hash[] getHashes() {
		return this.lineHashes;
	}
	
}
