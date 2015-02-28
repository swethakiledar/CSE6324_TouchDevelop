package edu.uta.tdj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.project.PackageElement;
import edu.uta.tdj.code.project.ProjectElement;
import edu.uta.tdj.ui.CodePanel;
import edu.uta.tdj.ui.CodePanelTabs;

public class ProjectController {
	private static ProjectController instance;
	private ArrayList<ProjectElement> projectList = new ArrayList<ProjectElement>();

	public ArrayList<ProjectElement> getProjectList() {
		return projectList;
	}

	private String workspace = "g:/testWorkspace/";

	private ProjectController() {
	}

	public static ProjectController getInstance() {
		if (instance == null) {
			instance = new ProjectController();
		}
		return instance;
	}

	public ProjectElement newProject(String projectname) {
		ProjectElement projectElement = new ProjectElement(workspace,
				projectname);
		projectElement.save();
		this.projectList.add(projectElement);

		return projectElement;
	}

	public ProjectElement newProject() {

		String result = JOptionPane.showInputDialog(null, "Project Name",
				"Input the Project Name", JOptionPane.QUESTION_MESSAGE);
		if ((result == null) || (result.length() <= 0)) {
			JOptionPane
					.showMessageDialog(null, "Please input the Project Name");
		} else {
			return newProject(result);
		}
		return null;
	}

	public PackageElement newPackage(String name) {
		PackageElement packageElement = new PackageElement(name);
		return packageElement;
	}

	public PackageElement newPackage() {
		Object[] message = new Object[4];
		message[0] = "Please Input The Package Name";
		message[1] = new JTextField();

		JComboBox cb = new JComboBox();
		for (ProjectElement projectElement : projectList) {
			cb.addItem(projectElement.getName());
		}
		message[3] = cb;
		message[2] = "Please select the project";

		// Options
		String[] options = { "OK", "Cancel" };
		int result = JOptionPane.showOptionDialog(null, // the
				message, // the dialog message array
				"New Package", // the title
				JOptionPane.DEFAULT_OPTION, // option type
				JOptionPane.INFORMATION_MESSAGE, // message type
				null, // optional icon, use null to use the default icon
				options, // options string array, will be made into
							// buttons
				options[0] // option that should be made into a default
							// button
				);
		switch (result) {
		case 0: // yes
			String packagename = ((JTextField) message[1]).getText();
			int i = cb.getSelectedIndex();
			PackageElement packageElement = newPackage(packagename);
			projectList.get(i).addPackage(packageElement);
			packageElement.save();
			return packageElement;
		default:
			break;
		}

		return null;
	}

	public ComplieUnitElement newComplieUnitElement(ProjectElement project,
			String name) {
		ComplieUnitElement cue = project.getCodeFactory()
				.createComplieUnitElement(name);
		cue.setName(name);
		return cue;
	}

	public ComplieUnitElement newComplieUnitElement() {

		Object[] message = new Object[6];
		message[0] = "Please Input The Class Name";
		message[1] = new JTextField();
		message[2] = "Please select the package";
		final JComboBox packages = new JComboBox();
		message[3] = packages;
		message[4] = "Please select the Project";

		final JComboBox projects = new JComboBox();
		for (ProjectElement projectElement : projectList) {
			projects.addItem(projectElement.getName());
		}

		int iss = projects.getSelectedIndex();
		if (iss >= projectList.size() - 1) {
			ProjectElement selectedproject = projectList.get(iss);
			if (selectedproject != null) {
				packages.removeAllItems();
				for (PackageElement pe : selectedproject.getPackages()) {
					packages.addItem(pe.getName());
					System.out.println(pe.getName());
				}
			}

		}
		projects.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = projects.getSelectedIndex();
				ProjectElement selectedproject = projectList.get(i);
				packages.removeAllItems();
				for (PackageElement pe : selectedproject.getPackages()) {
					packages.addItem(pe.getName());
					System.out.println(pe.getName());
				}

			}
		});
		message[5] = projects;
		// Options
		String[] options = { "OK", "Cancel" };
		int result = JOptionPane.showOptionDialog(null, // the
				message, // the dialog message array
				"New Class", // the title
				JOptionPane.DEFAULT_OPTION, // option type
				JOptionPane.INFORMATION_MESSAGE, // message type
				null, // optional icon, use null to use the default icon
				options, // options string array, will be made into
							// buttons
				options[0] // option that should be made into a default
							// button
				);
		switch (result) {
		case 0: // yes
			String classname = ((JTextField) message[1]).getText();
			int projectid = projects.getSelectedIndex();
			int packageid = packages.getSelectedIndex();

			ProjectElement projectElement = projectList.get(projectid);
			PackageElement packageElement = projectElement.getPackages().get(
					packageid);

			ComplieUnitElement ce = newComplieUnitElement(
					projectList.get(projectid), classname);

			packageElement.addComplieUnit(ce);
			ce.save();
			CodePanelTabs.getInstance().addCodePanel(ce);

			break;
		default:
			break;
		}

		return null;

		// ComplieUnitElement cue = new ComplieUnitElement();
		// cu.types().add(ce.getAstNode());
		// CodePanelTabs.getInstance().addCodePanel(ce);
		// return null;
	}

	public CodePanel getSelectedCodePanel() {
		JScrollPane comp = (JScrollPane) CodePanelTabs.getInstance()
				.getSelectedComponent();
		if (comp == null)
			return null;
		CodePanel cp = (CodePanel) comp.getViewport().getView();
		return cp;

		// return projectList.get(0);
	}

	public static void main(String[] args) {
		ProjectController.getInstance().newProject();
		ProjectController.getInstance().newPackage();
		ProjectController.getInstance().newComplieUnitElement();
	}
}
