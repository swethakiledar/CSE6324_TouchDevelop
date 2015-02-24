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

public class CodeTest extends JFrame {

	private static CodeTest instance;

	public static CodeTest getInstance() {
		if (instance == null)
			instance = new CodeTest();
		return instance;
	}

	private CodeTest() {
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
		this.setSize(this.getWidth() + 1, this.getHeight() + 1);
		this.setSize(this.getWidth() - 1, this.getHeight() - 1);
	}

	public static void main(String arg[]) {
		CodeTest ctCodeTest = new CodeTest();
		ctCodeTest.init();
	}

	public void init() {
		CodeController.init();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}