package edu.uta.tdj.ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import java.awt.Dialog.ModalExclusionType;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import edu.uta.tdj.controller.FileController;
import edu.uta.tdj.menuAction.MenuAction;

public class GUI extends JFrame {

	private static GUI instance;
	private MenuAction mAction;

	public static GUI getInstance() {
		if (instance == null)
			instance = new GUI();
		return instance;
	}

	private GUI() {

		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnF = new JMenu("File");
		menuBar.add(mnF);

		mAction = new MenuAction();

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new FileController());
		mnF.add(mntmSave);

		JMenuItem mntmNewProject = new JMenuItem("New Project");
		mntmNewProject.addActionListener(mAction);
		mnF.add(mntmNewProject);

		JMenuItem mntmNewPackage = new JMenuItem("New Package");
		mntmNewPackage.addActionListener(mAction);
		mnF.add(mntmNewPackage);

		JMenuItem mntmNewClass = new JMenuItem("New Class");
		mntmNewClass.addActionListener(mAction);
		mnF.add(mntmNewClass);

		JMenuItem mntmComplie = new JMenuItem("Complie");
		mntmComplie.addActionListener(mAction);
		mnF.add(mntmComplie);
		
		JMenuItem mntmWorkspaceItem = new JMenuItem("Set Workspace");
		mntmWorkspaceItem.addActionListener(mAction);
		mnF.add(mntmWorkspaceItem);
		
		JMenuItem mntmOpenprojectItem = new JMenuItem("Open project");
		mntmOpenprojectItem.addActionListener(mAction);
		mnF.add(mntmOpenprojectItem);
		
		getContentPane().setLayout(new BorderLayout(0, 0));

		// add tools panel
		ToolsPanel toolPanel = ToolsPanel.getInstance();
		// add code panels
		CodePanelTabs codePanels = CodePanelTabs.getInstance();
		// add project panel
		ProjectPanel projectPanel = ProjectPanel.getInstance();

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				codePanels, toolPanel);
		splitPane.setContinuousLayout(true);
		splitPane.setDividerLocation(350);// 由jb2011 从200改成现在值
		splitPane.setBorder(null);

		JSplitPane splitPaneMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				projectPanel, splitPane);
		getContentPane().add(splitPaneMain, BorderLayout.CENTER);

	}

	public void refresh() {
		this.repaint();
		ToolsPanel.getInstance().updateUI();
		CodePanelTabs.getInstance().getInstance().updateUI();
	}

	public static void main(String arg[]) {
		try {
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			// TODO exception
		}
		GUI ctCodeTest = GUI.getInstance();
		ctCodeTest.init();
	}

	public void init() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 600);
	}
}