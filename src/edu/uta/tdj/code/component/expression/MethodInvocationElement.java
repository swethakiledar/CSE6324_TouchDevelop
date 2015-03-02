package edu.uta.tdj.code.component.expression;

import java.awt.Color;
import java.awt.Graphics;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;

import edu.uta.tdj.ui.forms.PrintStatementForm;

public class MethodInvocationElement extends ExpressionElement {

	public MethodInvocationElement(AST ast) {
		super(ast);
		// TODO Auto-generated constructor stub
		astNode = ast.newMethodInvocation();
		// Expression e = ast.newMethodInvocation();
		//
		// ((MethodInvocation)e).setExpression(ast.newSimpleName("System"));
		// ((MethodInvocation)e).setName(ast.newSimpleName("out"));
		//
		// ((MethodInvocation)astNode).setExpression(e);
		// ((MethodInvocation)astNode).setName(ast.newSimpleName("println"));

		SimpleName nameSystem = ast.newSimpleName("System");
		SimpleName nameOut = ast.newSimpleName("out");
		SimpleName namePrintln = ast.newSimpleName("println");

		// 连接‘System’和‘out’
		// System.out
		QualifiedName nameSystemOut = ast.newQualifiedName(nameSystem, nameOut);

		// 连接‘System.out’和‘println’到MethodInvocation节点
		// System.out.println()
		((MethodInvocation) astNode).setExpression(nameSystemOut);
		((MethodInvocation) astNode).setName(namePrintln);
		this.form = new PrintStatementForm();
		form.setElement(this);
		setHeight(20);
		 sDone = ast.newStringLiteral();

		 ((MethodInvocation) astNode).arguments().add(sDone);
	}
	String valueprinted = "";
	StringLiteral sDone;
	public void setArguments(String value) {
		sDone.setEscapedValue("\"" + value + "\"");
		valueprinted = value;
	}

	public String getArguments() {
		return valueprinted;
	}
	
	@Override
	public void draw(Graphics g) {
		this.width = toString().length() * 5;
		super.draw(g);
		g.setColor(Color.red);
		g.drawString(astNode.toString(), x, y + 20);
		g.setColor(Color.black);
	}

}
