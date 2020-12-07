package votesys.domain;

public class Candidate {
	
	private String symbol;
	private String name;
	private String count;
	
	public Candidate(String symbol, String name, String count) {
		this.symbol = symbol;
		this.name = name;
		this.count = count;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCount() {
		return count;
	}
	
	public void addCount() {
		int tmp = Integer.parseInt(count);
		count = Integer.toString(tmp + 1);
	}
	
}
