package edu.uta.tdj.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.Box;

public class ButtonPanel extends JPanel {

	private static ButtonPanel instance;

	private ButtonPanel() {
		setLayout(new GridLayout(0, 1));
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
		this.repaint();
	}

	public ArrayList<JButton> getButtonList() {
		return this.buttonList;
	}
}
