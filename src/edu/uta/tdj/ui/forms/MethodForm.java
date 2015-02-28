package edu.uta.tdj.ui.forms;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


public class MethodForm extends Form{
	
//	private static ClassForm instanceClassForm;

//	public static ClassForm getInstance() {
//		if (instanceClassForm == null)
//			instanceClassForm = new ClassForm();
//		return instanceClassForm;
//	}

	public MethodForm() {
		super();
		String[][] item_value_array = { { "Method Name", "" } };
		super.setTableItem(item_value_array);
		setUpdateData();
	}

	//set update cells
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
