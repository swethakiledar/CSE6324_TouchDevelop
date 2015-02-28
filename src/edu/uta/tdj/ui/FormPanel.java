package edu.uta.tdj.ui;

import java.awt.Dimension;

import javax.swing.JPanel;

import edu.uta.tdj.code.component.Element;
import edu.uta.tdj.ui.forms.Form;

public class FormPanel extends JPanel {

	private Form insidePanel;

	private static FormPanel instanceFormPanel;

	private FormPanel() {
//		this.setPreferredSize(new Dimension(180,200));
		this.setLayout(new VFlowLayout());
	}

	public static FormPanel getInstance() {
		if (instanceFormPanel == null)
			instanceFormPanel = new FormPanel();
		return instanceFormPanel;
	}

	public void setInsidePanel(Form panel) {
		this.insidePanel = panel;
		this.removeAll();
		this.add(panel);
		this.repaint();
		this.updateUI();
	}

	public Form getInsidePanel() {
		return insidePanel;
	}
	
	public void setElement(Element element){
		this.setInsidePanel(element.getForm());
	}
	
}
