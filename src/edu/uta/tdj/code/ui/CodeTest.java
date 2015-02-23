package edu.uta.tdj.code.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import edu.uta.tdj.code.controller.CodeController;
import edu.uta.tdj.code.controller.SelectListener;
import edu.uta.tdj.controller.FileController;
import edu.uta.tdj.ui.CodePanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import edu.uta.tdj.ui.ButtonPanel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JScrollPane;

public class CodeTest extends JFrame {
	
	
	private static CodeTest instance;
	
	public static CodeTest getInstance(){
		if(instance == null)
			instance = new CodeTest();
		return instance;
	}
	
	private CodeTest() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		CodePanel codePanel = new CodePanel();
		codePanel.addMouseListener(new SelectListener());
		GridBagConstraints gbc_codePanel = new GridBagConstraints();
		gbc_codePanel.fill = GridBagConstraints.BOTH;
		gbc_codePanel.weightx = 2.0;
		gbc_codePanel.gridheight = 3;
		gbc_codePanel.gridwidth = 2;
		gbc_codePanel.insets = new Insets(0, 0, 5, 5);
		gbc_codePanel.gridx = 0;
		gbc_codePanel.gridy = 0;
		getContentPane().add(codePanel, gbc_codePanel);

//		 ButtonPanel buttonPanel = new ButtonPanel();

		ButtonPanel buttonPanel = ButtonPanel.getInstance();

		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.weightx = 1.0;
		gbc_buttonPanel.anchor = GridBagConstraints.NORTHEAST;
		gbc_buttonPanel.gridheight = 2;
		gbc_buttonPanel.gridx = 2;
		gbc_buttonPanel.gridy = 0;
		getContentPane().add(buttonPanel, gbc_buttonPanel);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnF = new JMenu("File");
		menuBar.add(mnF);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new FileController());
		mnF.add(mntmSave);
	}
	
	public void refresh(){
		this.setSize(this.getWidth()+1,	 this.getHeight()+1);
		this.setSize(this.getWidth()-1,	 this.getHeight()-1);
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