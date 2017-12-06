package Group73;

import java.util.ArrayList;

public class Email {

	private String name;
	private ArrayList<Rule> rules;
	
	
	public Email (String name, ArrayList<Rule> rules) {
		this.name=name;
		this.rules=rules;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<Rule> getRules() {
		return rules;
	}


	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}
	
}
