package view;

import javax.swing.JFrame;

public class PDFLockerView {
	private JFrame mainFrame;
	
	public PDFLockerView(){
		startGUI();
	}
	private void startGUI(){
		mainFrame = new JFrame("PDFLocker");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(400,800);
		mainFrame.setVisible(true);
	}
}
