package edu.uta.tdj.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.component.Element;
import edu.uta.tdj.code.component.MethodElement;
import edu.uta.tdj.controller.ProjectController;
import edu.uta.tdj.ui.CodePanel;
import edu.uta.tdj.ui.GUI;

public class AddMainMethodAction extends ButtonActions {

	@Override
	public void actionPerformed(ActionEvent e) {
		CodePanel cp = ProjectController.getInstance().getSelectedCodePanel();
		ClassElement ce = (ClassElement) cp.getSelectListener()
				.getSelectedElement();

		MethodElement main = ((ComplieUnitElement) ce.getParent()).getPackage()
				.getProjectElement().getCodeFactory().createMainMethodElement();
		
		for(Element method: ce.getChildArrayList()){
			if(method.getName().equalsIgnoreCase(main.getName()) && method instanceof MethodElement ){
				JOptionPane.showMessageDialog(null, "Already existing");
				return;
			}
		}

		ce.addChild(main);

		((ComplieUnitElement) ce.getParent()).getPackage().getProjectElement()
		// get the project
				.setMainClass(((ComplieUnitElement) ce.getParent()));

		((ComplieUnitElement) ce.getParent()).save();
		GUI.getInstance().refresh();

	}
}
