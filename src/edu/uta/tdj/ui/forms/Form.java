package edu.uta.tdj.ui.forms;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.uta.tdj.code.component.Element;
import edu.uta.tdj.ui.GUI;
import edu.uta.tdj.ui.VFlowLayout;

public abstract class Form extends JPanel {
	protected Element element;
	protected JTable table;
	private DefaultTableCellRenderer tcr;

	public Form() {
		setLayout(new VFlowLayout());
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setRowSelectionAllowed(true);
		add(table);
		table.setAutoscrolls(true);
		tcr = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component cell = super.getTableCellRendererComponent(table,
						value, isSelected, hasFocus, row, column);
//				if (column == 0 && cell.isBackgroundSet()) {
//					cell.setBackground(new Color(225, 234, 234));
//				}
				return cell;
			}
		};
	}
	
	protected abstract void setUpdateData();
	
	protected void setTableItem(String[][] item_value_array) {

		table.setModel(new DefaultTableModel(item_value_array, new String[] {
				"Item", "Value" }) {
			Class[] columnTypes = new Class[] { String.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int rowindex, int colindex) {
				if (colindex == 0)
					return false;
				return true;
			}
		});

		table.getColumn("Item").setCellRenderer(tcr);
		table.getColumn("Value").setCellRenderer(tcr);
	}

	public void setElement(Element element) {
		this.element = element;
		this.setName(element.getName());
	}

	private String oldName;

	// set the class name to the cell
	public void setName(String name) {
		if (oldName != name) {
			oldName = name;
			table.getModel().setValueAt(name, 0, 1);
			element.setName(name);
			GUI.getInstance().refresh();
		}
	}

	// get the class name from the table's cell
	public String getName() {
		String name = (String) table.getModel().getValueAt(0, 1);
		return name;
	}

}
