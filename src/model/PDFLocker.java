package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;

public class PDFLocker {
	private static final String[] smallLetters = { "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z" };
	private static final String[] bigLetters = { "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };
	private static Random random = new Random();
	private StringBuilder sb;

	private String fileLocation;
	private String outputLocation;
	private int count;

	public PDFLocker() {

	}

	public void lockPDF(int count, File selectedFile, String outputLocation,
			String ownerPassword, String optionCombo) throws IOException,
			DocumentException {
		File source = selectedFile;

		String fileName = source.getName().substring(0,
				source.getName().lastIndexOf('.'));
		System.out.println(fileName);
		String outputName = outputLocation;
		String csv = outputLocation + "\\"+fileName+"_passwords.csv";
		CSVWriter writer = new CSVWriter(new FileWriter(csv));
		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[] { "Owner password", ownerPassword });

		for (int i = 0; i < count; i++) {
			sb = new StringBuilder();
			PdfReader pdfReader = new PdfReader(selectedFile.getAbsolutePath());

			String tempName = outputName + "\\" + fileName + i + ".pdf";
			System.out.println(tempName);
			File dest = new File(tempName);
			FileOutputStream fos = new FileOutputStream(dest);
			PdfStamper pdfs = new PdfStamper(pdfReader, fos);
			String password = generateLock();
			if (ownerPassword.equals("")) {
				if (optionCombo.equals("Allow user pdf options")) {
					pdfs.setEncryption(password.getBytes(), null,
							PdfWriter.ALLOW_PRINTING
									| PdfWriter.ALLOW_MODIFY_CONTENTS
									| PdfWriter.ALLOW_COPY
									| PdfWriter.ALLOW_ASSEMBLY
									| PdfWriter.ALLOW_FILL_IN
									| PdfWriter.ALLOW_MODIFY_ANNOTATIONS
									| PdfWriter.ALLOW_SCREENREADERS,
							PdfWriter.STANDARD_ENCRYPTION_128);
				} else {
					pdfs.setEncryption(password.getBytes(), null, 0,
							PdfWriter.STANDARD_ENCRYPTION_128);
				}

			} else {
				if (optionCombo.equals("Allow user pdf options")) {
					pdfs.setEncryption(password.getBytes(),
							ownerPassword.getBytes(), PdfWriter.ALLOW_PRINTING
									| PdfWriter.ALLOW_MODIFY_CONTENTS
									| PdfWriter.ALLOW_COPY
									| PdfWriter.ALLOW_ASSEMBLY
									| PdfWriter.ALLOW_FILL_IN
									| PdfWriter.ALLOW_MODIFY_ANNOTATIONS
									| PdfWriter.ALLOW_SCREENREADERS,
							PdfWriter.STANDARD_ENCRYPTION_128);
				} else {
					pdfs.setEncryption(password.getBytes(),
							ownerPassword.getBytes(), 0,
							PdfWriter.STANDARD_ENCRYPTION_128);
				}

			}
			pdfs.close();

			data.add(new String[] { fileName + i, password });
		}
		writer.writeAll(data);

		writer.close();
		JOptionPane.showMessageDialog(null, "Task Complete");
	}

	public String generateLock() {
		for (int i = 0; i < 12; i++) {

			int j = random.nextInt(3);
			if (j == 0) {
				int k = random.nextInt(26);
				sb.append(smallLetters[k]);
			}
			if (j == 1) {
				int k = random.nextInt(26);
				sb.append(bigLetters[k]);
			}
			if (j == 2) {
				int k = random.nextInt(10);
				sb.append(String.valueOf(k));
			}

		}
		return sb.toString();
	}

	private static void copyFileUsingFileChannels(File source, File dest)

	throws IOException {

		FileChannel inputChannel = null;
		FileChannel outputChannel = null;

		try {

			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());

		} finally {

			inputChannel.close();
			outputChannel.close();
		}

	}

	class namePassword {
		private String name;
		private String password;
		private String[] pair = new String[2];

		public namePassword(String name, String pass) {
			this.name = name;
			this.password = pass;
			pair[0] = this.name;
			pair[1] = this.password;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String[] toStringArray() {
			return this.pair;

		}

	}

}
