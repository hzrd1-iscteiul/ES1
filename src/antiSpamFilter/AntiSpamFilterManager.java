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
	
	public ArrayList<Rule> getRulesList(){
		return gui.getRulesList();
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
	
	public Results evaluate(ArrayList<Rule> rules) { 
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
	            if (mail_values < 5)
	                fn++;
	        }
	        reader.close();
		}catch (IOException e) {
        	System.out.println("Spam file isn't correct.");
        }
		return new Results(fp, fn);
	}

	private ArrayList<Rule> getJMetalBest() {
		ArrayList<Rule> rules = gui.getRulesList();
		
		int best = -1;
		try {
            BufferedReader jmetal = new BufferedReader(new FileReader("experimentBaseDirectory\\AntiSpamStudy\\data\\NSGAII\\AntiSpamFilterProblem\\BEST_HV_FUN.tsv"));
            double best_fp = -1, best_fn = -1;
            String line;
            int linecounter = 0;
            while ((line = jmetal.readLine()) != null) {
            	String [] fp_fn = line.split(" ");
                double fp = Double.parseDouble(fp_fn[0]);
                double fn = Double.parseDouble(fp_fn[1]);
                if (best == -1) {
                    best = 0;
                    best_fp = fp;
                    best_fn = fn;
                } else { 
                	if (fp < best_fp && fn > best_fn) {
                		best = linecounter;
                    	best_fp = fp;
                    	best_fn = fn;
                	}
                }
                linecounter++;
            }
            
            BufferedReader values = new BufferedReader(new FileReader("experimentBaseDirectory\\AntiSpamStudy\\data\\NSGAII\\AntiSpamFilterProblem\\BEST_HV_VAR.tsv"));
            linecounter = 0;
            line = values.readLine();
            while (linecounter != best) {
                line = values.readLine();
                linecounter++;
            }
            String[] weights = line.split(" ");
            for (int i = 0; i < rules.size(); i++) {
                rules.get(i).setWeight( Double.parseDouble(weights[i]));
            }
            values.close();
            jmetal.close();
        } catch (IOException e) {
            System.out.println("Make sure you have access to the experimentBaseDirectory folder.");
        }
		return rules;
	}
	
	//TODO
	public void automatic () {
		try {
			AntiSpamFilterAutomaticConfiguration.runJMetal();
		} catch (IOException e) {
			System.out.println("JMetal something went wrong.");
		}
        ArrayList<Rule> rules = getJMetalBest();
		gui.setRulesList(rules);
		gui.setResults(evaluate(rules));
	}
	
	public void manual () {
		 gui.setResults(evaluate(gui.getRulesList()));
	}

	public static void main(String[] args) {
		AntiSpamFilterManager.getInstance();
	}
}
