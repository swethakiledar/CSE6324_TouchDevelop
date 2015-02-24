package edu.uta.tdj.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.uta.tdj.controller.CodeController;
import edu.uta.tdj.factory.CodeFactory;


public class AddMainMethodAction implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CodeController.addElement(CodeFactory.getInstance().createMainMethodElement());
	}
	
}
