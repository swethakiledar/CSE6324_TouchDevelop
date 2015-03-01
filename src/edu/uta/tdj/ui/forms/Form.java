package edu.uta.tdj.ui.forms;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
				return cell;
			}
		};
	}

	private void setUpdateData() {
		table.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					updateElement();
					GUI.getInstance().refresh();
				}
			}
		});
	}

	protected void setTableItem(String[][] item_value_array) {

		table.setModel(new DefaultTableModel(item_value_array, new String[] {
				"Item", "Value" }) {
			Class[] columnTypes = new Class[] { String.class, String.class };

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
		
		setUpdateData();
	}

	public void setElement(Element element) {
		this.element = element;
		setTableValue();
	}

	public abstract void setTableValue();

	public abstract void updateElement();
}
