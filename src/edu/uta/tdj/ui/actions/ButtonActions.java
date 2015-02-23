package edu.uta.tdj.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.uta.tdj.code.controller.CodeController;
import edu.uta.tdj.code.factory.CodeFactory;


public class ButtonActions implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CodeController.addElement(CodeFactory.getInstance().createMainMethodElement());
	}
	
}
