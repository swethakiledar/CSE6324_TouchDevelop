package edu.uta.tdj.code.component.expression;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.component.Element;
import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.ExpressionForm;

public class ExpressionElement extends Element {

	public ExpressionElement(AST ast) {
		super(ast);
		// TODO Auto-generated constructor stub
		astNode = ast.newExpressionStatement(ast.newAssignment());
		setHeight(20);
		this.form = new ExpressionForm();
		form.setElement(this);
	}

	@Override
	public void addChild(Element element) {
	}

	@Override
	public void setModifiers(ModifierKeyword modifiers) {
	}

	@Override
	public String toString() {
		return astNode.toString();
	}

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return pcComputer.getButtons(this);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.blue);
		g.drawString(toString(), x, y + 20);
	}

}
