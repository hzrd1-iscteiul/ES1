package Group73;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class WindowManual {
	// BorderLayout
		private JPanel manualpanel;
		// manual estará no north
		private JLabel manual;
		// Center, GridLayout(1,2)
		private JPanel centerm;
		// GridLayout (2,2)
		private JPanel manualboards;
		private JPanel mn;
		private JPanel mb;
		private  JPanel mboards;
		private JLabel rulesm;
		private JLabel weigthm;
		private JTextArea rulesmanual;
		private JTextArea weigthsmanual;
		// Button
		private JButton resultsm;
		// South, FlowLayout
		private JPanel manualresults;
		private JLabel falsepositivesm;
		private JTextField falsepositivesvaluem;
		private JLabel falsenegativesm;
		private JTextField falsenegativesvaluem;
		
		
		
		





		/**
		 * Cria a interface Manual
		 */
		public void manual() {

			manualpanel = new JPanel();
			mn=new JPanel();
			mn.setLayout(new GridLayout(1,2));
			mb=new JPanel();
			mb.setLayout(new GridLayout(1,2));
			mboards=new JPanel();
			manualpanel.setLayout(new BorderLayout());
			manual = new JLabel("Manual");
			manualpanel.add(manual, BorderLayout.NORTH);

			centerm = new JPanel();
			centerm.setLayout(new GridLayout(1, 2));

			manualboards = new JPanel();
			manualboards.setLayout(new BorderLayout());
			rulesm = new JLabel("Rules");
			weigthm = new JLabel("Weigth");

			// Insere a lista das rules
			rulesmanual = new JTextArea();
			Mail.getInstance().getRead().readRules();
			rulesmanual.setText(Mail.getInstance().getRead().names());

			// Obtem a lista dos pesos
			weigthsmanual = new JTextArea();
			weigthsmanual.setText(Mail.getInstance().getRead().weights());
			weigthsmanual.setEditable(true);
			weigthsmanual.addAncestorListener(new AncestorListener() {
				

				@Override
				public void ancestorAdded(AncestorEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void ancestorMoved(AncestorEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void ancestorRemoved(AncestorEvent arg0) {
					// TODO Auto-generated method stub
					
					
				}
			});
			JScrollPane sboards = new JScrollPane(mb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sboards.setViewportView(mb);
			mb.getAutoscrolls();

		//Labels
			mn.add(rulesm);
			mn.add(weigthm);
		//Text Areas
			mb.add(rulesmanual);
			mb.add(weigthsmanual);
			mboards.add(mb);
			mboards.add(sboards);
		// Add all to the big panel
			manualboards.add(mn,BorderLayout.NORTH);
			manualboards.add(mboards,BorderLayout.CENTER);
		

			manualresults = new JPanel();
			manualresults.setLayout(new FlowLayout());
			falsepositivesm = new JLabel("False Positives");
			falsepositivesvaluem = new JTextField();
			falsenegativesm = new JLabel("False Negatives");
			falsenegativesvaluem = new JTextField();
			manualresults.add(falsepositivesm);
			manualresults.add(falsepositivesvaluem);
			manualresults.add(falsenegativesm);
			manualresults.add(falsenegativesvaluem);

// Coloca os valores dos falsos positivos e falsos negativos ao clicar no jbutton
			resultsm = new JButton("Show Results");
			resultsm.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
				    Mail.getInstance().showfpfn();
				    falsepositivesvaluem.setText(Integer.toString(Mail.getInstance().getFalsospositivos()));
				    falsenegativesvaluem.setText(Integer.toString(Mail.getInstance().getFalsosnegativos()));
				  } 
				} );
			centerm.add(manualboards);
			centerm.add(resultsm);

			manualpanel.add(centerm, BorderLayout.CENTER);
			manualpanel.add(manualresults, BorderLayout.SOUTH);

			Mail.getInstance().getWindow().getFrame().add(manualpanel);
		}

}
