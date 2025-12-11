import java.util.ArrayList;
import java.lang.StringBuilder;

public class FileDiff {
	private FileReader left;
	private FileReader right;
	private int contextSize;
	private double similarityThreshold;
	private int[][] table;
	private ArrayList<Pair> pairs;
	
	public FileDiff(FileReader left, FileReader right, int contextSize, double similarityThreshold) {
		this.left = left;
		this.right = right;
		this.contextSize = contextSize;
		this.similarityThreshold = similarityThreshold;
		this.table = new int[left.getNLines()+1][right.getNLines()+1];
		this.pairs = new ArrayList<Pair>();
		
		enumerateTable();
		enumeratePairs();
		sortPairs();
	}

	private void enumerateTable() {
		if (this.table == null) { return; }
		
		for (int i = this.left.getNLines() - 1; i > -1; --i) {
			for (int j = this.right.getNLines() - 1; j > -1; --j) {
				if (this.left.getLinehash(i) == this.right.getLinehash(j)) {
					this.table[i][j] = this.table[i+1][j+1] + 1;
				} else {
					this.table[i][j] = Math.max(this.table[i+1][j],
												this.table[i][j+1]);
				}
			}
		}
	}

	private void enumeratePairs() {
		if (this.table == null || this.pairs == null) { return; }
		
		int i = 0;
		int j = 0;
		
		ArrayList<Integer> unmatchedLeft = new ArrayList<Integer>();
		ArrayList<Integer> unmatchedRight = new ArrayList<Integer>();
		
		while (i < this.left.getNLines() && j < this.right.getNLines()) {
			if (this.left.getLinehash(i) == this.right.getLinehash(j)) {
				Pair pair = new Pair(i, j, this.left.getLine(i), this.right.getLine(j));
				this.pairs.add(pair);
				i++;
				j++;
				continue;
			}
			
			if (this.table[i+1][j] >= this.table[i][j+1]) {
				unmatchedLeft.add(0, i);
				i++;
			} else {
				unmatchedRight.add(0, j);
				j++;
			}
		}
		
		for (int r = 0; r < unmatchedRight.size(); r++) {
			int rightIdx = unmatchedRight.get(r);
			String rightLine = this.right.getLineStr(rightIdx);
			String rightCtx = lineContext(this.right, rightIdx);

			double bestSim = 0.0;
			int bestLeftIdx = -1;

			for (int l = 0; l < unmatchedLeft.size(); l++) {
				int leftIdx = unmatchedLeft.get(l);
				String leftLine = this.left.getLineStr(leftIdx);
				String leftCtx = lineContext(this.left, leftIdx);

				double lineSim = levenshtein(leftLine, rightLine);
				double ctxSim = levenshtein(leftCtx, rightCtx);
				double combinedSim = 0.6 * lineSim + 0.4 * ctxSim;

				if (combinedSim > bestSim) {
					bestSim = combinedSim;
					bestLeftIdx = leftIdx;
				}
			}

			if (bestLeftIdx < 0 || bestSim < this.similarityThreshold) { continue; }
			
			Pair pair = new Pair(bestLeftIdx, rightIdx, this.left.getLine(bestLeftIdx), this.right.getLine(rightIdx));
			this.pairs.add(pair);
			unmatchedLeft.remove(Integer.valueOf(bestLeftIdx));
		}
	}

	private String lineContext(FileReader file, int lineIdx) {
		if (file == null || lineIdx < 0 || this.contextSize < 1) { return null; };
		StringBuilder context = new StringBuilder();
		
		// Above
		int i = lineIdx - 1;
		int j = 0;

		while (i > -1 && j < contextSize) {
			context.insert(0, file.getLineStr(i));
			i--;
			j++;
		}

		// Below
		i = lineIdx + 1;
		j = 0;
		
		while (i < file.getNLines() && j < contextSize) {
			context.append(file.getLineStr(i));
			context.append(" ");
			i++;
			j++;
		}
		
		return context.toString().trim();
	}
	
	private double levenshtein(String s, String t) {
		if (s == null || t == null || (s.isEmpty() && t.isEmpty())) {
			return 1.0;
		}

		int[][] lTable = new int[s.length() + 1][t.length() + 1];

		for (int i = 0; i <= s.length(); i++) {
			lTable[i][0] = i;
		}
		
		for (int j = 0; j <= t.length(); j++) {
			lTable[0][j] = j;
		}

		// Enumerate lTable
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= t.length(); j++) {
				int cost = (s.charAt(i - 1) == t.charAt(j - 1)) ? 0 : 1;
				lTable[i][j] = Math.min(lTable[i - 1][j] + 1, lTable[i][j - 1] + 1);
				lTable[i][j] = Math.min(lTable[i][j], lTable[i - 1][j - 1] + cost);
			}
		}

		int distance = lTable[s.length()][t.length()];
		int len = Math.max(s.length(), t.length());
		return 1.0 - (double) distance / len;
	}

	private void sortPairs() {
		if (this.pairs == null || this.pairs.size() < 2) { return; }

		for (int i = 1; i < this.pairs.size(); i++) {
			Pair k = this.pairs.get(i);
			int kLineNumber = k.getLeftLine().getLineNumber();
			int j = i - 1;
			while (j >= 0 &&
				   this.pairs.get(j).getLeftLine().getLineNumber() > kLineNumber) {
				this.pairs.set(j + 1, this.pairs.get(j));
				j--;
			}
			this.pairs.set(j + 1, k);
		}
	}

	public int getPairsSize() {
		return this.pairs.size();
	}

	ArrayList<Pair> getPairs() {
		return this.pairs;
	};

	public Pair getPair(int i) {
		if (i < 0 || pairs == null ||
			pairs.size() <= i) { return null; }
		return this.pairs.get(i);
	};
}
