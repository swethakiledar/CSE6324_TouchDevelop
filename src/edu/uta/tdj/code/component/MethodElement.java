package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

public class MethodElement extends Element {

	private MethodDeclaration mMethodDeclaration;
	private String modifiedString = "";
	private String returnTypeString = "";
	private ArrayList<ExpressionStatementElement> statementList;

	public MethodElement(AST ast) {
		super(ast);
		mMethodDeclaration = ast.newMethodDeclaration();
		statementList = new ArrayList<ExpressionStatementElement>();
	}

	public void setName(String name) {
		mMethodDeclaration.setName(ast.newSimpleName(name));
		this.name = name;
	}

	public ASTNode getNode() {
		return mMethodDeclaration;
	}

	public void setModifiers(ModifierKeyword modifiers) {
		mMethodDeclaration.modifiers().add(ast.newModifier(modifiers));
		modifiedString = modifiedString + " " + modifiers.toString();
	}

	public void addParam(String name, String type) {
		SingleVariableDeclaration variableDeclaration = ast
				.newSingleVariableDeclaration();
		variableDeclaration.setType(ast.newSimpleType(ast.newSimpleName(type)));
		variableDeclaration.setName(ast.newSimpleName(name));
		mMethodDeclaration.parameters().add(variableDeclaration);
	}

	public void setReturnType(Type type) {
		mMethodDeclaration.setReturnType2(type);
		returnTypeString = type.toString();
	}

	public void createBlock() {
		mMethodDeclaration.setBody(ast.newBlock());
	}

	public void addStatement(Statement s) {
		mMethodDeclaration.getBody().statements().add(s);
	}

	public void addStatement(ExpressionStatementElement ese) {
		mMethodDeclaration.getBody().statements().add(ese.getAstNode());
		ese.setX(x + 20);
		ese.setY(y + (statementList.size() + 1) * 20);
		statementList.add(ese);
	}

	public ASTNode getAstNode() {
		return mMethodDeclaration;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.drawString(this.toString(), x, y);
		for (ExpressionStatementElement ee : statementList) {
			ee.draw(g);
		}
		g.setColor(Color.blue);
		g.drawString("}", x, y+(statementList.size() + 1) * 20);
	}

	public String toString() {

		return modifiedString
				+ " "
				+ returnTypeString
				+ " "
				+ name
				+ "("
				+ mMethodDeclaration.parameters().toString().replace("[", "")
						.replace("]", "") + ")" + "{";
	}

}
