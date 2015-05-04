package edu.uta.tdj.controller;

import java.awt.event.MouseListener;
import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.component.Element;
import edu.uta.tdj.ui.CodePanel;
import edu.uta.tdj.ui.GUI;
import edu.uta.tdj.ui.ToolsPanel;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class SelectListener implements MouseListener {

	private CodePanel cp;

	public SelectListener(CodePanel cp) {
		this.cp = cp;
		cp.addMouseListener(this);
	}

	// get the Class element
	public ComplieUnitElement getCode() {
		return cp.getComplieUnitElement();
	}

	private Element lastSelectedElement;

	// select the element and return it
	public Element selectedElement(int x_in, int y_in) {
		Element selectedElement = getCode().getSelectedElement(x_in, y_in);
		getCode().unSelected();
		if (lastSelectedElement != null
				&& lastSelectedElement == selectedElement) {
			getCode().unSelected();
		}else
		if (selectedElement != null) {
			lastSelectedElement = selectedElement;
			selectedElement.setSelected(!selectedElement.isSelected());
			showTools(selectedElement);
		}
		return selectedElement;
	}

	// return the selected element
	public Element getSelectedElement() {
		return lastSelectedElement;
	}

	// add a child for the selected element
	public void addElement(Element element) {
		getSelectedElement().addChild(element);
	}

	// show the tools (buttons and form)
	public void showTools(Element element) {
		ToolsPanel.getInstance().setElement(element);
		GUI.getInstance().refresh();
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		selectedElement(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
