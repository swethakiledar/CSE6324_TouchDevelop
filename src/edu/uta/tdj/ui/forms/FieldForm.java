package edu.uta.tdj.ui.forms;

import java.util.List;

import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import edu.uta.tdj.code.component.FieldElement;

public class FieldForm extends Form {

	public FieldForm() {
		super();
		String[][] item_value_array = { { "Field Name", oldname }, { "Type", "" },
				{ "Access", "" } };
		super.setTableItem(item_value_array);
	}

	@Override
	public void setTableValue() {
		String name = element.getName();
		String type = ((FieldElement) element).getType().toString();
		List list = ((FieldElement) element).getModifierKeyword();
		this.table.getModel().setValueAt(name, 0, 1);
		this.table.getModel().setValueAt(type, 1, 1);
		// this.table.getModel().setValueAt((ModifierKeyword) list.get(0), 2,
		// 1);
	}

	String oldname = "";
	String oldType = null;
	String oldAccess = null;

	@Override
	public void updateElement() {
		String newname = (String) this.table.getValueAt(0, 1);
		if (newname !=null &&!oldname.equals(newname)) {
			element.setName(newname);
			oldname = newname;
		}
	}

}
