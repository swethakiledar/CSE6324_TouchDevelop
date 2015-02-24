package edu.uta.tdj.controller;

import java.util.ArrayList;
import java.util.List;

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
import edu.uta.tdj.code.proposal.ProposalComputer;
import edu.uta.tdj.factory.CodeFactory;
import edu.uta.tdj.factory.ProposalButtonFactory;
import edu.uta.tdj.ui.ButtonPanel;
import edu.uta.tdj.ui.CodeTest;
import edu.uta.tdj.ui.FormPanel;
import edu.uta.tdj.ui.forms.ClassForm;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class CodeController {

	private static ClassElement ce;
	private static ProposalComputer pc = new ProposalComputer();

	/**
	 * for test
	 * */

	public static void init() {
		CodeFactory codeFactory = CodeFactory.getInstance();

		AST ast = AST.newAST(AST.JLS4);
		codeFactory.setAST(ast);
		CompilationUnit cu = ast.newCompilationUnit();
		ce = codeFactory.createClass("Test");
		cu.types().add(ce.getAstNode());
		pc.setCompilationUnit(cu);
		FieldElement fe = codeFactory.createFieldElement("field1", "Test",
				ModifierKeyword.PRIVATE_KEYWORD);
		ce.addField(fe);
		ce.setModifiers(ModifierKeyword.PUBLIC_KEYWORD);
		MethodElement me = codeFactory.createMethodElement("method1",
				ModifierKeyword.PUBLIC_KEYWORD,
				ast.newPrimitiveType(PrimitiveType.VOID));
		ce.addMethod(me);
		ce.addMethod(codeFactory.createMainMethodElement());

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
		if (selectedElement != null) {
			lastSelectedElement = selectedElement;
			selectedElement.setSelected(!selectedElement.isSelected());
			// for test proposal
			System.out.println(pc.getProposal(selectedElement));

		}
		return selectedElement;
	}

	public static Element getSelectedElement() {
		return lastSelectedElement;
	}

	public static void addElement(Element element) {
		getSelectedElement().addChild(element);
	}

	public static void showTools(Element element) {
		List<ASTNode> nodeList = pc.getProposal(element);
		ArrayList buttonList = new ArrayList<>();
		if (element instanceof ClassElement) {
			// buttons
			buttonList = (ArrayList) ProposalButtonFactory.getInstance()
					.getClassButtons(nodeList);
			// form
			ClassForm.getInstance().setElement((ClassElement) element);
			FormPanel.getInstance().setInsidePanel(ClassForm.getInstance());
		}
		if (element instanceof MethodElement) {
			buttonList = (ArrayList) ProposalButtonFactory.getInstance()
					.getMethodButtons(nodeList);
		}
		ButtonPanel.getInstance().setButtonList(buttonList);
	}

	public static void showForm(Element element) {

	}
}
