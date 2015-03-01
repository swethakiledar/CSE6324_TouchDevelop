package edu.uta.tdj.ui;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import edu.uta.tdj.code.component.ComplieUnitElement;

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
		this.insertTab(cp.getComplieUnitElement().getName(), new CloseTabIcon(
				null), jsp, null, this.getTabCount() - 1);
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
		}
	}

}
