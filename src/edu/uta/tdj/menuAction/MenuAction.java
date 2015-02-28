package edu.uta.tdj.menuAction;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import edu.uta.tdj.compiler.JavaBuilder;
import edu.uta.tdj.controller.ProjectController;

public class MenuAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "New Project":
			ProjectController.getInstance().newProject();
			break;
		case "New Package":
			ProjectController.getInstance().newPackage();
			break;
		case "New Class":
			ProjectController.getInstance().newComplieUnitElement();
			break;
		case "Complie":
			JavaBuilder.execute(ProjectController.getInstance()
					.getSelectedProjectElement().getComplieUnitElement()
					.getPackage().getProjectElement());
			break;
		default:
			break;
		}
	}

}
