package edu.uta.tdj.ui.actions;

import java.awt.event.ActionEvent;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.component.MethodElement;
import edu.uta.tdj.controller.ProjectController;
import edu.uta.tdj.ui.CodePanel;

public class AddMainMethodAction extends ButtonActions {

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		CodePanel cp = ProjectController.getInstance()
				.getSelectedCodePanel();
		ClassElement ce = (ClassElement) cp.getSelectListener()
				.getSelectedElement();

		MethodElement main = ((ComplieUnitElement) ce.getParent()).getPackage()
				.getProjectElement().getCodeFactory().createMainMethodElement();

		ce.addChild(main);

		((ComplieUnitElement) ce.getParent())
				.getPackage() 
				.getProjectElement()   // get the project 
				.setMainClass(
						((ComplieUnitElement) ce.getParent()).getPackage()
								.getName()
								+ "."
								+ ((ComplieUnitElement) ce.getParent())
										.getName());
		
		((ComplieUnitElement) ce.getParent()).save();

	}
}
