package edu.uta.tdj.code.project;

import java.util.ArrayList;


import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.file.ISave;

public class PackageElement  implements ProjectNode {
	private ArrayList complieUnitArrayList = new ArrayList();

	private String name = "";

	private String path = "";

	private ProjectElement projectElement;


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


	public ProjectElement getProjectElement() {
		return projectElement;
	}

	public void setProjectElement(ProjectElement projectElement) {
		this.projectElement = projectElement;
	}

	public String toXML() {
		String head = "<package>";
		String end = "</package>";
		String name = "<name>";
		String nameEnd = "</name>";
		String path = "<path>";
		String pathEnd = "</path>";
		StringBuilder sb = new StringBuilder();
		sb.append(head);
		sb.append(name);
		sb.append(this.name);
		sb.append(nameEnd);
		sb.append(path);
		sb.append(this.path);
		sb.append(pathEnd);
		sb.append(end);
		return sb.toString();
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
