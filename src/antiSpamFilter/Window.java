package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

public class Window {

	private JFrame frame;
	private WindowFiles windowFiles=new WindowFiles();
	private WindowManual windowManual= new WindowManual();
	private WindowAutomatic windowAutomatic= new WindowAutomatic();

	public void open() {
		frame = new JFrame("Spam Filter");
		frame.setLayout(new GridLayout(3, 1));
		windowFiles.files();
		windowManual.manual();
		windowAutomatic.automatic();
		frame.pack();
		frame.setVisible(true);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public JFrame getFrame() {
		return frame;
	}

	public WindowFiles getWindowFiles() {
		return windowFiles;
	}

	public void setWindowFiles(WindowFiles windowFiles) {
		this.windowFiles = windowFiles;
	}

	public WindowManual getWindowManual() {
		return windowManual;
	}

	public void setWindowManual(WindowManual windowManual) {
		this.windowManual = windowManual;
	}

	public WindowAutomatic getWindowAutomatic() {
		return windowAutomatic;
	}

	public void setWindowAutomatic(WindowAutomatic windowAutomatic) {
		this.windowAutomatic = windowAutomatic;
	}

	

}
