package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;


import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.BlockForm;

public class BlockElement extends Element {

	public BlockElement() {
		defaultHeight = 50;
		this.setHeight(defaultHeight);
		this.form = new BlockForm();
		form.setElement(this);
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
	public String toString() {
		return "";
	}

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return pcComputer.getButtons(this);
	}

	@Override
	public String toCode() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (Element ee : childArrayList) {
			sb.append(ee.toCode());
		}
		sb.append("}");
		return sb.toString();
	}

}
