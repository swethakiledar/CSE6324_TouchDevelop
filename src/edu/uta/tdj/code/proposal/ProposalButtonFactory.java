package edu.uta.tdj.code.proposal;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.IfStatement;

import edu.uta.tdj.code.component.BlockElement;
import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.FieldElement;
import edu.uta.tdj.code.component.MethodElement;
import edu.uta.tdj.code.component.expression.ExpressionElement;
import edu.uta.tdj.code.component.statment.IfStatementElement;
import edu.uta.tdj.ui.actions.AdPrintAction;
import edu.uta.tdj.ui.actions.AddFieldAction;
import edu.uta.tdj.ui.actions.AddIfStatementAction;
import edu.uta.tdj.ui.actions.AddMainMethodAction;
import edu.uta.tdj.ui.actions.AddMethodAction;
import edu.uta.tdj.ui.actions.AddStatement;
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
		addMethodButton.addActionListener(new AddMethodAction());
		JButton addFieldButton = new JButton("Add a Field");
		addFieldButton.addActionListener(new AddFieldAction());

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
		JButton addPrintButton = new JButton("System.out.println");
		JButton addStatementButton = new JButton("Add a Statement");

		JButton addFieldButton = new JButton("Add a Field");
		addFieldButton.addActionListener(new AddFieldAction());

		addPrintButton.addActionListener(new AdPrintAction());
		addIfButton.addActionListener(new AddIfStatementAction());
		addStatementButton.addActionListener(new AddStatement());
		methodButtons_al.add(addWhileButton);
		methodButtons_al.add(addDoButton);
		methodButtons_al.add(addIfButton);
		methodButtons_al.add(addForButton);
		methodButtons_al.add(removeButton);
		methodButtons_al.add(addPrintButton);
		methodButtons_al.add(addStatementButton);
		methodButtons_al.add(addFieldButton);
	}

	public List<JButton> getButtons(IfStatementElement is) {
		ArrayList<JButton> al = new ArrayList<JButton>();
		al.add(removeButton);
		return al;
	}

	public List<JButton> getButtons(BlockElement is) {
		ArrayList<JButton> al = new ArrayList<JButton>();
		al.addAll(methodButtons_al);
		return al;
	}

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

	public List<JButton> getButtons(FieldElement fe) {
		ArrayList<JButton> al = new ArrayList<JButton>();
		al.add(removeButton);
		return al;
	}

	public List<JButton> getButtons(ExpressionElement expressionElement) {
		ArrayList<JButton> al = new ArrayList<JButton>();
		al.add(removeButton);
		return al;
	}

}
