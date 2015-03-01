package edu.uta.tdj.code.component.statment;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IfStatement;

import edu.uta.tdj.code.component.BlockElement;
import edu.uta.tdj.code.component.Element;
import edu.uta.tdj.code.component.expression.ExpressionElement;
import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.IfForm;

public class IfStatementElement extends StatementElement {
	/**
 * */

	public IfStatementElement(AST ast) {
		super(ast);
		// TODO Auto-generated constructor stub
		astNode = ast.newIfStatement();

		expressionElement = new ExpressionElement(ast);
		expressionElement.setHeight(20);
		// else block
		elseBlockElement = new BlockElement(ast);
		elseBlockElement.setParent(this);
		elseBlockElement.setHeight(50);
		// then block
		thenBlockElement = new BlockElement(ast);
		thenBlockElement.setParent(this);
		thenBlockElement.setHeight(50);
		// add
		childArrayList.add(thenBlockElement);
		childArrayList.add(elseBlockElement);
		setHeight(120);
		defaultHeight = 120;
		this.form = new IfForm();
		form.setElement(this);
	}

	private ExpressionElement expressionElement;
	private BlockElement thenBlockElement;
	private BlockElement elseBlockElement;

	public void setX(int x) {
		super.setX(x);
		elseBlockElement.setX(x);
		thenBlockElement.setX(x);
	}

	public void setY(int y) {
		super.setY(y);
		elseBlockElement.setY(y + expressionElement.getHeight());
		thenBlockElement.setY(y + expressionElement.getHeight()
				+ elseBlockElement.getHeight());
	}

	public ExpressionElement getExpressionElement() {
		return expressionElement;
	}

	public void setExpressionElement(ExpressionElement expressionElement) {
		this.expressionElement = expressionElement;
		((IfStatement) astNode).setExpression((Expression) expressionElement
				.getAstNode());
		expressionElement.setParent(this);
	}

	public BlockElement getThenBlockElement() {
		return thenBlockElement;
	}

	public BlockElement getElseBlockElement() {
		return elseBlockElement;
	}

	public Element getSelectedElement(int x_in, int y_in) {
		if (this.isInelement(x_in, y_in)) {
			return this;
		} else {
			for (Element element : childArrayList) {
				Element e = element.getSelectedElement(x_in, y_in);
				if (e != null)
					return e;
			}
		}
		return null;
	}

	public void reSort() {
		if (this.getParent() != null)
			this.getParent().reSort();
		int lastHeight = 20;
		setHeight(defaultHeight);
		for (Element me : childArrayList) {
			me.setX(x);
			me.setY(y + getHeight() - 30);
			setHeight(getHeight() + me.getHeight());
			me.setY(lastHeight + y);
			lastHeight = me.getHeight() + lastHeight;
		}
	}

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return pcComputer.getButtons(this);
	}

	@Override
	public void draw(Graphics g) {
		setY(getY());
		super.draw(g);
		g.setColor(Color.blue);
		g.drawString(this.toString(), x, y + 20);
		for (Element element : childArrayList) {
			element.draw(g);
		}
	}

	@Override
	public String toString() {
		return "If (" + ((IfStatement) astNode).getExpression() + ")";
	}

}
