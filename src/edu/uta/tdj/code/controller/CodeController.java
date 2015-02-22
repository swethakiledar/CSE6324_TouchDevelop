package edu.uta.tdj.code.controller;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.ExpressionStatementElement;
import edu.uta.tdj.code.component.MethodElement;
import edu.utd.tdj.code.proposal.ProposalComputer;

public class CodeController {
	public static ClassElement getCode() {
		ProposalComputer pc = new ProposalComputer();
		/**
		 * build AST start
		 * */
		AST ast = AST.newAST(AST.JLS4);
		CompilationUnit cu = ast.newCompilationUnit();
		ClassElement ce = new ClassElement(ast, "Test");
		cu.types().add(ce.getAstNode());
		pc.setCompilationUnit(cu);
		ce.setModifiers(ModifierKeyword.PUBLIC_KEYWORD);
		ce.addField("test", "Test", ModifierKeyword.PRIVATE_KEYWORD);
		ce.addContructor(ModifierKeyword.PUBLIC_KEYWORD);
		ce.addMethod("display", ast.newPrimitiveType(PrimitiveType.VOID),
				ModifierKeyword.PUBLIC_KEYWORD);
		MethodElement me = new MethodElement(ast);
		me.setName("newMethod");
		me.createBlock();
		me.setModifiers(ModifierKeyword.PUBLIC_KEYWORD);
		me.setReturnType(ast.newPrimitiveType(PrimitiveType.VOID));
		ce.addMethod(me);
		
		ExpressionStatementElement eeElement = new ExpressionStatementElement(
				ast);
		
		eeElement.setLeft("test");
		eeElement.setOperation(Assignment.Operator.ASSIGN);
		eeElement.setRight("s");
		me.addStatement(eeElement);
	
		return ce;
	}
}
