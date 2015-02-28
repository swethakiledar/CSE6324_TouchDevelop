package edu.uta.tdj.code.proposal;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.MethodElement;
import edu.uta.tdj.ui.actions.AddMainMethodAction;
import edu.uta.tdj.ui.actions.RemoveAction;

public class ProposalButtonFactory {

	private AST ast;

	private static ProposalButtonFactory instanceButtonFactory;

	private ArrayList<JButton> classButtons_al = new ArrayList<JButton>();
	private ArrayList<JButton> methodButtons_al = new ArrayList<JButton>();
	private ArrayList<JButton> fieldButtons_al = new ArrayList<JButton>();

	private JButton removeButton;

	private ProposalButtonFactory() {
		this.init_RemoveButtonsl();
		this.init_ClassButtons_al();
		this.init_MethodButtons_al();
	}

	public static ProposalButtonFactory getInstance() {
		if (instanceButtonFactory == null) {
			instanceButtonFactory = new ProposalButtonFactory();
		}
		return instanceButtonFactory;
	}

	public void setAST(AST ast) {
		this.ast = ast;
	}

	public void init_RemoveButtonsl() {
		removeButton = new JButton("Remove");
		removeButton.addActionListener(new RemoveAction());
	}

	public void init_ClassButtons_al() {
		JButton addMethodButton = new JButton("Add a Method");
		JButton addFieldButton = new JButton("Add a Field");
		JButton addMainMethodButton = new JButton("Add the Main Method");
		addMainMethodButton.addActionListener(new AddMainMethodAction());

		classButtons_al.add(addFieldButton);
		classButtons_al.add(addMethodButton);
		classButtons_al.add(addMainMethodButton);
	}

	public void init_MethodButtons_al() {
		JButton addWhileButton = new JButton("Add a While");
		JButton addForButton = new JButton("Add a For");
		JButton addIfButton = new JButton("Add a IF");
		JButton addDoButton = new JButton("Add a DO While");

		methodButtons_al.add(addWhileButton);
		methodButtons_al.add(addDoButton);
		methodButtons_al.add(addIfButton);
		methodButtons_al.add(addForButton);
		methodButtons_al.add(removeButton);
	}

	// public void init_FieldButtons_al(){
	// JButton
	//
	// fieldButtons_al
	// }

	// public List<JButton> getButtons(Element element) {
	// List<ASTNode> astNodes = pc.getProposal(element);
	// if (element instanceof ExpressionStatementElement) {
	//
	// return null;
	// }
	// if (element instanceof MethodElement) {
	//
	// return getMethodButtons(astNodes);
	// }
	// if (element instanceof ClassElement) {
	// return getClassButtons(astNodes);
	// }
	//
	// return null;
	// }

	public List<JButton> getButtons(ClassElement ce) {

		ArrayList<JButton> al = new ArrayList<JButton>();
		al.addAll(classButtons_al);
		return al;
	}

	public List<JButton> getButtons(MethodElement me) {
		ArrayList<JButton> al = new ArrayList<JButton>();
		al.addAll(methodButtons_al);
		return al;
	}

}
