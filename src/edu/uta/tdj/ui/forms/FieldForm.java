package edu.uta.tdj.ui.forms;

public class FieldForm extends Form {
	
	
	public FieldForm() {
		super();
		String[][] item_value_array = { { "Field Name", "" } ,{"Type",""},{"Access",""}};
		super.setTableItem(item_value_array);
	}

	
	@Override
	public void setTableValue() {
		
	}


	@Override
	public void updateElement() {
		
	}

}
