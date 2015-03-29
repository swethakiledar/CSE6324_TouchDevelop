package edu.uta.tdj.menuAction;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import edu.uta.tdj.compiler.JavaBuilder;
import edu.uta.tdj.controller.ProjectController;
import edu.uta.tdj.controller.PropertyController;

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
					.getSelectedCodePanel().getComplieUnitElement()
					.getPackage().getProjectElement());
			break;
		case "Set Workspace":
			
			JFileChooser fd = new JFileChooser();
			fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fd.setCurrentDirectory(new File(PropertyController.getProperties().getProperty("workspace")));
			fd.showOpenDialog(null);
			
			File f = fd.getSelectedFile();
			if(f != null){
				PropertyController.getProperties().setProperty("workspace", f.getAbsolutePath());
				PropertyController.save();
			}

			break;
		case "Open project":
			JFileChooser fd2 = new JFileChooser();
			fd2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fd2.setCurrentDirectory(new File(PropertyController.getProperties().getProperty("workspace")));
			fd2.showOpenDialog(null);
			File f2 = fd2.getSelectedFile();
			if(f2 != null){
				ProjectController.getInstance().openProject(f2.getAbsolutePath());
			}
			break;
		default:
			break;
		}
	}

}
