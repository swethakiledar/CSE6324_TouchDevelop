package edu.uta.tdj.code.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.eclipse.jdt.core.dom.AST;
import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.file.ISave;
import edu.uta.tdj.factory.CodeFactory;

public class ProjectElement implements ISave {

	private ArrayList<PackageElement> packageList = new ArrayList<PackageElement>();

	private String projectName = "";

	private String path = "";
	private AST ast;

	private ComplieUnitElement mainclassString;

	private CodeFactory codeFactory = new CodeFactory();

	public ProjectElement(String path, String name) {
		this.projectName = name;
		this.path = path +"/"+ name;
		this.ast = AST.newAST(AST.JLS4);
		codeFactory.setAST(ast);
	}

	/**
	 * for open a project
	 * */
	public ProjectElement(String pathName) {
		this.path = pathName;
		String[] tmp = pathName.split("/");
		this.projectName = tmp[tmp.length - 1];
		this.ast = AST.newAST(AST.JLS4);
		codeFactory.setAST(ast);
	}

	public void setMainClass(ComplieUnitElement mainclass) {
		this.mainclassString = mainclass;
	}

	public ComplieUnitElement getMainclass() {
		return this.mainclassString;
	}

	public CodeFactory getCodeFactory() {
		return codeFactory;
	}

	public void addPackage(PackageElement packageElement) {
		this.packageList.add(packageElement);
		packageElement.setPath(this.path);
		packageElement.setAST(ast);
		packageElement.setProjectElement(this);
	}

	public ArrayList<PackageElement> getPackages() {
		return this.packageList;
	}

	public String getPath() {
		return this.path;
	}

	public String getName() {
		return this.projectName;
	}

	public void setName(String name) {
		this.projectName = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String toXML() {
		String headString = "<project>";
		String endString = "</project>";
		String name = "<name>";
		String nameEnd = "</name>";
		String path = "<path>";
		String pathEnd = "</path>";
		String packages = "<packages>";
		String packagesEnd = "</packages>";
		StringBuilder sb = new StringBuilder();
		sb.append(headString);
		sb.append(name);
		sb.append(this.projectName);
		sb.append(nameEnd);
		sb.append(path);
		sb.append(this.path);
		sb.append(pathEnd);
		sb.append(packages);
		for (PackageElement packageElement : packageList) {
			sb.append(packageElement.toXML());
		}
		sb.append(packagesEnd);
		sb.append(endString);
		return sb.toString();
	}

	@Override
	public void save() {
		
		fs.createFolder(this.path);
		for (int i = 0; i < packageList.size(); i++) {
			ISave save = (ISave) packageList.get(i);
			save.save();
		}
		
		FileWriter fWriter =null;
		try {
			fWriter = new FileWriter(new File(this.path + "/project.project"));
			fWriter.write(this.toXML());
			fWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
