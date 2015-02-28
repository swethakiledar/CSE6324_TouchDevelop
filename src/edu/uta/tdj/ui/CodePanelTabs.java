package edu.uta.tdj.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.controller.CodeController;
import edu.uta.tdj.controller.SelectListener;

public class CodePanelTabs extends JClosableTabbedPane {
	private CodePanelTabs() {
		addADDtab();
	}

	private static CodePanelTabs instance;

	public static CodePanelTabs getInstance() {
		if (instance == null) {
			instance = new CodePanelTabs();
		}
		return instance;
	}

	private ArrayList<CodePanel> codePanel_al = new ArrayList<CodePanel>();

	public void addCodePanel(CodePanel cp) {
		this.codePanel_al.add(cp);
		JScrollPane jsp = new JScrollPane(cp);
		this.insertTab(cp.getComplieUnitElement().getName(), new CloseTabIcon(null),
				jsp, null, this.getTabCount() - 1);
	}

	public void addCodePanel(ComplieUnitElement ce) {
		CodePanel cp = new CodePanel();
		cp.setComplieUnitElement(ce);
		addCodePanel(cp);
	}

	public void addADDtab() {
		this.addTab("", null, new JLabel());
	}

	@Override
	protected void addNewTab() {
		String result = JOptionPane.showInputDialog(this, "Class Name",
				"Input the Class Name", JOptionPane.QUESTION_MESSAGE);
		if ((result == null) || (result.length() <= 0)) {
			JOptionPane.showMessageDialog(this, "Please input the Class Name");
		} else {
			CodeController.newCodePanel(result);
		}
	}

}
