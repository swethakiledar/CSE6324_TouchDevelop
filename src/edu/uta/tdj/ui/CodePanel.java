package edu.uta.tdj.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.controller.CodeController;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class CodePanel extends JPanel {

	public CodePanel() {
		this.setBackground(Color.orange);
		this.setPreferredSize(new Dimension(200, 1000));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ClassElement ce = CodeController.getCode();
		this.setPreferredSize(new Dimension(1000, ce.getHeight() + 200));
		if (ce != null)
			ce.draw(g);
	}
}
