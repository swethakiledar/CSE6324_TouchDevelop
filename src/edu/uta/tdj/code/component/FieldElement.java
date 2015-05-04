package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;

import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.FieldForm;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class FieldElement extends Element {

	private String typeString = "";
	private String modifiersString = "";
	private String accessString = "";
	private String assignmentString = "";

	public FieldElement() {
		setHeight(20);
		this.form = new FieldForm();
		form.setElement(this);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.typeString = type;
	}

	public String getType() {
		return typeString;
	}

	@Override
	public void draw(Graphics g) {
		this.width = toString().length() * 5;
		super.draw(g);
		g.setColor(Color.red);
		g.drawString(toString(), x, y + 20);
		g.setColor(Color.black);
	}

	public void setModifiers(String modifiers) {
		this.modifiersString = modifiers;
	}

	public void setAccess(String access) {
		this.accessString = access;
	}

	public String getAccess() {
		return this.accessString;
	}

	public String getModifiers() {
		return this.modifiersString;
	}

	public String toString() {
		String aString = assignmentString.trim().equals("") ? "" : "="
				+ assignmentString;
		return accessString + " " + modifiersString + " " + typeString + " "
				+ name + aString + ";";
	}

	@Override
	public void addChild(Element element) {
	}

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return pcComputer.getButtons(this);
	}

	@Override
	public String toCode() {
		return toString();
	}

	public String getAssignmentString() {
		return assignmentString;
	}

	public void setAssignmentString(String assignmentString) {
		this.assignmentString = assignmentString;
	}

}
