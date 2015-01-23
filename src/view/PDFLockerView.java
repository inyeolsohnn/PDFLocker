package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import control.Controller;

public class PDFLockerView extends JPanel implements ActionListener {
	private JFrame mainFrame;
	private Container p;
	private Controller control;
	private JButton fileLocationButton, fileOutButton, lockButton;
	private JTextField fileLocation, fileOut, copyNumber;
	private JLabel fileLocationL, fileOutL, copyNumberL;
	private JFileChooser fc, outfc;
	private File selectedFile;
	private String outputLocation;
	private JLabel opLabel;
	private JTextField opField;
	private JComboBox optionCombo;

	public PDFLockerView(Controller control) {
		this.control = control;

		fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setAcceptAllFileFilterUsed(false);
		fc.addChoosableFileFilter(new FileFilter() {

			public String getDescription() {
				return "PDF Documents (*.pdf)";
			}

			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					return f.getName().toLowerCase().endsWith(".pdf");
				}
			}
		});
		outfc = new JFileChooser();
		outfc.setCurrentDirectory(new java.io.File("."));
		outfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //
		// disable the "All files" option.
		outfc.setAcceptAllFileFilterUsed(false);
		startGUI();
	}

	private void startGUI() {
		mainFrame = new JFrame("PDFLocker");

		p = mainFrame.getContentPane();

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(true);

		p = new JPanel(new GridLayout(5, 3));

		// start setup file selector
		fileLocationL = new JLabel("File Location:");
		p.add(fileLocationL); // added filelocation label
		fileLocation = new JTextField(100);
		fileLocation.setEditable(false);
		p.add(fileLocation); // added file location textfield
		fileLocationButton = new JButton("Select File");
		fileLocationButton.addActionListener(this);
		p.add(fileLocationButton); // added file location button
		// finish setup file selector
		// start output location setup
		fileOut = new JTextField(100);
		fileOut.setEditable(false);
		fileOutL = new JLabel(" Output Location: ");
		p.add(fileOutL);
		p.add(fileOut);
		fileOutButton = new JButton("Select output location");
		fileOutButton.addActionListener(this);
		p.add(fileOutButton);
		// finish output location set up

		// start copy number set up
		copyNumberL = new JLabel("Choose number of files to copy and lock");
		p.add(copyNumberL);
		copyNumber = new JTextField(20);
		p.add(copyNumber);
		p.add(new JPanel());
		// finish copy number set up

		opLabel = new JLabel("Owner password");
		opField = new JTextField(30);
		String[] optionStrings = { "Allow user pdf options",
				"Block user pdf options" };
		optionCombo = new JComboBox(optionStrings);

		p.add(opLabel);
		p.add(opField);
		p.add(optionCombo);
		p.add(new JPanel());
		lockButton = new JButton("Lock Files");
		p.add(lockButton);
		p.add(new JPanel());
		lockButton.addActionListener(this);

		mainFrame.add(p);
		mainFrame.setSize(800, 200);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);

		// Create a file chooser

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == fileLocationButton) {

			int returnValue = fc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				selectedFile = fc.getSelectedFile();
				fileLocation.setText(fc.getSelectedFile().getAbsolutePath());
			}
		}

		else if (e.getSource() == fileOutButton) {
			System.out.println("out");

			int returnValue = outfc.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				System.out.println("getCurrentDirectory(): "
						+ outfc.getCurrentDirectory());
				outputLocation = outfc.getSelectedFile().getAbsolutePath();
				fileOut.setText(outfc.getSelectedFile().getAbsolutePath());
			}

		} else if (e.getSource() == lockButton) {
			int numberOfCopies = 0;

			if (fileLocation.getText().equals("")
					|| copyNumber.getText().equals("")
					|| fileOut.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Verify input fields");
			} else {
				try {
					numberOfCopies = Integer.parseInt(copyNumber.getText());
					if (numberOfCopies <= 0) {
						throw new Exception(
								"number of copies need to be positive integer");
					}
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null,
							"number of copies need to be positive integer");
				}
				try {
					this.control.lockPDF(numberOfCopies, selectedFile,
							outputLocation, opField.getText(), (String)optionCombo.getSelectedItem());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}
}
