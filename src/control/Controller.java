package control;

import java.io.File;
import java.io.IOException;

public abstract class Controller {
	public  abstract void test();
	public abstract void lockFiles(int count, File selectedFile, String outputLocation) throws IOException;
}
