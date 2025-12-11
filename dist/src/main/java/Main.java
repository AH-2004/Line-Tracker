import java.util.ArrayList;

public class Main {
	final static String name = "linetracker";
	final static int contextSize = 3;
	final static double threshold = 0.4;
	
    public static void main(String[] args) {
		if (args.length < 2) {
			usage();
			return;
		};

		
		FileReader left = new FileReader(args[0]);
        FileReader right = new FileReader(args[1]);

		if (!left.exists()) {
			System.out.printf("%s: %s: No such file or directory\n", name, left.getPath());
			return;
		};

		if (!right.exists()) {
			System.out.printf("%s: %s: No such file or directory\n", name, right.getPath());
			return;
		};
		
		FileDiff diff = new FileDiff(left, right, contextSize, threshold);
		System.out.printf("Diff: %s %s\n", left.getPath(), right.getPath());

		for (int i = 0; i < diff.getPairsSize(); ++i) {
			System.out.printf("(%d) <-> (%d)\n", diff.getPair(i).getLeftLine().getLineNumber(), diff.getPair(i).getRightLine().getLineNumber());
			System.out.printf("\t%s\n\t%s\n", diff.getPair(i).getLeftString(), diff.getPair(i).getRightString());
		};

		System.out.printf("Number of lines in left file: %d\n", left.getNLines());
		System.out.printf("Number of lines in right file: %d\n", right.getNLines());
		System.out.printf("Number of lines matched: %d\n", diff.getPairsSize());
    }

	private static void usage() {
		System.out.printf("Usage: %s <old file> <new file>\n", name);
	}
}
