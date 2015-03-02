package edu.uta.tdj.code.component;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.CellEditor;
import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.PackageDeclaration;

import edu.uta.tdj.code.file.ISave;
import edu.uta.tdj.code.project.PackageElement;
import edu.uta.tdj.code.proposal.ProposalButtonFactory;

public class ComplieUnitElement extends Element implements ISave {

	private ClassElement publicClassElement;

	public void setChildsList(ArrayList<Element> childsList) {
		this.childArrayList = childsList;
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
		childArrayList.add(element);
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
	public void setModifiers(ModifierKeyword modifiers) {
		// TODO Auto-generated method stub

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

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(pDeclaration.toString());
		sb.append(System.lineSeparator());
		for (Element e : childArrayList) {
			sb.append(e.getAstNode().toString());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	@Override
	public void save() {
		this.setName(publicClassElement.getName());
		fs.deleteFile(path);
		path = path = packageElement.getPath() + "/" + publicClassElement.getName() + ".java";
		fs.saveFile(path, toString());
	}

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		// TODO Auto-generated method stub
		return null;
	}

}
