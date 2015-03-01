package edu.uta.tdj.ui.actions;

import java.awt.event.ActionEvent;

import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.component.statment.IfStatementElement;
import edu.uta.tdj.controller.ProjectController;
import edu.uta.tdj.ui.CodePanel;
import edu.uta.tdj.ui.GUI;

public class AddIfStatementAction extends ButtonActions {

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		CodePanel cp = ProjectController.getInstance().getSelectedCodePanel();
		IfStatementElement ifstatement = cp.getComplieUnitElement()
				.getPackage().getProjectElement().getCodeFactory()
				.createIfStatementElement();
		cp.getSelectListener().getSelectedElement().addChild(ifstatement);

		cp.getComplieUnitElement().save();
		GUI.getInstance().refresh();
	}
}
