package antiSpamFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Read {

	private ArrayList<Rule> rules = new ArrayList<Rule>();
	private ArrayList<NonSpam> nonspams = new ArrayList<NonSpam>();
	private ArrayList<Spam> spams = new ArrayList<Spam>();
	private ArrayList<Email> emails = new ArrayList<Email>();

	/**
	 * Função genérica para ler ficheiros
	 * 
	 * @param path
	 * @return
	 */
	public ArrayList<String> readFiletoList(String path) {
		ArrayList<String> list = new ArrayList<>();
		try {
			list = new ArrayList<String>();
			BufferedReader read = new BufferedReader(new FileReader(path));
			String aux;
			while ((aux = read.readLine()) != null) {
				list.add(aux);
			}
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Fábrica que cria regras a partir de um ficheiro e as adiciona numa arraylist
	 * 
	 * @param path
	 */
	public void readRules() {
		String path = Mail.getInstance().getWindow().getWindowFiles().getPathtorules();
		ArrayList<String> list = readFiletoList(path);
		for (int i = 0; i < list.size(); i++) {
			Rule r = new Rule(list.get(i), 0);
			rules.add(r);
			
		}

	}

	/**
	 * Fábrica que cria emails nonspam a partir de um ficheiro e os adiciona numa
	 * arraylist
	 * 
	 * @param path
	 */
	public void readNonSpam() {
		String path = Mail.getInstance().getWindow().getWindowFiles().getPathtoham();
		ArrayList<String> list = readFiletoList(path);
		for (int i = 0; i < list.size(); i++) {
			String aux = list.get(i);
			String[] auxs = aux.split("	");
			ArrayList<Rule> rns = new ArrayList<>();
			for (int y = 1; y < auxs.length; y++) {
				Rule rulens = new Rule(auxs[y], 0);
				rns.add(rulens);
			}
			NonSpam ns = new NonSpam(auxs[0], rns);
			nonspams.add(ns);
			emails.add(ns);
			System.out.println(ns.toString());
		}
	}

	/**
	 * Fábrica que cria emails spam a partir de um ficheiro e os adiciona numa
	 * arraylist
	 * 
	 * @param path
	 */
	public void readSpam() {
		String path = Mail.getInstance().getWindow().getWindowFiles().getPathtospam();
		ArrayList<String> list = readFiletoList(path);
		for (int i = 0; i < list.size(); i++) {
			String aux = list.get(i);
			String[] auxs = aux.split("	");
			ArrayList<Rule> rs = new ArrayList<>();
			for (int y = 1; y < auxs.length; y++) {
				Rule rules = new Rule(auxs[y], 0);
				rs.add(rules);

			}
			Spam s = new Spam(auxs[0], rs);
			spams.add(s);
			emails.add(s);
			System.out.println(s.toString());
		}

	}

	public String names() {
		ArrayList<String> names = new ArrayList<>();
		for (int i = 0; i < rules.size(); i++) {
			String name = rules.get(i).getName();
			names.add(name);
		}
		String n = arrayToString(names);
		return n;
	}

	public String weights() {
		int[] aux = new int[rules.size()];
		ArrayList<String> weights = new ArrayList<>();
		for (int i = 0; i < rules.size(); i++) {
			weights.add(String.valueOf(rules.get(i).getWeight()));
		}
		String w = arrayToString(weights);
		return w;
	}

	public void write() throws IOException {
		FileWriter outputStream = null;
		outputStream = new FileWriter("outputfile.txt");
	}

	public String arrayToString(ArrayList<String> arraylist) {
		String array = "";
		for (int i = 0; i < arraylist.size(); i++) {
			array = array + arraylist.get(i) + "\n";
		}

		return array;
	}

	public ArrayList<Email> getEmails() {
		return emails;
	}

	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}

	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	public ArrayList<NonSpam> getNonspams() {
		return nonspams;
	}

	public void setNonspams(ArrayList<NonSpam> nonspams) {
		this.nonspams = nonspams;
	}

	public ArrayList<Spam> getSpams() {
		return spams;
	}

	public void setSpams(ArrayList<Spam> spams) {
		this.spams = spams;
	}

}
