package edu.uta.tdj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

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
import edu.uta.tdj.ui.CodePanel;
import edu.uta.tdj.ui.CodePanelTabs;
import edu.uta.tdj.ui.GUI;
import edu.uta.tdj.ui.FormPanel;
import edu.uta.tdj.ui.forms.ClassForm;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class CodeController {

	private static CodePanel cp;

	/**
	 * for test
	 * */

	private static AST ast = AST.newAST(AST.JLS4);

	public static void init() {
		CodeFactory codeFactory = CodeFactory.getInstance();
		codeFactory.setAST(ast);
		CompilationUnit cu = ast.newCompilationUnit();
		ClassElement ce = codeFactory.createClass("Test");
		cu.types().add(ce.getAstNode());
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
		CodePanelTabs.getInstance().addCodePanel(ce);
	}

	public static void newCodePanel(String name) {
		CompilationUnit cu = ast.newCompilationUnit();
		ClassElement ce = CodeFactory.getInstance().createClass(name);
		cu.types().add(ce.getAstNode());
		CodePanelTabs.getInstance().addCodePanel(ce);
	}

	// get the Class element
	public static ClassElement getCode() {
		return cp.getClassElement();
	}

	public static CodePanel getSelectedPanel() {
		return (CodePanel) ((JScrollPane) CodePanelTabs.getInstance()
				.getSelectedComponent()).getViewport().getView();
	}

	public static Element getSelectedElement() {
		return getSelectedPanel().getSelectListener().getSelectedElement();
	}

	// private static Element lastSelectedElement;

	// select the element and return it
	// public static Element selectedElement(int x_in, int y_in) {
	// Element selectedElement = getCode().getSelectedElement(x_in, y_in);
	//
	// if (lastSelectedElement != null
	// && lastSelectedElement != selectedElement) {
	// getCode().unSelected();
	// }
	// if (selectedElement != null) {
	// lastSelectedElement = selectedElement;
	// selectedElement.setSelected(!selectedElement.isSelected());
	// // show the tools (buttons and form) for the selected element
	// CodeController.showTools(selectedElement);
	// }
	// return selectedElement;
	// }

	// return the selected element
	// public static Element getSelectedElement() {
	// return lastSelectedElement;
	// }
	//
	// // add a child for the selected element
	// public static void addElement(Element element) {
	// getSelectedElement().addChild(element);
	// }
	//
	// // show the tools (buttons and form)
	// public static void showTools(Element element) {
	// List<ASTNode> nodeList = pc.getProposal(element);
	// ArrayList buttonList = new ArrayList<>();
	//
	// buttonList = (ArrayList) ProposalButtonFactory.getInstance()
	// .getButtons(element);
	// FormPanel.getInstance().setElement(element);
	// // set buttons for the button panel
	// ButtonPanel.getInstance().setButtonList(buttonList);
	// GUI.getInstance().refresh();
	// }
	//
	// public static CodePanel getCodePanel() {
	// return cp;
	// }
	//
	// public static void setCodePanel(CodePanel cpCodePanel) {
	// CodeController.cp = cpCodePanel;
	// }
}
