package edu.uta.tdj.code.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.controller.CodeController;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * @author Fuqiang Zhang
 */

public class CodePanel extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ClassElement ce = CodeController.getCode();
		ce.draw(g);
	}
}
