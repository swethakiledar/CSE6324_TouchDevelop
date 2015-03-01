package edu.uta.tdj.ui.actions;

import java.awt.event.ActionEvent;

import edu.uta.tdj.code.component.Element;
import edu.uta.tdj.controller.ProjectController;
import edu.uta.tdj.ui.GUI;

public class RemoveAction extends ButtonActions {

	@Override
	public void actionPerformed(ActionEvent e) {
		Element element = ProjectController.getInstance()
				.getSelectedCodePanel().getSelectListener()
				.getSelectedElement();
		element.delete();
		System.out.println("remove");
		GUI.getInstance().refresh();
	}
}
