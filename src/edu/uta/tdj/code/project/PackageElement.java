package edu.uta.tdj.code.project;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.AST;
import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.file.ISave;

public class PackageElement extends ProjectNode implements ISave {

	private ArrayList complieUnitArrayList = new ArrayList();

	private String name = "";

	private String path = "";

	private ProjectElement projectElement;

	private AST ast;

	public PackageElement(String project, String name) {
		this(name);
		this.setPath(project);
	}

	public PackageElement(ProjectElement project, String name) {
		this(project.getPath(), name);
	}

	public PackageElement(String name) {
		this.name = name;
	}

	public void addComplieUnit(ComplieUnitElement cue) {
		this.complieUnitArrayList.add(cue);
		cue.setParent(this);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String projectpath) {
		this.path = projectpath + "/src/" + name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ComplieUnitElement> getComplieUnitArrayList() {
		return complieUnitArrayList;
	}

	public void setComplieUnitArrayList(ArrayList complieUnitArrayList) {
		this.complieUnitArrayList = complieUnitArrayList;
	}

	public void setAST(AST ast) {
		this.ast = ast;
	}

	public ProjectElement getProjectElement() {
		return projectElement;
	}

	public void setProjectElement(ProjectElement projectElement) {
		this.projectElement = projectElement;
	}

	@Override
	public void save() {
		fs.createFolder(getPath());
		for (int i = 0; i < complieUnitArrayList.size(); i++) {
			ISave save = (ISave) complieUnitArrayList.get(i);
			save.save();
		}
	}

}
