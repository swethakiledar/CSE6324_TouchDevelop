package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.component.expression.MethodInvocationElement;
import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.MethodForm;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class MethodElement extends Element {

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return pcComputer.getButtons(this);
	}

	private String modifiedString = "";
	private String returnTypeString = "";

	public MethodElement(AST ast) {
		super(ast);
		astNode = ast.newMethodDeclaration();
		setHeight(50);
		defaultHeight = 50;
		this.form = new MethodForm();
	}

	public void setName(String name) {
		((MethodDeclaration) astNode).setName(ast.newSimpleName(name));
		this.name = name;
		this.width = name.length() * 6;
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

	public void setY(int y) {
		this.y = y;
		int i = 1;
		for (Element ese : childArrayList) {
			ese.setY(y + 20 * i);
			i++;
		}
	}

	@Override
	public void addChild(Element element) {
		// for demo
		if (element instanceof MethodInvocationElement) {
			((MethodDeclaration) astNode)
					.getBody()
					.statements()
					.add(ast.newExpressionStatement((Expression) element
							.getAstNode()));
		} else {
			((MethodDeclaration) astNode).getBody().statements()
					.add(element.getAstNode());
		}
		// for demo end
		element.setParent(this);

		childArrayList.add(element);
		element.setX(x + 20);
		element.setY(y + getHeight() - 30);
		setHeight(getHeight() + element.getHeight());

		reSort();
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.blue);
		g.drawString(this.toString(), x, y + 20);
		for (Element ee : childArrayList) {
			ee.draw(g);
		}
		g.setColor(Color.blue);
		g.drawString("}", x, getHeight() + y - 10);
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

}
