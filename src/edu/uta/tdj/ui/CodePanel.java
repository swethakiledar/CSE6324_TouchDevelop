package edu.uta.tdj.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.controller.CodeController;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class CodePanel extends JPanel {
	
	public CodePanel(){
		this.setBackground(Color.LIGHT_GRAY);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		ClassElement ce = CodeController.getCode();
		if (ce != null)
			ce.draw(g);
	}
}
