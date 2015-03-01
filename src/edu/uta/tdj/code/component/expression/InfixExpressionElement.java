package edu.uta.tdj.code.component.expression;

import java.awt.Graphics;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.component.Element;

public class InfixExpressionElement extends ExpressionElement {

	private ExpressionElement left;
	private ExpressionElement right;
	private InfixExpression.Operator operator;

	public InfixExpressionElement(AST ast) {
		super(ast);
		astNode = ast.newInfixExpression();
		System.out.println(astNode);
	}

	public ExpressionElement getLeft() {
		return left;
	}

	public void setLeft(ExpressionElement left) {
		left.setX(x);
		left.setY(y);
		left.setWidth(left.getAstNode().toString().length() * 5);
		this.left = left;
		((InfixExpression) astNode).setLeftOperand((Expression) left
				.getAstNode());
	}

	public ExpressionElement getRight() {

		return right;
	}

	public void setRight(ExpressionElement right) {
		right.setX(x + operator.toString().length() * 5);
		right.setY(y);
		right.setWidth(right.getAstNode().toString().length() * 5);
		this.right = right;
		((InfixExpression) astNode).setLeftOperand((Expression) right
				.getAstNode());
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		left.draw(g);
		g.drawString(operator.toString(), x, y);
		right.draw(g);
	}

	public InfixExpression.Operator getOperator() {
		return operator;
	}

	public void setOperator(InfixExpression.Operator operator) {
		this.operator = operator;
	}

	@Override
	public void addChild(Element element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setModifiers(ModifierKeyword modifiers) {
		// TODO Auto-generated method stub

	}
}
