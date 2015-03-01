package edu.uta.tdj.ui.forms;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class ClassForm extends Form {

	public ClassForm() {
		super();
		String[][] item_value_array = { { "Class Name", "" } };
		super.setTableItem(item_value_array);
	}

	@Override
	public void setTableValue() {
		this.table.getModel().setValueAt(element.getName(), 0, 1);
	}

	@Override
	public void updateElement() {
		element.setName((String) this.table.getValueAt(0, 1));
		System.out.println( this.table.getValueAt(0, 1));
	}
}
