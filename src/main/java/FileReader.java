import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
	private String path;
	private File file;
	private ArrayList<Line> lines;
	
	public FileReader(String path) {
		this.file = new File(path);
		this.path = path;
		read();
		normalize();
		hashify();
	}

	private void read() {
		if (!this.file.exists()) { return; }

		this.lines = new ArrayList<Line>();
		
		Scanner scan;
		int i = 1;

		try {
			scan = new Scanner(this.file);
			while (scan.hasNextLine()) {
				this.lines.add(new Line(scan.nextLine(), i++));
			}
		} catch (FileNotFoundException e) {
			System.out.printf("FileHash: read: Failed to read lines");
		};
	};

	private void normalize() {
		for (int i = 0; i < this.lines.size(); ++i) {
			this.lines.get(i).normalize();
			this.lines.removeIf(l -> l.isBlank());
		}
	};
	
	private void hashify() {
		if (!this.file.exists() || lines == null
			|| lines.size() < 0) { return; }
		for (int i = 0; i < lines.size(); ++i) {
			Hash hash = new Hash(this.lines.get(i).getStr());
			this.lines.get(i).setHash(hash);
		}
	}
	
	public String getPath() {
		return this.path;
	};
	
	public Boolean exists() {
		return this.file.exists();
	};

	public int getNLines() {
		return this.lines.size();
	}

	public ArrayList<Line> getLines() {
		return this.lines;
	};

	public Line getLine(int i) {
		if (i < 0 || lines == null ||
			lines.size() <= i) { return null; }
		return lines.get(i);
	}

	public String getLineStr(int i) {
		if (i < 0 || lines == null ||
			lines.size() <= i) { return null; }
		return lines.get(i).getStr();
	}
	
	public long getLinehash(int i) {
		if (i < 0 || lines == null ||
			lines.size() <= i) { return -1; }
		return lines.get(i).getHash().getHash();
	}
}
