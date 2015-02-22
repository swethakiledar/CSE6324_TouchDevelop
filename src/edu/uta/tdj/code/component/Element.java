package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.utd.tdj.code.proposal.ComputedElement;
import edu.utd.tdj.code.proposal.ProposalComputer;

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
	
	public Element(AST ast){
		this.ast = ast;
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void setModifiers(ModifierKeyword modifiers);
	
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
	
}
