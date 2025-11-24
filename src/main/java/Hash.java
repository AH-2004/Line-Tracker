public class Hash {
	private String str;
    private long hash;

	public Hash(String str) {
		this.str = str;
        this.hash = 5381;
        djb2(this.hash);
	}

	public Hash(String str, int seed) {
		this.str = str;
        this.hash = seed;
        djb2(this.hash);
	}

	private long djb2(long seed) {
        if ("".equals(this.str)) { return 0; }

        this.hash = seed;

        for (int i = 0; i < this.str.length(); ++i) {
            this.hash = ((this.hash << 5) + this.hash) + this.str.charAt(i);
        }

        return this.hash;
    }

    public void setString(String str) {
        this.str = str;
    }

    public String getString() {
        return this.str;
    }

    public long getHash() {
        return this.hash;
    }
}
