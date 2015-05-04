package edu.uta.tdj.code.component;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * @author Fuqiang Zhang
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;

import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.ClassForm;

/**
 * For a class
 * 
 * */

public class ClassElement extends Element {

	private String modifiedString = "";
	private String accessString = "";

	public ClassElement() {
		this.setX(50);
		this.setY(50);
		this.form = new ClassForm();
		this.form.setElement(this);
		setHeight(100);
		defaultHeight = 100;
	}

	public void setName(String name) {
		this.name = name;
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

	/**
	 * should be changed and split into parts
	 * */
	public void setModifiers(String modifiers) {
		this.modifiedString = modifiers;
	}

	public String getModifiers() {
		return this.modifiedString;
	}

	public void setAccess(String access) {
		this.accessString = access;
	}

	public String getAccess() {

		return this.accessString;
	}

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return pcComputer.getButtons(this);
	}

	@Override
	public void draw(Graphics g) {
		this.width = name.length() * 5;
		super.draw(g);
		g.setColor(Color.black);
		g.drawString(this.toString(), x, y + 20);

		for (Element me : childArrayList) {
			me.draw(g);
		}
		g.drawString("}", x, getHeight() + y - 10);

	}

	public String toString() {
		String head = this.modifiedString + this.accessString + "  class  "
				+ name + "  {";
		return head;
	}

	public String toCode() {
		String head = this.modifiedString + this.accessString + "  class  "
				+ name + "  {";
		StringBuffer sb = new StringBuffer();
		sb.append(head);
		sb.append(System.getProperty("line.separator"));
		for (Element me : childArrayList) {
			sb.append(me.toCode());
			sb.append(System.getProperty("line.separator"));
		}
		sb.append("}");
		System.out.println(sb.toString());
		
		return sb.toString();
	}

}