public class Line {
	private String str;
	private Hash hash;
	private int lineNumber;

	public Line(String str) {
		this.str = str;
	}
	
	public Line(String str, int lineNumber) {
		this.str = str;
		this.lineNumber = lineNumber;
	}
		
	public Line(String str, Hash hash, int lineNumber) {
		this.str = str;
		this.hash = hash;
		this.lineNumber = lineNumber;
	}

	public void normalize() {
		this.str = this.str.strip();
		this.str = this.str.replaceAll("\\s+", " ");
	}

	public void setStr(String str) {
		this.str = str;
	}

	public void setHash(Hash hash) {
		this.hash = hash;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getStr() {
		return str;
	}

	public Hash getHash() {
		return hash;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public boolean isBlank() {
		return this.str.strip().isBlank();
	}
};
