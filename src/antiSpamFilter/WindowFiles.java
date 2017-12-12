package antiSpamFilter;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WindowFiles {
	private JPanel filespanel;

	private JLabel rules;
	private JLabel ham;
	private JLabel spam;
	private JTextField file1;
	private String pathtorules = "C://Users//msi//git////ES1-2017-IC1-73//src//Group73//rules.cf";
	private JTextField file2;
	private String pathtoham = "C://Users//msi//git//ES1-2017-IC1-73//src//Group73//ham.log";
	private JTextField file3;
	private String pathtospam = "C://Users//msi//git//ES1-2017-IC1-73//src//Group73//spam.log";

	/**
	 * Cria a interface e abre os caminhos para os ficheiros pretendidos
	 */
	public void files() {
		filespanel = new JPanel();
		filespanel.setLayout(new GridLayout(3, 2));
		rules = new JLabel("Rules");
		file1 = new JTextField();
		file1.setText(pathtorules);
		ham = new JLabel("Email");
		file2 = new JTextField();
		file2.setText(pathtoham);
		spam = new JLabel("Spam");
		file3 = new JTextField();
		file3.setText(pathtospam);
		// Falta por os caminhos e criar os actionlisteners
		filespanel.add(rules);
		filespanel.add(file1);
		filespanel.add(ham);
		filespanel.add(file2);
		filespanel.add(spam);
		filespanel.add(file3);

		Mail.getInstance().getWindow().getFrame().add(filespanel);
	}

	public String getPathtorules() {
		return pathtorules;
	}

	public void setPathtorules(String pathtorules) {
		this.pathtorules = pathtorules;
	}

	public String getPathtoham() {
		return pathtoham;
	}

	public void setPathtoham(String pathtoham) {
		this.pathtoham = pathtoham;
	}

	public String getPathtospam() {
		return pathtospam;
	}

	public void setPathtospam(String pathtospam) {
		this.pathtospam = pathtospam;
	}
	
}
