package votesys.domain;

public class Voter {
	
	private String name;
	private String secNum;
	
	public Voter(String name, String secNum){
		this.name = name;
		this.secNum = secNum;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSecNum() {
		return secNum;
	}
	
	public String toString() {
		return secNum.substring(0, 8) + "******"+"      " + name;
	}
	
}
