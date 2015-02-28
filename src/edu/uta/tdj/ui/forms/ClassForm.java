package edu.uta.tdj.ui.forms;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class ClassForm extends Form {

	public ClassForm() {
		super();
		String[][] item_value_array = { { "Class Name", "" } };
		super.setTableItem(item_value_array);
		setUpdateData();
	}

	@Override
	public void setUpdateData() {
		table.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					String name = table.getValueAt(0, 1).toString();
					setName(name);
				}
			}
		});
	}
}
