package antiSpamFilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class AntiSpamFilterManager {
	
	GUI gui;
	
	public AntiSpamFilterManager() {
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
	
	//TODO
	private void automatic () {
		
	}
	
	private void manual () {
		
	}
	
	public static void main(String[] args) {
		new AntiSpamFilterManager();
	}
}
