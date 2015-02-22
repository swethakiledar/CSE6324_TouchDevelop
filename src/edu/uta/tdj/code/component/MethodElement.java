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

import edu.uta.tdj.code.component.observer.Observable;
import edu.uta.tdj.code.component.observer.Observer;

public class MethodElement extends Element implements Observable {

	private MethodDeclaration mMethodDeclaration;
	private String modifiedString = "";
	private String returnTypeString = "";
	private ArrayList<ExpressionStatementElement> statementList;
	
	public MethodElement(AST ast) {
		super(ast);
		mMethodDeclaration = ast.newMethodDeclaration();
		statementList = new ArrayList<ExpressionStatementElement>();
		this.height = 50;
	}
	
	public void setName(String name) {
		mMethodDeclaration.setName(ast.newSimpleName(name));
		this.name = name;
		this.width = name.length()*5;
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
		this.height = height + 20;
	}

	public ASTNode getAstNode() {
		return mMethodDeclaration;
	}
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.blue);
		g.drawString(this.toString(), x, y+20);
		for (ExpressionStatementElement ee : statementList) {
			ee.draw(g);
		}
		g.setColor(Color.blue);
		g.drawString("}", x, this.height+y-10);
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

	@Override
	public void add(Observer observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Observer observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		int i = 1;
		for(ExpressionStatementElement ese: statementList){
			ese.setY(y + 20*i);
			i++;
		}
	}

	@Override
	public Element getSelectedElement(int x_in, int y_in) {
		if(this.isInelement(x_in, y_in)){
			return this;
		}else{
			for(ExpressionStatementElement ese: statementList){
				Element element = ese.getSelectedElement(x_in, y_in);
				if(element!=null){
					return element;
				}
			}
		}
		return null;
	}
	

	@Override
	public void unSelected() {
		super.unSelected();
		for (ExpressionStatementElement ese : statementList) {
			ese.unSelected();
		}
	}

}
