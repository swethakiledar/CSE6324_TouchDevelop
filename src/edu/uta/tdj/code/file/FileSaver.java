package edu.uta.tdj.code.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import edu.uta.tdj.code.component.ClassElement;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class FileSaver {

	private String filePath;
	private File file;

	public boolean ifExistFile() {
		return file.exists();
	}

	public void saveFile(ClassElement ce) {
		// this.saveFile(ce.getAstNode().toString());
	}

	public static void saveFile(String path, String input) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(input);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean createFolder(String pathname) {
		File folder = new File(pathname);
		if (!folder.exists()) {
			return folder.mkdirs();
		}
		return false;
	}

	public static void deleteFile(String path){
		File file = new File(path);
		file.delete();
	}

	public static String readFile(File file) {
		StringWriter sw = null;
		FileReader fr = null;
		try {
			fr = new FileReader(file);
			int i;
			// StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(fr);

			String buffer = null;

			sw = new StringWriter();
			BufferedWriter bw = new BufferedWriter(sw);
			while ((buffer = br.readLine()) != null) {
				bw.write(buffer);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sw.toString();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
		file = new File(filePath);
	}
}
