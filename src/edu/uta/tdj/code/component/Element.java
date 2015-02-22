package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.utd.tdj.code.proposal.ComputedElement;
import edu.utd.tdj.code.proposal.ProposalComputer;
/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * @author Fuqiang Zhang
 */

/**
 * each Element contains one ASTNode 
 * */

public abstract class Element implements ComputedElement{
	
	protected ASTNode astNode;
	// for now it's useless
	protected ProposalComputer pcComputer;
	protected AST ast;
	protected String name;
	protected Color backgroundColor;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	protected boolean selected = false;
	
	public Element(AST ast){
		this.ast = ast;
	}
	
	public void draw(Graphics g){
		this.width = toString().length()*5;
		if(selected){
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);
		}
	}
	
	public void unSelected(){
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
	
	
	public void setBackgroundColor(Color color){
		this.backgroundColor = color;
	}
	
	@Override
	public void accept(ProposalComputer pcComputer) {
		this.pcComputer = pcComputer;
		pcComputer.setComputedElement(this);
	}
	
	public ASTNode getAstNode(){
		return astNode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
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
