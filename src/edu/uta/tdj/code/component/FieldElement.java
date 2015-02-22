package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

public class FieldElement extends Element {

	private FieldDeclaration astNode;
	private VariableDeclarationFragment vdf;
	
	public FieldElement(AST ast) {
		super(ast);
		vdf = ast.newVariableDeclarationFragment();
		astNode = ast.newFieldDeclaration(vdf);
	}

	public void setName(String name) {
		vdf.setName(ast.newSimpleName(name));
		this.name = name;
	}

	public void setType(String type) {
		astNode.setType(ast.newSimpleType(ast.newSimpleName(type)));
	}

	@Override
	public void setModifiers(ModifierKeyword modifiers) {
		astNode.modifiers().add(ast.newModifier(modifiers));
	}

	public ASTNode getAstNode() {
		return astNode;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(backgroundColor);
		g.fillRect(x, y, 200, 20);
		g.setColor(Color.red);
		g.drawString(toString(), x, y);
		g.setColor(Color.black);
	}

	public String toString() {
		return astNode.toString();
	}

}
