package edu.uta.tdj.factory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;

import edu.uta.tdj.code.component.Element;
import edu.uta.tdj.controller.CodeController;
import edu.uta.tdj.ui.actions.AddMainMethodAction;

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
	
	public void init_RemoveButtonsl(){		
		removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Element element = CodeController.getSelectedElement();
				element.getParent().removeChild(element);
			}
		});
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

	public List<JButton> getClassButtons(List<ASTNode> astNodes) {

		ArrayList<JButton> al = new ArrayList<JButton>();
		al.addAll(classButtons_al);
		// for(ASTNode node : astNodes){
		//
		// switch (node.getNodeType()) {
		// case ASTNode.:
		//
		// break;
		//
		// default:
		// break;
		// }
		//
		// }

		return al;
	}

	public List<JButton> getMethodButtons(List<ASTNode> astNodes) {
		ArrayList<JButton> al = new ArrayList<JButton>();
		al.addAll(methodButtons_al);
		for (ASTNode node : astNodes) {
			
		}

		return al;
	}

}
