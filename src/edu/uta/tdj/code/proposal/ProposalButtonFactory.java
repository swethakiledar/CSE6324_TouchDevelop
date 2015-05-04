package edu.uta.tdj.code.proposal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;

import edu.uta.tdj.code.component.BlockElement;
import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.ExpressionStatement;
import edu.uta.tdj.code.component.FieldElement;
import edu.uta.tdj.code.component.IfStatement;
import edu.uta.tdj.code.component.MethodElement;
import edu.uta.tdj.code.component.MethodInvocationElement;
import edu.uta.tdj.code.component.SysPrint;
import edu.uta.tdj.code.component.WhileStatement;
import edu.uta.tdj.controller.ProjectController;
import edu.uta.tdj.ui.CodePanel;
import edu.uta.tdj.ui.GUI;
import edu.uta.tdj.ui.actions.AddAssignMentActions;
import edu.uta.tdj.ui.actions.AddBlockAction;
import edu.uta.tdj.ui.actions.AddFieldAction;
import edu.uta.tdj.ui.actions.AddForActions;
import edu.uta.tdj.ui.actions.AddIfStatementAction;
import edu.uta.tdj.ui.actions.AddMainMethodAction;
import edu.uta.tdj.ui.actions.AddMethodAction;
import edu.uta.tdj.ui.actions.AddWhileActions;
import edu.uta.tdj.ui.actions.RemoveAction;

public class ProposalButtonFactory {

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
		addWhileButton.addActionListener(new AddWhileActions());

		JButton addForButton = new JButton("Add a For");
		addForButton.addActionListener(new AddForActions());

		JButton addIfButton = new JButton("Add a IF");
		addIfButton.addActionListener(new AddIfStatementAction());

		JButton addDoButton = new JButton("Add a DO While");
		JButton addPrintButton = new JButton("System.out.println");

		addPrintButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CodePanel cp = ProjectController.getInstance()
						.getSelectedCodePanel();

				SysPrint sp = new SysPrint();
				cp.getSelectListener().getSelectedElement().addChild(sp);
				// save
				cp.getComplieUnitElement().save();
				//
				GUI.getInstance().refresh();
			}
		});

		JButton addStatementButton = new JButton("Add a Statement");

		JButton addFieldButton = new JButton("Add a Field");
		addFieldButton.addActionListener(new AddFieldAction());

		JButton addExpressionButton = new JButton("Add an Assignment");
		addExpressionButton.addActionListener(new AddAssignMentActions());

		methodButtons_al.add(addExpressionButton);
		methodButtons_al.add(addWhileButton);
		methodButtons_al.add(addDoButton);
		methodButtons_al.add(addIfButton);
		methodButtons_al.add(addForButton);
		methodButtons_al.add(removeButton);
		methodButtons_al.add(addPrintButton);
		methodButtons_al.add(addStatementButton);
		methodButtons_al.add(addFieldButton);
	}

	public List<JButton> getButtons(IfStatement ifs) {
		ArrayList<JButton> al = new ArrayList<>();
		al.add(removeButton);

		JButton addBlockButton = new JButton("Add a Block");
		addBlockButton.addActionListener(new AddBlockAction());
		al.add(addBlockButton);

		JButton setElseButton = new JButton("Set Else");

		al.add(setElseButton);

		return al;

	}

	public List<JButton> getButtons(BlockElement is) {
		ArrayList<JButton> al = new ArrayList<JButton>();
		al.addAll(methodButtons_al);
		return al;
	}

	public List<JButton> getButtons(ExpressionStatement es) {
		ArrayList<JButton> al = new ArrayList<JButton>();
		al.add(removeButton);
		return al;
	}

	public List<JButton> getButtons(MethodInvocationElement es) {
		ArrayList<JButton> al = new ArrayList<JButton>();
		al.add(removeButton);
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

	public List<JButton> getButtons(WhileStatement fe) {
		ArrayList<JButton> al = new ArrayList<>();
		al.add(removeButton);
		JButton addBlockButton = new JButton("Add a Block");
		addBlockButton.addActionListener(new AddBlockAction());
		al.add(addBlockButton);
		return al;
	}

}
