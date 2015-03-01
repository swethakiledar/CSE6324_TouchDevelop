package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.component.statment.StatementElement;
import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.Form;

public class BlockElement extends StatementElement {

	public BlockElement(AST ast) {
		super(ast);
		astNode = ast.newBlock();
		defaultHeight = 50;
	}

	public void setY(int y) {
		this.y = y;
		int i = 1;
		for (Element ese : childArrayList) {
			ese.setY(y + 20 * i);
			i++;
		}
	}

	@Override
	public void addChild(Element element) {
		((Block) astNode).statements().add(element.getAstNode());
		childArrayList.add(element);
		element.setParent(this);
		element.setX(x + 20);
		element.setY(y + getHeight() - 30);
		setHeight(getHeight() + element.getHeight());
		
		reSort();
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.blue);
		g.drawString("{", x, y + 20);
		for (Element ee : childArrayList) {
			ee.draw(g);
		}
		g.setColor(Color.blue);
		g.drawString("}", x, this.getHeight() + y - 10);
	}

	@Override
	public void setModifiers(ModifierKeyword modifiers) {
	}

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return pcComputer.getButtons(this);
	}

}
