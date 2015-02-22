package edu.uta.tdj.code.ui;

import javax.swing.JFrame;

public class CodeTest extends JFrame{
	public static void main(String arg[]) {
		CodeTest ctCodeTest = new CodeTest();
		ctCodeTest.init();
	}
	
	public void init(){
		CodePanel cp = new CodePanel();
		this.add(cp);
		this.setVisible(true);
	}
}