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
 * 2015 2015年2月22日
 * 
 * @author Fuqiang Zhang
 */

public class FileSaver {

	private String filePath;// 属性尽量选择private，别问我为啥，我也不知道，就是任性！
	private File file;

	public boolean ifExistFile() {
		return file.exists();
	}

	public void saveFile(ClassElement ce) {
		this.saveFile(ce.getAstNode().toString());
	}

	private void saveFile(String input) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
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

	// 读取文件并返回string格式的文件文本
	private String readFile() {
		StringWriter sw = null;
		try {
			FileReader fr = new FileReader(file);
			int i;
			// StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(fr);

			String buffer = null;

			sw = new StringWriter();
			BufferedWriter bw = new BufferedWriter(sw);
			while ((buffer = br.readLine()) != null) {
				// sb.append(buffer);
				bw.write(buffer);
				bw.newLine();
			}
			// System.out.println(bw.toString());
			bw.flush();
			bw.close();
			// System.out.println(sw.toString());

			//
			// while((i = fr.read())!=-1){
			// char c = (char)i;
			// sb.append(c);
			// }
			// System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
