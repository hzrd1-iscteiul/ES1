package antiSpamFilter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class AntiSpamFilterManager {
	
	private static AntiSpamFilterManager instance;

	public static AntiSpamFilterManager getInstance() {
		if (instance == null)
			instance = new AntiSpamFilterManager();
		return instance;
	}
	
	GUI gui;
	
	private AntiSpamFilterManager() {
		startGUI();
		loadRules();
	}
	
	private void startGUI() {
		gui = new GUI();
		gui.setLookGUI();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
		        gui.setVisible(true);
		        SwingUtilities.updateComponentTreeUI(gui);
            }
        });  
	}
	
	private void loadRules () {
		ArrayList <Rule> ruleList = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(gui.getRules_Path()));
			String text;
			while ((text=reader.readLine()) != null) {
				String [] rule = text.split("\t");
				if(rule.length==1) {
					ruleList.add(new Rule(rule[0],0));
				}
				else if(rule.length==2) {
					ruleList.add(new Rule(rule[0], Double.parseDouble(rule[1])));
				}else {
					System.out.println("Rule format not recognized");
				}
			}
			reader.close();
			gui.setRulesList(ruleList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveRules () {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(gui.getRules_Path()));
			ArrayList<Rule> rules = gui.getRulesList();
			for(Rule rule : rules) {
				writer.write(rule.getName()+"\t"+rule.getWeight()+"\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private double test_mail(String[] rules, ArrayList<Rule> rulesList) {
		double MailTotalWeigth = 0.0;
		for (String rule : rules) {
            for (Rule r : rulesList) {
                if (r.getName().equals(rule)) {
                    MailTotalWeigth += r.getWeight();
                    break;
                }
            }
        }
		return MailTotalWeigth;
	}
	
	private Results evaluate() {
		ArrayList<Rule> rules = gui.getRulesList(); 
		int fp=0;	
		try {
			BufferedReader reader = new BufferedReader(new FileReader(gui.getHam_Path()));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] mail_rules = line.split("\t");
	            double mail_values = test_mail(mail_rules, rules);
	            if (mail_values >= 5)
	                fp++;
	        }
	        reader.close();
		}catch (IOException e) {
        	System.out.println("Ham file isn't correct.");
        }
		int fn=0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(gui.getSpam_Path()));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] mail_rules = line.split("\t");
	            double mail_values  = test_mail(mail_rules, rules);
	            if (mail_values >= 5)
	                fn++;
	        }
	        reader.close();
		}catch (IOException e) {
        	System.out.println("Spam file isn't correct.");
        }
		return new Results(fp, fn);
	}
	
	//TODO
	public void automatic () {
		
	}
	
	public void manual () {
		 gui.setResults(evaluate());
	}

	public static void main(String[] args) {
		AntiSpamFilterManager.getInstance();
	}
}
