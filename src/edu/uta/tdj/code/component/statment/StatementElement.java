package edu.uta.tdj.code.component.statment;

import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.component.Element;
import edu.uta.tdj.code.proposal.ProposalButtonFactory;

public class StatementElement extends Element {

	public StatementElement(AST ast) {
		super(ast);
	}

	@Override
	public void addChild(Element element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setModifiers(ModifierKeyword modifiers) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		// TODO Auto-generated method stub
		return null;
	}

}
