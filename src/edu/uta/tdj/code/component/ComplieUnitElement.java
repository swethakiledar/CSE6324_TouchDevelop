package edu.uta.tdj.code.component;

import java.awt.Graphics;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.PackageDeclaration;

import edu.uta.tdj.code.file.ISave;
import edu.uta.tdj.code.project.PackageElement;

public class ComplieUnitElement extends Element implements ISave {

	private ArrayList<Element> childsList = new ArrayList<Element>();
	private ClassElement publicClassElement;

	public ArrayList<Element> getChildsList() {
		return childsList;
	}

	public void setChildsList(ArrayList<Element> childsList) {
		this.childsList = childsList;
	}

	private PackageElement packageElement;
	private PackageDeclaration pDeclaration;

	private String path = "";

	public ComplieUnitElement(AST ast) {
		super(ast);
		// this.ast = AST.newAST(AST.JLS4);
		astNode = this.ast.newCompilationUnit();
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
		Modifier modifierKeyword = (Modifier) ((TypeDeclaration) (element)
				.getAstNode()).modifiers().get(0);
		if (modifierKeyword.toString().equalsIgnoreCase("public")) {
			setPublicClass((ClassElement) element);
		}
		((CompilationUnit) astNode).types().add(element.getAstNode());
		childsList.add(element);
	}

	public void setParent(PackageElement pe) {
		this.packageElement = pe;
		path = pe.getPath() + "/" + name + ".java";
		System.out.println(path);
		pDeclaration = ast.newPackageDeclaration();
		pDeclaration.setName(ast.newSimpleName(pe.getName()));
		((CompilationUnit) astNode).setPackage(pDeclaration);
	}

	public PackageElement getPackage() {
		return this.packageElement;
	}

	@Override
	public void removeChild(Element element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setModifiers(ModifierKeyword modifiers) {
		// TODO Auto-generated method stub

	}

	@Override
	public Element getSelectedElement(int x_in, int y_in) {
		// if (this.isInelement(x_in, y_in)) {
		// return null;
		// } else {
		for (Element fe : childsList) {
			Element element = fe.getSelectedElement(x_in, y_in);
			if (element != null)
				return element;
		}

		// }
		return null;
	}
	
	public void unSelected() {
		selected = false;
		for (Element fe : childsList) {
			unSelected();
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawString(pDeclaration.toString(), x, y);
		for (Element e : childsList) {
			e.draw(g);
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(pDeclaration.toString());
		sb.append(System.lineSeparator());
		for (Element e : childsList) {
			sb.append(e.getAstNode().toString());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	@Override
	public void save() {
		fs.saveFile(path, toString());
	}

}
