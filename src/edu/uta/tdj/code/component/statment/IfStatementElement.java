package edu.uta.tdj.code.component.statment;

import java.awt.Color;
import java.awt.Graphics;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.IfStatement;

import edu.uta.tdj.code.component.BlockElement;
import edu.uta.tdj.code.component.ExpressionStatementElement;

public class IfStatementElement extends StatementElement {
	/**
 * */
	public IfStatementElement(AST ast) {
		super(ast);
		// TODO Auto-generated constructor stub
		astNode = ast.newIfStatement();
		expressionElement =new ExpressionElement(ast);
		thenBlockElement = new BlockElement(ast);
		elseBlockElement = new BlockElement(ast);
	}

	private ExpressionElement expressionElement;
	private BlockElement thenBlockElement;
	private BlockElement elseBlockElement;

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.blue);
		g.drawString(this.toString(), x, y + 20);
		thenBlockElement.draw(g);
		elseBlockElement.draw(g);
	}

	@Override
	public String toString(){
		return "If (" + ((IfStatement)astNode).getExpression() + ")";
	}

}
