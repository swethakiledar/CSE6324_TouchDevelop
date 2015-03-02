package edu.uta.tdj.code.project;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.file.FileSaver;
import edu.uta.tdj.code.file.ISave;
import edu.uta.tdj.controller.ProjectController;
import edu.uta.tdj.factory.CodeFactory;

public class ProjectElement implements ISave{

	private ArrayList<PackageElement> packageList = new ArrayList<PackageElement>();

	private String projectName = "";

	private String path = "";

	private AST ast;
	
	private ComplieUnitElement mainclassString;

	private CodeFactory codeFactory = new CodeFactory();

	public ProjectElement(String path, String name) {
		this.projectName = name;
		this.path = path + name;
		this.ast = AST.newAST(AST.JLS4);
		codeFactory.setAST(ast);
	}
	
	public void setMainClass(ComplieUnitElement mainclass){
		this.mainclassString = mainclass;
	}
	
	public ComplieUnitElement getMainclass(){
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

	@Override
	public void save() {
		fs.createFolder(this.path);
		for (int i = 0; i < packageList.size(); i++) {
			ISave save = (ISave) packageList.get(i);
			save.save();
		}
	}
}
