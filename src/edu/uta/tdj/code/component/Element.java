package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.proposal.ComputedElement;
import edu.uta.tdj.code.proposal.ProposalComputer;
import edu.uta.tdj.factory.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.Form;

/**
 * each Element contains one ASTNode
 * */

public abstract class Element implements ComputedElement {

	protected ASTNode astNode;
	protected Element parent;
	// for now it's useless
	protected AST ast;
	protected String name;
	protected Color backgroundColor;

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Form form;
	
	protected ArrayList<JButton> buttons_ArrayList;

	public Form getForm() {
		return this.form;
	}

	public Element getParent() {
		return parent;
	}

	public void setParent(Element parent) {
		this.parent = parent;
	}
	
	public void delete(){
		this.getParent().removeChild(this);
	}
	
	public abstract void addChild(Element element);

	public abstract void removeChild(Element element);

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
		if (selected) {
			g.setColor(Color.white);
			g.fillRect(0, y, 500, height);
		}
	}

	public void unSelected() {
		selected = false;
	}

	public abstract void setModifiers(ModifierKeyword modifiers);

	public abstract Element getSelectedElement(int x_in, int y_in);

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
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return buttons_ArrayList;
	}

	public ASTNode getAstNode() {
		return astNode;
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

	public void setHeight(int height) {
		this.height = height;
	}

}
