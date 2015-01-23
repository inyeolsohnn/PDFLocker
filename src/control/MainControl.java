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

import com.itextpdf.text.DocumentException;

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
	public void lockPDF(int count, File selectedFile, String outputLocation, String ownerPassword, String optionCombo) throws IOException, DocumentException {
		locker.lockPDF(count, selectedFile, outputLocation, ownerPassword, optionCombo);
	
	}

	public void test() {
		System.out.println("test");
	}

	

	
}
