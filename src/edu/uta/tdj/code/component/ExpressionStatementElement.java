package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class ExpressionStatementElement extends Element {

	public ExpressionStatementElement(AST ast) {
		super(ast);
		// TODO Auto-generated constructor stub
		assignment = ast.newAssignment();
		vdf = ast.newVariableDeclarationFragment();
		vde = ast.newVariableDeclarationExpression(vdf);
	}

	private ExpressionStatement astNode;
	private Assignment assignment;
	private VariableDeclarationFragment vdf;
	private ClassInstanceCreation cc;
	VariableDeclarationExpression vde;

	public void setName(String name) {
		this.name = name;
		vdf.setName(ast.newSimpleName(name));
		assignment.setLeftHandSide(vde);
	}

	public void setLeft(String name) {
		assignment.setLeftHandSide(ast.newSimpleName(name));
	}

	public void setRight(String name) {
		assignment.setRightHandSide(ast.newSimpleName(name));
	}

	public void setOperation(Assignment.Operator assignmentOperator) {
		assignment.setOperator(assignmentOperator);
	}

	public void setType(String type) {
		vde.setType(ast.newSimpleType(ast.newSimpleName(type)));
	}

	public void newInstance(String type) {
		cc = ast.newClassInstanceCreation();
		cc.setType(ast.newSimpleType(ast.newSimpleName(type)));
		assignment.setRightHandSide(cc);
	}

	public ASTNode getAstNode() {
		if (astNode == null)
			astNode = ast.newExpressionStatement(assignment);
		return astNode;
	}

	@Override
	public void setModifiers(ModifierKeyword modifiers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.drawString(this.astNode.toString(), x, y+20);
		
	}

	@Override
	public Element getSelectedElement(int x_in, int y_in) {
		// TODO Auto-generated method stub
		if(this.isInelement(x_in, y_in))
			return this;
		return null;
	}


}
