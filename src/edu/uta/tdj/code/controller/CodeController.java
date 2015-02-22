package edu.uta.tdj.code.controller;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.Element;
import edu.uta.tdj.code.component.ExpressionStatementElement;
import edu.uta.tdj.code.component.FieldElement;
import edu.uta.tdj.code.component.MethodElement;
import edu.uta.tdj.code.factory.CodeFactory;

public class CodeController {

	private static ClassElement ce;
	
	
	/**
	 * for test
	 * */
	
	public static void init() {
		CodeFactory codeFactory = new CodeFactory();

		AST ast = AST.newAST(AST.JLS4);
		codeFactory.setAST(ast);
		CompilationUnit cu = ast.newCompilationUnit();
		ce = codeFactory.createClass("Test");
		cu.types().add(ce.getAstNode());

		FieldElement fe = codeFactory.createFieldElement("field1", "Test",
				ModifierKeyword.PRIVATE_KEYWORD);
		ce.addField(fe);

		MethodElement me = codeFactory.createMethodElement("method1",
				ModifierKeyword.PUBLIC_KEYWORD,
				ast.newPrimitiveType(PrimitiveType.VOID));
		ce.addMethod(me);

		ExpressionStatementElement eeElement = codeFactory
				.createExpressionStatementElement();
		me.addStatement(eeElement);

	}

	public static ClassElement getCode() {
		/**
		 * build AST start
		 * */

		return ce;
	}

	private static Element lastSelectedElement;

	public static Element selectedElement(int x_in, int y_in) {
		Element selectedElement = getCode().getSelectedElement(x_in, y_in);

		if (lastSelectedElement != null
				&& lastSelectedElement != selectedElement) {
			getCode().unSelected();
		}
		lastSelectedElement = selectedElement;
		selectedElement.setSelected(!selectedElement.isSelected());
		return selectedElement;
	}
}
