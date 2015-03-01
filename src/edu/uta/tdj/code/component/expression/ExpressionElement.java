package edu.uta.tdj.code.component.expression;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.component.Element;

public class ExpressionElement extends Element {

	public ExpressionElement(AST ast) {
		super(ast);
		// TODO Auto-generated constructor stub
		astNode = ast.newExpressionStatement(ast.newInfixExpression());
	}

	
	@Override
	public void addChild(Element element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setModifiers(ModifierKeyword modifiers) {

	}

	@Override
	public String toString() {
		return astNode.toString();
	}

}
