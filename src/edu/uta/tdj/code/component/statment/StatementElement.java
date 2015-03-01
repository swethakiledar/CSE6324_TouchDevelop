package edu.uta.tdj.code.component.statment;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import edu.uta.tdj.code.component.Element;

public class StatementElement extends Element {

	public StatementElement(AST ast) {
		super(ast);
		setHeight(20);
	}

	@Override
	public void addChild(Element element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setModifiers(ModifierKeyword modifiers) {
		// TODO Auto-generated method stub

	}

}
