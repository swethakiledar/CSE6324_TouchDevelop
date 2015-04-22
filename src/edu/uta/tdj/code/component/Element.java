package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.proposal.ComputedElement;
import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.code.proposal.ProposalComputer;
import edu.uta.tdj.ui.actions.RemoveAction;
import edu.uta.tdj.ui.forms.Form;

/**
 * each Element contains one ASTNode
 * */

public abstract class Element implements ComputedElement {

	protected ASTNode astNode;
	protected Element parent;
	protected AST ast;
	protected String name;
	protected Color backgroundColor;

	protected int x;
	protected int y;
	protected int width;
	private int height;
	protected Form form;
	protected int defaultHeight;

	protected ArrayList<JButton> buttons_ArrayList;

	protected ArrayList<Element> childArrayList = new ArrayList<Element>();

	public ArrayList<Element> getChildArrayList() {
		return childArrayList;
	}

	public void setChildArrayList(ArrayList<Element> childArrayList) {
		this.childArrayList = childArrayList;
	}

	public Form getForm() {
		return this.form;
	}

	public Element getParent() {
		return parent;
	}

	public void setHeight(int height) {
		if (getParent() != null) {
			this.getParent().setHeight(
					this.getParent().getHeight() + height - getHeight());
		}
		this.height = height;
	}

	public void setParent(Element parent) {
		this.parent = parent;
	}

	public void delete() {
		this.getParent().removeChild(this);
		form.setVisible(false);
	}

	public abstract void addChild(Element element);

	public void removeChild(Element element) {
		childArrayList.remove(element);
		element.getAstNode().delete();

		height = height - element.getHeight();
		reSort();
	}

	public void reSort() {
		if (this.getParent() != null)
			this.getParent().reSort();
		int lastHeight = 20;
		setHeight(defaultHeight);
		for (Element me : childArrayList) {
			me.setX(x + 20);
			me.setY(y + getHeight() - 30);
			setHeight(getHeight() + me.getHeight());
			me.setY(lastHeight + y);
			lastHeight = me.getHeight() + lastHeight;
		}
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	protected boolean selected = false;

	public Element(AST ast) {
		this.ast = ast;
	}

	public void draw(Graphics g) {
		g.setFont(new Font("Tahoma", Font.BOLD, 15));
		if (selected) {
			g.setColor(Color.white);
			g.fillRect(0, y, 500, height);
		}
	}

	public Element getSelectedElement(int x_in, int y_in) {
		if (this.isInelement(x_in, y_in)) {
			return this;
		} else {
			for (Element element : childArrayList) {
				Element e = element.getSelectedElement(x_in, y_in);
				if (e != null)
					return e;
			}
		}
		return null;
	}

	public void unSelected() {
		selected = false;
		System.out.println(this.getName() + " = === == " + selected);
		for (Element fe : childArrayList) {
			fe.unSelected();
		}
	}

	public abstract void setModifiers(ModifierKeyword modifiers);

	public boolean isInelement(int x_in, int y_in) {
		if (y_in > this.y && y_in < this.y + 20) {
			return true;
		} else {
			return false;
		}
	}

	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;
	}

	@Override
	public abstract List<JButton> getButtons(ProposalButtonFactory pcComputer);

	public ASTNode getAstNode() {
		return astNode;
	}

	public void setAstNode(ASTNode astNode) {
		this.astNode = astNode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return astNode.toString();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

}
