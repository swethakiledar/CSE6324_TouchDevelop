package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.component.observer.Observable;
import edu.uta.tdj.code.component.observer.Observer;
import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.ClassForm;
import edu.uta.tdj.ui.forms.MethodForm;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class MethodElement extends Element implements Observable {

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return pcComputer.getButtons(this);
	}

	private String modifiedString = "";
	private String returnTypeString = "";
	private ArrayList<ExpressionStatementElement> statementList;

	public MethodElement(AST ast) {
		super(ast);
		astNode = ast.newMethodDeclaration();
		statementList = new ArrayList<ExpressionStatementElement>();
		this.height = 50;
		this.form = new MethodForm();
	}

	public void setName(String name) {
		((MethodDeclaration) astNode).setName(ast.newSimpleName(name));
		this.name = name;
		this.width = name.length() * 6;
	}

	public ASTNode getNode() {
		return astNode;
	}

	public void setModifiers(ModifierKeyword modifiers) {
		((MethodDeclaration) astNode).modifiers().add(
				ast.newModifier(modifiers));
		modifiedString = modifiedString + " " + modifiers.toString();
	}

	public void addParam(String name, String type) {
		SingleVariableDeclaration variableDeclaration = ast
				.newSingleVariableDeclaration();
		variableDeclaration.setType(ast.newSimpleType(ast.newSimpleName(type)));
		variableDeclaration.setName(ast.newSimpleName(name));
		((MethodDeclaration) astNode).parameters().add(variableDeclaration);
	}

	public void addParam(String name, Type type) {
		SingleVariableDeclaration variableDeclaration = ast
				.newSingleVariableDeclaration();
		variableDeclaration.setType(type);
		variableDeclaration.setName(ast.newSimpleName(name));
		((MethodDeclaration) astNode).parameters().add(variableDeclaration);
	}

	public void setReturnType(Type type) {
		((MethodDeclaration) astNode).setReturnType2(type);
		returnTypeString = type.toString();
	}

	public void createBlock() {
		((MethodDeclaration) astNode).setBody(ast.newBlock());
	}

	public void addStatement(Statement s) {
		((MethodDeclaration) astNode).getBody().statements().add(s);
	}

	public void addStatement(ExpressionStatementElement ese) {
		ese.setParent(this);
		((MethodDeclaration) astNode).getBody().statements()
				.add(ese.getAstNode());
		ese.setX(x + 20);
		ese.setY(y + (statementList.size() + 1) * 20);
		statementList.add(ese);
		this.height = height + 20;
	}

	public ASTNode getAstNode() {
		return astNode;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.blue);
		g.drawString(this.toString(), x, y + 20);
		for (ExpressionStatementElement ee : statementList) {
			ee.draw(g);
		}
		g.setColor(Color.blue);
		g.drawString("}", x, this.height + y - 10);
	}

	public String toString() {

		return modifiedString
				+ " "
				+ returnTypeString
				+ " "
				+ name
				+ "("
				+ ((MethodDeclaration) astNode).parameters().toString()
						.replace("[", "").replace("]", "") + ")" + "{";
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
		for (ExpressionStatementElement ese : statementList) {
			ese.setY(y + 20 * i);
			i++;
		}
	}

	@Override
	public Element getSelectedElement(int x_in, int y_in) {
		if (this.isInelement(x_in, y_in)) {
			return this;
		} else {
			for (ExpressionStatementElement ese : statementList) {
				Element element = ese.getSelectedElement(x_in, y_in);
				if (element != null) {
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

	@Override
	public void addChild(Element element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeChild(Element element) {
		// TODO Auto-generated method stub

	}

}
