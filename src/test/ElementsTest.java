package test;

import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.ExpressionStatementElement;
import edu.utd.tdj.code.proposal.ProposalComputer;

public class ElementsTest {
	public static void main(String[] args) {

		// used to compute the proposal
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

		ce.addMethod("display2", ast.newPrimitiveType(PrimitiveType.VOID),
				ModifierKeyword.PUBLIC_KEYWORD);

		System.out.println(cu);

		/**
		 * build AST end 
		 * public class Test { 
		 * 		private Test test; 
		 * 		public void Test(){ } 
		 * 		public void display(){ } 
		 * 		public void display2(){ } 
		 * }
		 * */

		// assume the user want to  create a statement to invoke the method of test,
		// because it's a statement so ...
		// we create a statement
		ExpressionStatementElement eeElement = new ExpressionStatementElement(
				ast);
		//assume the user already input the 'test'
		eeElement.setLeft("test");
		// get the proposals using pc
		List<ASTNode> list = pc.getProposal((ExpressionStatement) eeElement
				.getAstNode());
		//for testing, here we print all the proposals of ASTNode 
		for (ASTNode node : list) {
			System.err.println(node);
		}
		//if the user selected the method(cursor in the body of method). then get the proposals of the methods
		List<ASTNode> list2 = pc
				.getProposal(((TypeDeclaration) ce.getAstNode()).getMethods()[0]);

		for (ASTNode node : list2) {
			System.err.println((node));
		}
		//generate the statement by given proposal it's method invoke
		MethodDeclaration md = (MethodDeclaration) list.get(1);
		MethodInvocation mi = ast.newMethodInvocation();
		mi.setName(ast.newSimpleName(md.getName().toString()));
		mi.setExpression(ast
				.newSimpleName(((Assignment) ((ExpressionStatement) eeElement
						.getAstNode()).getExpression()).getLeftHandSide()
						.toString()));
		((ExpressionStatement) (eeElement.getAstNode())).setExpression(mi);
		
		//add the generated statement to the method2
		MethodDeclaration md2 = (MethodDeclaration) list.get(2);
		md2.getBody().statements().add(eeElement.getAstNode());
		//print the result
		System.out.println(cu);

	}
}
