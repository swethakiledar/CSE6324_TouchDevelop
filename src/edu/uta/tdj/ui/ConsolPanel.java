package edu.uta.tdj.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

public class ConsolPanel extends JPanel {
	
	private JTextArea ta_Console = new JTextArea();
	private JToolBar  toolBar = new JToolBar();

	private ConsolPanel() {
		this.setBorder(null);
		this.setLayout(new BorderLayout(0, 0));
		this.add(toolBar,BorderLayout.PAGE_START);
		this.add(getTextAreaPanel());
		this.setBackground(Color.black);
		initTools();
	}
	
	
	private JPanel getTextAreaPanel() {
		JPanel textArePanel = new JPanel();
		textArePanel.setLayout(new GridLayout(1, 1));
		JScrollPane jst = new JScrollPane(ta_Console);
		textArePanel.add(jst);
		textArePanel.setBackground(Color.orange);
//		textArePanel.setBorder(null);
		jst.setBorder(null);
		return textArePanel;

	}

	private void initTools(){
//		Icon icon = new DiamondIcon((Color)color[COLOR_POSITION], true, 20, 20);
		toolBar.setFloatable(false);
		JButton button = new JButton("ss");
		toolBar.add(button);
	}
	
	private static ConsolPanel instance;

	public static ConsolPanel getInstance() {
		if (instance == null)
			instance = new ConsolPanel();
		return instance;
	}

	public void setConsoleText(String text) {
		this.ta_Console.append(text);
	}
	
	public void clearConsole() {
		this.ta_Console.setText("");
	}

}
