package edu.uta.tdj.ui.actions;

import java.awt.event.ActionEvent;
import edu.uta.tdj.controller.CodeController;
import edu.uta.tdj.factory.CodeFactory;

public class AddMainMethodAction extends ButtonActions {

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		CodeController.getSelectedElement().addChild(
				CodeFactory.getInstance().createMainMethodElement());
	}
}
