package edu.uta.tdj.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import edu.uta.tdj.code.component.Element;

public class ToolsPanel extends JPanel {

	private static ToolsPanel instance;

	private FormPanel fp = FormPanel.getInstance();
	private ButtonPanel bp = ButtonPanel.getInstance();
	private ConsolPanel cp = ConsolPanel.getInstance();

	private ToolsPanel() {
//		this.setPreferredSize(new Dimension(200, 1000));
		this.init();
	}

	public static ToolsPanel getInstance() {
		if (instance == null)
			instance = new ToolsPanel();
		return instance;
	}

	public void init() {
		this.setBackground(Color.black);
		JScrollPane scollPane_BP = new JScrollPane(bp);
		JScrollPane scrollPane_FP = new JScrollPane(fp);
		JScrollPane scrollpane_CP = new JScrollPane(cp);
		setLayout(new BorderLayout(0, 0));
		JSplitPane splitPane_tool = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				scollPane_BP, scrollPane_FP);
		splitPane_tool.setBorder(null);
		JSplitPane splitPanewithConsole = new JSplitPane(JSplitPane.VERTICAL_SPLIT,splitPane_tool,scrollpane_CP);
//		splitPane_tool.setDividerLocation(350);// 由jb2011 从200改成现在值
		splitPanewithConsole.setContinuousLayout(true);
		this.add(splitPanewithConsole);

	}

	public void setElement(Element element) {
		fp.setElement(element);
		bp.setElement(element);
	}
}
