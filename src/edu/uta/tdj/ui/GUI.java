package edu.uta.tdj.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;

import javax.swing.ScrollPaneConstants;

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
		ToolsPanel toolPanel = new ToolsPanel();
		getContentPane().add(toolPanel, BorderLayout.EAST);
		// add code panel
		CodePanel codePanel = new CodePanel();
		codePanel.addMouseListener(new SelectListener());

		JScrollPane scrollPane = new JScrollPane(codePanel);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

	}

	public void refresh() {
		this.repaint();
	}

	public static void main(String arg[]) {
		GUI ctCodeTest = GUI.getInstance();
		ctCodeTest.init();
	}

	public void init() {
		CodeController.init();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}