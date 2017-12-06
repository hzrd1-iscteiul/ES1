package Group73;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WindowAutomatic {

	// BorderLayout
	private JPanel automaticpanel;
	// automatico estará no north
	private JLabel automatic;
	// Center, GridLayout(1,2)
	private JPanel centera;
	// GridLayout (2,2)
	private JPanel automaticboards;
	private JPanel an;
	private JPanel ab;
	private JLabel rulesa;
	private JLabel weigtha;
	private JTextArea rulesautomatic;
	private JTextArea weigthsautomatic;
	// FlowLayout
	private JPanel buttons;
	private JButton automaticvalues;
	private JButton resultsa;
	// South, FlowLayout
	private JPanel automaticresults;
	private JLabel falsepositivesa;
	private JTextField falsepositivesvaluea;
	private JLabel falsenegativesa;
	private JTextField falsenegativesvaluea;

	private String weight;
	

	/**
	 * Cria a interface automatica
	 */
	public void automatic() {

		automaticpanel = new JPanel();
		automaticpanel.setLayout(new BorderLayout());
		automatic = new JLabel("Automatic");
		automaticpanel.add(automatic, BorderLayout.NORTH);

		centera = new JPanel();
		centera.setLayout(new GridLayout(1, 2));

		automaticboards = new JPanel();
		automaticboards.setLayout(new GridLayout(2, 2));
		rulesa = new JLabel("Rules");
		weigtha = new JLabel("Weigth");

		rulesautomatic = new JTextArea();
		Mail.getInstance().getRead().readRules();
		rulesautomatic.setText(Mail.getInstance().getRead().names());
		weigthsautomatic = new JTextArea();
		weigthsautomatic.setEditable(true);
		weigthsautomatic.setText(Mail.getInstance().getRead().weights());

		automaticboards.add(rulesa);
		automaticboards.add(weigtha);
		automaticboards.add(rulesautomatic);
		automaticboards.add(weigthsautomatic);

		buttons = new JPanel();
		buttons.setLayout(new GridLayout(2, 1));
		automaticvalues = new JButton("Generate Values");
		resultsa = new JButton("Show Results");
		buttons.add(automaticvalues);
		buttons.add(resultsa);

		centera.add(automaticboards);
		centera.add(buttons);

		automaticresults = new JPanel();
		automaticresults.setLayout(new FlowLayout());
		falsepositivesa = new JLabel("False Positives");
		falsepositivesvaluea = new JTextField();
		falsenegativesa = new JLabel("False Negatives");
		falsenegativesvaluea = new JTextField();
		automaticresults.add(falsepositivesa);
		automaticresults.add(falsepositivesvaluea);
		automaticresults.add(falsenegativesa);
		automaticresults.add(falsenegativesvaluea);

		automaticpanel.add(centera, BorderLayout.CENTER);
		automaticpanel.add(automaticresults, BorderLayout.SOUTH);

		Mail.getInstance().getWindow().getFrame().add(automaticpanel);
	}
}
