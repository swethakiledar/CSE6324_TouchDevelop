package edu.uta.tdj.ui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

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
	DefaultTreeModel treeModel;

	public void init() {
		top = new DefaultMutableTreeNode("projects");
		projects = new JTree(top);
		projects.setEditable(true);
		projects.addMouseListener(new MouseHandle());
		treeModel = (DefaultTreeModel) projects.getModel();
		this.add(new JScrollPane(projects));
		reset();
	}

	public void reset() {
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) treeModel
				.getRoot();
		rootNode.removeAllChildren();
		treeModel.reload();
		for (ProjectElement project : ProjectController.getInstance()
				.getProjectList()) {
			addProject(project);
		}
		this.repaint();
	}

	public void addProject(ProjectElement project) {

		DefaultMutableTreeNode parentNode = null;
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
				project.getName());
		newNode.setAllowsChildren(true);

		for (PackageElement packageNode : project.getPackages()) {
			addPackage(newNode, packageNode);
		}

		// parent of the project is the root(top)
		parentNode = top;
		// add the new node in to the root
		treeModel.insertNodeInto(newNode, parentNode,
				parentNode.getChildCount());
		// tree的scrollPathToVisible()方法在使Tree会自动展开文件夹以便显示所加入的新节点。若没加这行则加入的新节点
		// 会被 包在文件夹中，你必须自行展开文件夹才看得到。
		projects.scrollPathToVisible(new TreePath(newNode.getPath()));
	}

	private void addPackage(DefaultMutableTreeNode project,
			PackageElement packagee) {
		DefaultMutableTreeNode packageNode = new DefaultMutableTreeNode(
				packagee.getName());
		packageNode.setAllowsChildren(true);

		for (ComplieUnitElement cue : packagee.getComplieUnitArrayList()) {
			addClass(packageNode, cue);
		}
		treeModel.insertNodeInto(packageNode, project, project.getChildCount());

		projects.scrollPathToVisible(new TreePath(packageNode.getPath()));
	}

	private void addClass(DefaultMutableTreeNode packagee,
			ComplieUnitElement classs) {

		DefaultMutableTreeNode classNode = new DefaultMutableTreeNode(
				classs.getName());
		classNode.setAllowsChildren(true);
		treeModel.insertNodeInto(classNode, packagee, packagee.getChildCount());
		projects.scrollPathToVisible(new TreePath(classNode.getPath()));

	}

	class MouseHandle extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if (e.getClickCount() == 2) {
				try {
					JTree tree = (JTree) e.getSource();
					int rowLocation = tree
							.getRowForLocation(e.getX(), e.getY());
					TreePath treepath = tree.getPathForRow(rowLocation);
					TreeNode treenode = (TreeNode) treepath
							.getLastPathComponent();
					if (treepath.getPathCount() == 4) {
						int classIndex = treenode.getParent()
								.getIndex(treenode);
						int packageIndex = treenode.getParent().getParent()
								.getIndex(treenode.getParent());
						int projectIndex = treenode.getParent().getParent()
								.getParent()
								.getIndex(treenode.getParent().getParent());
						ProjectController.getInstance().showSelectedCodePanel(
								projectIndex, packageIndex, classIndex);
					}
				} catch (NullPointerException ne) {
				}
			}
		}
	}

}
