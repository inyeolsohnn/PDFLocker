package control;

import java.io.File;

import model.PDFLocker;
import view.PDFLockerView;
import java.nio.channels.FileChannel;
import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class MainControl extends Controller {
	private PDFLockerView lockerView;
	private PDFLocker locker;

	public MainControl() {
		locker = new PDFLocker();
		lockerView = new PDFLockerView(this);
	}

	public static void main(String[] args) {
		MainControl controller = new MainControl();
	}

	@Override
	public void lockFiles(int count, File selectedFile, String outputLocation) throws IOException {
		File source = selectedFile;
		String fileName = source.getName().substring(0,
				source.getName().lastIndexOf('.'));
		System.out.println(fileName);
		String outputName = outputLocation;
		
		for (int i = 0; i < count; i++) {
			String tempName = outputName + "\\" + fileName + i + ".pdf";
			System.out.println(tempName);
			File dest = new File(tempName);
			copyFileUsingFileChannels(source, dest);

		}
	}

	public void test() {
		System.out.println("test");
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

		public namePassword(String name, String pass) {
			this.name = name;
			this.password = pass;
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

	}
}
