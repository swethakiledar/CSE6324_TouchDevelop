package edu.uta.tdj.ui.actions;

import java.awt.event.ActionEvent;

import edu.uta.tdj.code.component.Element;
import edu.uta.tdj.controller.ProjectController;
import edu.uta.tdj.factory.CodeFactory;
import edu.uta.tdj.ui.GUI;


public class AdPrintAction extends ButtonActions{

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Element element = ProjectController.getInstance()
				.getSelectedCodePanel().getSelectListener()
				.getSelectedElement();
		
		CodeFactory  factory = ProjectController.getInstance()
		.getSelectedCodePanel().getComplieUnitElement().getPackage().getProjectElement().getCodeFactory();
		
		Element newElement = factory.createMethodInvocationElement("TEST!");
		element.addChild(newElement);
		
		ProjectController.getInstance().getSelectedCodePanel()
				.getComplieUnitElement().save();
		GUI.getInstance().refresh();
	}

}
