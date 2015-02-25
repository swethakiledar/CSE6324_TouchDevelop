package edu.uta.tdj.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.controller.SelectListener;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class CodePanel extends JPanel {

	private ClassElement ce;
	private SelectListener sl;
	public CodePanel() {
		this.sl =  new SelectListener(this);
		this.setBackground(Color.orange);
		this.setPreferredSize(new Dimension(200, 1000));
	}
	public void setClassElement(ClassElement ceElement) {
		this.ce = ceElement;
	}
	
	public ClassElement getClassElement(){
		return ce;
	}
	
	public String getCodePanelName(){
		return ce.getName();
	}
	
	public SelectListener  getSelectListener(){
		return this.sl;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setPreferredSize(new Dimension(1000, ce.getHeight() + 200));
		if (ce != null)
			ce.draw(g);
	}

}
