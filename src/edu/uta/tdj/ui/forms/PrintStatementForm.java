package edu.uta.tdj.ui.forms;

import edu.uta.tdj.code.component.expression.MethodInvocationElement;

public class PrintStatementForm extends Form {

	public PrintStatementForm() {
		super();
		String[][] item_value_array = { { "Print Value", "" } };
		super.setTableItem(item_value_array);
	}

	@Override
	public void setTableValue() {
		this.table.getModel().setValueAt(((MethodInvocationElement) element).getArguments(), 0, 1);
	}

	String oldvalue = "";

	@Override
	public void updateElement() {
		String newname = (String) this.table.getValueAt(0, 1);
		if (newname != null && !oldvalue.equals(newname)) {
			((MethodInvocationElement) element).setArguments(newname);
			oldvalue = newname;
		}
	}

}
