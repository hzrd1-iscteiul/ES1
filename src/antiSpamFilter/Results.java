package antiSpamFilter;

public class Results {
	
	int fp;
	int fn;
	
	public Results(int fp, int fn) {
		this.fp = fp;
		this.fn = fn;
	}
	
	public int getFn() {
		return fn;
	}
	
	public int getFp() {
		return fp;
	}
}
