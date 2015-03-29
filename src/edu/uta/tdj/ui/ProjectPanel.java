package edu.uta.tdj.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.project.PackageElement;
import edu.uta.tdj.code.project.ProjectElement;
import edu.uta.tdj.controller.ProjectController;

public class ProjectPanel extends JPanel {
	private JTree projects;

	private static ProjectPanel instance;

	private ProjectPanel() {
		this.setLayout(new GridLayout(1, 1));
		init();
	}

	public static ProjectPanel getInstance() {
		if (instance == null)
			instance = new ProjectPanel();
		return instance;
	}

	DefaultMutableTreeNode top;
	TreeModel treeModel;

	public void init() {
		top = new DefaultMutableTreeNode("projects");

		treeModel = new DefaultTreeModel(top);
		// treeModel.addTreeModelListener(new MyTreeModelListener());
		projects = new JTree(treeModel);
		projects.setEditable(true);
		projects.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		projects.setShowsRootHandles(true);

		projects.setEditable(true);
		reset();
		this.add(new JScrollPane(projects));
	}

	public void reset() {
		top.removeAllChildren();
		for (ProjectElement project : ProjectController.getInstance()
				.getProjectList()) {
			addProject(project);
		}
		this.repaint();
	}

	public void addProject(ProjectElement project) {
		DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(
				project.getName());
		for (PackageElement packageNode : project.getPackages()) {
			addPackage(projectNode, packageNode);
		}
		top.insert(projectNode, top.getChildCount());
		// top.add(projectNode);
	}

	private void addPackage(DefaultMutableTreeNode project,
			PackageElement packagee) {
		DefaultMutableTreeNode packageNode = new DefaultMutableTreeNode(
				packagee.getName());
		for (ComplieUnitElement cue : packagee.getComplieUnitArrayList()) {
			addClass(packageNode, cue);
		}
		project.insert(packageNode, project.getChildCount());
	}

	private void addClass(DefaultMutableTreeNode packagee,
			ComplieUnitElement classs) {
		DefaultMutableTreeNode classNode = new DefaultMutableTreeNode(
				classs.getName());
		System.out.println("add a class " + classs.getName());
		packagee.insert(classNode, packagee.getChildCount());
	}

}
