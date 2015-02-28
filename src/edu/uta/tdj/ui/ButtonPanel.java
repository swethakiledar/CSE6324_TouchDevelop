package edu.uta.tdj.ui;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.uta.tdj.code.component.Element;
import edu.uta.tdj.code.proposal.ProposalButtonFactory;

import java.awt.Dimension;

public class ButtonPanel extends JPanel {

	private static ButtonPanel instance;

	private ButtonPanel() {
		setLayout(new VFlowLayout());
//		this.setPreferredSize(new Dimension(180, 400));
	}

	public static ButtonPanel getInstance() {
		if (instance == null) {
			instance = new ButtonPanel();
		}
		return instance;
	}

	private ArrayList<JButton> buttonList = new ArrayList<JButton>();

	public void setButtonList(ArrayList<JButton> buttonList) {
		this.buttonList = buttonList;

		this.removeAll();
		for (JButton button : buttonList) {
			this.add(button);
		}
		this.updateUI();
	}

	public ArrayList<JButton> getButtonList() {
		return this.buttonList;
	}

	public void setElement(Element element) {
		setButtonList((ArrayList<JButton>) element
				.getButtons(ProposalButtonFactory.getInstance()));
	}
}
