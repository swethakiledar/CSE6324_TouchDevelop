package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.FieldForm;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class FieldElement extends Element {

	private VariableDeclarationFragment vdf;

	public FieldElement(AST ast) {
		super(ast);
		vdf = ast.newVariableDeclarationFragment();
		astNode = ast.newFieldDeclaration(vdf);
		setHeight(20);
		this.form = new FieldForm();
		form.setElement(this);
	}

	public void setName(String name) {
		vdf.setName(ast.newSimpleName(name));
		this.name = name;
	}

	public void setType(String type) {
		((FieldDeclaration) astNode).setType(ast.newSimpleType(ast
				.newSimpleName(type)));
	}

	public void setType(org.eclipse.jdt.core.dom.Type type) {
		((FieldDeclaration) astNode).setType(type);
	}

	public org.eclipse.jdt.core.dom.Type getType() {
		org.eclipse.jdt.core.dom.Type type = ((FieldDeclaration) astNode)
				.getType();
		return type;
	}

	@Override
	public void setModifiers(ModifierKeyword modifiers) {
		((FieldDeclaration) astNode).modifiers()
				.add(ast.newModifier(modifiers));
	}

	public List getModifierKeyword() {
		return ((FieldDeclaration) astNode).modifiers();
	}

	public ASTNode getAstNode() {
		return astNode;
	}

	@Override
	public void draw(Graphics g) {
		this.width = toString().length() * 5;
		super.draw(g);
		g.setColor(Color.red);
		g.drawString(toString(), x, y + 20);
		g.setColor(Color.black);
	}

	public String toString() {
		return astNode.toString();
	}

	@Override
	public void addChild(Element element) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return pcComputer.getButtons(this);
	}

}
