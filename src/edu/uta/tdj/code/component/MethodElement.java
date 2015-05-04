package edu.uta.tdj.code.component;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;

import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.MethodForm;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class MethodElement extends Element {

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return pcComputer.getButtons(this);
	}

	private String modifiedString = "";
	private String returnTypeString = "";
	private String accessString = "";
	
	private HashMap< String, String> paramterMap = new HashMap<>();
	

	public MethodElement() {
		setHeight(50);
		defaultHeight = 50;
		this.form = new MethodForm();
		form.setElement(this);
	}

	public void setName(String name) {
		this.name = name;
		this.width = name.length() * 6;
	}

	public void setModifiers(String modifiers) {
		this.modifiedString = modifiers;
	}
	
	public void setAccess(String access){
		this.accessString = access;
	}
	
	public String getAccess(){
		return this.accessString;
	}
	
	public String getModifiers(){
		return this.modifiedString;
	}

	public void addParam(String name, String type) {
		paramterMap.put(name, type);
	}

	
	public void setReturnType(String type) {
		returnTypeString = type;
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
		element.setParent(this);
		childArrayList.add(element);
		element.setX(x + 20);
		element.setY(y + getHeight() - 30);
		setHeight(getHeight() + element.getHeight());
		reSort();
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.blue);
		g.drawString(this.toString(), x, y + 20);
		for (Element ee : childArrayList) {
			ee.draw(g);
		}
		g.setColor(Color.blue);
		g.drawString("}", x, getHeight() + y - 10);
	}

	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		for(String name : paramterMap.keySet()){
			sb.append( paramterMap.get(name)+ " "+name);
			sb.append(",");
		}
		
		
		if(sb.length()!=0)
			sb.replace(sb.length()-1, sb.length()-0, "");
		
		return accessString + " " + modifiedString + " " + returnTypeString + " " + name + "("
				+ sb.toString() + ")"
				+ "{";
	}

	@Override
	public String toCode() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.toString());
		sb.append(System.getProperty("line.separator"));
		for(Element e : childArrayList){
			sb.append(e.toCode());
			sb.append(System.getProperty("line.separator"));
		}
		sb.append("}");
		
		return sb.toString();
	}

}
