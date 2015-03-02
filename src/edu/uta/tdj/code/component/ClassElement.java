package edu.uta.tdj.code.component;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * @author Fuqiang Zhang
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.ClassForm;

/**
 * For a class
 * 
 * */

public class ClassElement extends Element {

	private String modifiedString = "";

	public ClassElement(AST ast) {
		super(ast);
		this.astNode = ast.newTypeDeclaration();
		this.setX(50);
		this.setY(50);
		this.form = new ClassForm();
		setHeight(100);
		defaultHeight = 100;
	}

	public void setName(String name) {
		this.name = name;
		((TypeDeclaration) astNode).setName(ast.newSimpleName(name));
	}

	@Override
	public void addChild(Element element) {
		((TypeDeclaration) astNode).bodyDeclarations()
				.add(element.getAstNode());
		// ((TypeDeclaration) astNode).
		childArrayList.add(element);
		element.setParent(this);
		element.setX(x + 20);
		element.setY(y + getHeight() - 30);
		setHeight(getHeight() + element.getHeight());
		reSort();
	}

	/**
	 * should be changed and split into parts
	 * */
	@Override
	public void setModifiers(ModifierKeyword modifiers) {
		((TypeDeclaration) astNode).modifiers().add(ast.newModifier

		(modifiers));
		modifiedString = modifiedString + " " + modifiers.toString();
	}

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return pcComputer.getButtons(this);
	}

	@Override
	public ASTNode getAstNode() {
		return astNode;
	}

	@Override
	public void draw(Graphics g) {
		this.width = name.length() * 5;
		super.draw(g);
		g.setColor(Color.black);
		g.drawString(this.toString(), x, y + 20);

		for (Element me : childArrayList) {
			me.draw(g);
		}
		g.drawString("}", x, getHeight() + y - 10);

	}

	public String toString() {
		return this.modifiedString + "  class  " + name + "  {";
	}

}