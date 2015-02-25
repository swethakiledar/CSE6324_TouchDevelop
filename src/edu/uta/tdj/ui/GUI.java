package edu.uta.tdj.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;

import javax.swing.ScrollPaneConstants;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import edu.uta.tdj.controller.CodeController;
import edu.uta.tdj.controller.FileController;
import edu.uta.tdj.controller.SelectListener;

public class GUI extends JFrame {

	private static GUI instance;

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

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new FileController());
		mnF.add(mntmSave);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		// add tools panel
		ToolsPanel toolPanel = ToolsPanel.getInstance();
		// add code panels
		CodePanelTabs codePanels = CodePanelTabs.getInstance();
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, codePanels, toolPanel);
        splitPane.setContinuousLayout(true);
        splitPane.setDividerLocation(350);//由jb2011 从200改成现在值
        
        getContentPane().add(splitPane, BorderLayout.CENTER);
        

	}

	public void refresh() {
		this.repaint();
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
		CodeController.init();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 600);
	}
}