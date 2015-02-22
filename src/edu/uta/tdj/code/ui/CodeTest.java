package edu.uta.tdj.code.ui;

import javax.swing.JFrame;

import edu.uta.tdj.code.controller.CodeController;
import edu.uta.tdj.code.controller.SelectListener;

public class CodeTest extends JFrame{
	public static void main(String arg[]) {
		CodeTest ctCodeTest = new CodeTest();
		ctCodeTest.init();
	}
	
	public void init(){
		CodePanel cp = new CodePanel();
		CodeController.init();
		cp.addMouseListener(new SelectListener());
		this.add(cp);
		this.setVisible(true);
	}
}