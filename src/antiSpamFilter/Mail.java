package antiSpamFilter;

import java.util.ArrayList;
import java.util.Random;

public class Mail {

	private static Mail instance;

	public static Mail getInstance() {
		if (instance == null) {
			instance = new Mail();
		}
		return instance;
	}

	private GUI g = new GUI();
	private Read read = new Read();
	private ArrayList<Email> fp = new ArrayList<>();
	private int falsospositivos = fp.size();
	private ArrayList<Email> fn = new ArrayList<>();
	private int falsosnegativos = fn.size();
	private ArrayList<Email> toldspam = new ArrayList<>();
	private ArrayList<Email> toldnonspam = new ArrayList<>();

	/**
	 * Indica se os emails são spam ou não spam através do count das suas regras e
	 * coloca-os na respectiva lista
	 */
	public void choosetype() {

		for (int i = 0; i < read.getEmails().size(); i++) {
			int count = 0;
			for (int y = 0; y < read.getEmails().get(i).getRules().size(); y++) {
				count = count + read.getEmails().get(i).getRules().get(y).getWeight();
				System.out.println("Count esta a valor:" + count + "para a rule"
						+ read.getEmails().get(i).getRules().get(y).getName());

			}
			// Valores menores que 5 são considerados nonspam
			if (count < 5) {
				toldnonspam.add(read.getEmails().get(i));
			}
			// Valores maiores que 5 são considerads Spam
			if (count > 5) {
				toldspam.add(read.getEmails().get(i));
			}
		}
	}

	/**
	 * Devolve os falsospositivos e os falsosnegativos
	 */
	public void showfpfn() {
		choosetype();
		for (int i = 0; i < read.getEmails().size(); i++) {
			if (read.getEmails().get(i) instanceof Spam) {
				for (int y = 0; y < toldnonspam.size(); y++) {
					if (read.getEmails().get(i).equals(toldnonspam.get(y))) {
						fn.add(read.getEmails().get(i));
					}
				}
				System.out.println("Entrou nos falsos positivos");

			}
			if (read.getEmails().get(i) instanceof Ham) {
				for (int y = 0; y < toldspam.size(); y++) {
					if (read.getEmails().get(i).equals(toldspam.get(y))) {
						fp.add(read.getEmails().get(i));
					}
				}
				System.out.println("Entrou nos falsos Negativos");

			}

		}
		System.out.println("Falsos Positivos"+falsospositivos+"Falsos Negativos"+falsosnegativos);

	}

	public void start() {
		g.showGUI();
		generatweigths();
		choosetype();
		showfpfn();

	}
	
	/**
	 * Gerar valores aleatórios para as rules entre -5 e 5
	 * @return
	 */
	public void generatweigths() {
		
		Random r= new Random();
		for(int i=0; i<read.getRules().size();i++) {
			read.getRules().get(i).setWeight((int) (Math.random() * (5- (-5)) + (-5)));
			System.out.println("Weigth:"+read.getRules().get(i).getWeight());
		}
		
	}

	public GUI getGUI() {
		return g;
	}
//
//	public void setWindow(Window window) {
//		this.window = window;
//	}

	public Read getRead() {
		return read;
	}

	public void setRead(Read read) {
		this.read = read;
	}

	public static void setInstance(Mail instance) {
		Mail.instance = instance;
	}

	public ArrayList<Email> getFp() {
		return fp;
	}

	public void setFp(ArrayList<Email> fp) {
		this.fp = fp;
	}

	public int getFalsospositivos() {
		return falsospositivos;
	}

	public void setFalsospositivos(int falsospositivos) {
		this.falsospositivos = falsospositivos;
	}

	public ArrayList<Email> getFn() {
		return fn;
	}

	public void setFn(ArrayList<Email> fn) {
		this.fn = fn;
	}

	public int getFalsosnegativos() {
		return falsosnegativos;
	}

	public void setFalsosnegativos(int falsosnegativos) {
		this.falsosnegativos = falsosnegativos;
	}

	
}
