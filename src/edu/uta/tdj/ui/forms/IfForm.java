package edu.uta.tdj.ui.forms;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import edu.uta.tdj.code.component.*;

public class IfForm extends Form {

	JComboBox comboBox = new JComboBox();
	JComboBox cb_Operator = new JComboBox();

	// MyCellEditor cellEditor = new MyCellEditor(comboBox);

	public IfForm() {
		super();
		String[][] item_value_array = { { "type", "" } };
		super.setTableItem(item_value_array);

		comboBox.addItem("boolean");
		comboBox.addItem("expression");

		cb_Operator.addItem("==");
		cb_Operator.addItem("!=");
		cb_Operator.addItem(">");
		cb_Operator.addItem("<");
		cb_Operator.addItem(">=");
		cb_Operator.addItem("<=");

		table.addComboCell(0, new DefaultCellEditor(comboBox));
	}

	@Override
	public void setTableValue() {

	}

	String oldBooleanString = "";

	@Override
	public void updateElement() {
		// TODO Auto-generated method stub
		if (comboBox.getSelectedIndex() == 0 && table.getSelectedColumn() == 1
				&& table.getSelectedRow() == 0) {
			String[][] item_value_array = { { "type", "boolean" },
					{ "boolean", ((IfStatement) element).getExpression() } };
			super.setTableItem(item_value_array);

		}
		if (comboBox.getSelectedIndex() == 0 && table.getSelectedColumn() == 1
				&& table.getSelectedRow() == 1) {
			((IfStatement) element).setExpression((String) table.getValueAt(1,
					1));
		}

		if (comboBox.getSelectedIndex() == 1 && table.getSelectedColumn() == 1) {
			if (table.getSelectedRow() == 0) {
				String[] left = { "left", "" };
				String[] right = { "right", "" };
				String[] operator = { "operator", "==" };
				String[][] item_value_array = { { "type", "expression" }, left,
						operator, right };
				super.setTableItem(item_value_array);
				table.addComboCell(2, new DefaultCellEditor(cb_Operator));
			} else {
				((IfStatement) element).setExpression((String) table
						.getValueAt(1, 1)
						+ (String) table.getValueAt(2, 1)
						+ (String) table.getValueAt(3, 1));
			}
		}

	}
}
