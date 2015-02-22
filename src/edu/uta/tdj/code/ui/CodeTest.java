package edu.uta.tdj.code.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import edu.uta.tdj.code.controller.CodeController;
import edu.uta.tdj.code.controller.SelectListener;
import edu.uta.tdj.controller.FileController;

public class CodeTest extends JFrame {
	public static void main(String arg[]) {
		CodeTest ctCodeTest = new CodeTest();
		ctCodeTest.init();
	}

	public void init() {
		CodePanel cp = new CodePanel();
		CodeController.init();
		cp.addMouseListener(new SelectListener());
		this.add(cp);
		this.setVisible(true);

		JMenuBar jMenuBar = new JMenuBar();
		JMenu menu = new JMenu("FILE");
		JMenuItem jMenuItem_SAVE = new JMenuItem("SAVE");
		
		menu.add(jMenuItem_SAVE);
		jMenuBar.add(menu);
		
		this.add(jMenuBar,BorderLayout.BEFORE_FIRST_LINE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jMenuItem_SAVE.addActionListener(new FileController());
	}
}