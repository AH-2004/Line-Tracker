public class Pair {
	private int left;
	private int right;
	private Line leftLine;
	private Line rightLine;

	public Pair(int left, int right, Line leftLine, Line rightLine) {
		this.left = left;
		this.right = right;
		this.leftLine = leftLine;
		this.rightLine = rightLine;
	};

	void setLeft(int left) {
		this.left = left;
	};
	
	void setRight(int right) {
		this.right = right;
	};

	void setLeftLine(Line leftLine) {
		this.leftLine = leftLine;
	};
	
	void setRightLine(Line rightLine) {
		this.rightLine = rightLine;
	};
	
	int getLeft() {
		return this.left;
	};
	
	int getRight() {
		return this.right;
	};

	Line getLeftLine() {
		return this.leftLine;
	};

	String getLeftString() {
		return this.leftLine.getStr();
	};
	
	Line getRightLine() {
		return this.rightLine;
	};
	
	String getRightString() {
		return this.rightLine.getStr();
	};
}
