package antiSpamFilter;

public class Rule {

	private String name;
	private double weight;
	
	public Rule(String name, double weight) {
		this.name=name;
		this.weight=weight;
		
	}


	public String getName() {
		return name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	
	
	

}
