package control;

import java.io.File;
import java.io.IOException;

import com.itextpdf.text.DocumentException;

public abstract class Controller {
	public  abstract void test();
	
	public void lockPDF(int count, File selectedFile, String outputLocation, String ownerPassword, String optionCombo)
			throws IOException, DocumentException {
		// TODO Auto-generated method stub
		
	}
}
