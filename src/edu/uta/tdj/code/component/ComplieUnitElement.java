package edu.uta.tdj.code.component;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import edu.uta.tdj.code.file.ISave;
import edu.uta.tdj.code.project.PackageElement;
import edu.uta.tdj.code.proposal.ProposalButtonFactory;

public class ComplieUnitElement extends Element implements ISave {

	private ClassElement publicClassElement;

	public void setChildsList(ArrayList<Element> childsList) {
		this.childArrayList = childsList;
	}

	private PackageElement packageElement;
	private String pDeclaration;

	private String path = "";

	public ComplieUnitElement() {
		setX(50);
		setY(50);
	}

	public void setPublicClass(ClassElement ce) {
		publicClassElement = ce;
	}

	public ClassElement getPublicClass() {
		return this.publicClassElement;
	}

	@Override
	public void addChild(Element element) {
		element.setParent(this);
		if (((ClassElement)element).getAccess().trim().equalsIgnoreCase("public")) {
			setPublicClass((ClassElement) element);
		}
		childArrayList.add(element);
	}

	public void setParent(PackageElement pe) {
		this.packageElement = pe;
		path = pe.getPath() + "/" + name + ".java";
		pDeclaration = "package "+pe.getName() + ";";
	}

	public PackageElement getPackage() {
		return this.packageElement;
	}


	@Override
	public Element getSelectedElement(int x_in, int y_in) {
		for (Element fe : childArrayList) {
			Element element = fe.getSelectedElement(x_in, y_in);
			if (element != null)
				return element;
		}
		return null;
	}

	@Override
	public void draw(Graphics g) {
		g.drawString(pDeclaration.toString(), x, y);
		for (Element e : childArrayList) {
			e.draw(g);
		}
	}
	public String toCode(){
		return toString();
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(pDeclaration.toString());
		sb.append(System.lineSeparator());
		for (Element e : childArrayList) {
			sb.append(e.toCode());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	@Override
	public void save() {
		this.setName(publicClassElement.getName());
		fs.deleteFile(path);
		path = path = packageElement.getPath() + "/" + publicClassElement.getName() + ".java";
		fs.saveFile(path, toCode());
	}

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		// TODO Auto-generated method stub
		return null;
	}

}
