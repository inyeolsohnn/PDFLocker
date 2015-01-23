package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;



public class PDFLocker   {
	private static final String[] smallLetters = { "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z" };
	private static final String[] bigLetters = { "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };
	private static Random random = new Random();
	private static StringBuilder sb = new StringBuilder();
	
	private String fileLocation;
	private String outputLocation;
	private int count;
	
	public PDFLocker(){
		
	}

	public void lockPDF(int count, String fileLocation, String outputLocation) {
		ArrayList fileList = new ArrayList<String>();
	

	}

	private void multiplyFiles(int count, String fileLocation,
			String outputLocation, ArrayList<String> fList) {
		File source = new File(fileLocation);
		for (int i = 0; i < count; i++) {
			

		}

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

}
