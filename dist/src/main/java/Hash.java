public class Hash {
	private String str;
	private long seed;
    private long hash;

	public Hash(String str) {
		this.str = str;
        this.seed = 5381;
        djb2();
	}

	public Hash(String str, long seed) {
		this.str = str;
        this.seed = seed;
        djb2();
	}

	private void djb2() {
        if ("".equals(this.str)) { return; }
        this.hash = this.seed;
        for (int i = 0; i < this.str.length(); ++i) {
            this.hash = ((this.hash << 5) + this.hash)
				+ this.str.charAt(i);
        }
    }

    public void setString(String str) {
        this.str = str;
		djb2();
    }

	public void setSeed(long seed) {
		this.seed = seed;
		djb2();
	}

    public String getString() {
        return this.str;
    }

	public long getSeed() {
		return this.seed;
	}

    public long getHash() {
        return this.hash;
    }
}
