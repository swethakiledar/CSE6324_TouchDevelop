package edu.uta.tdj.ui.forms;

import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import edu.uta.tdj.code.component.FieldElement;
import edu.uta.tdj.code.proposal.ConstantType;

public class FieldForm extends Form {

	JComboBox cb_Access = new JComboBox();
	JComboBox cb_Static = new JComboBox();
	JComboBox cb_Final = new JComboBox();
	JComboBox cb_Type = new JComboBox();
	
	
	public FieldForm() {
		super();
		String[][] item_value_array = { { "Field Name", oldname },
				{ "Type", oldType }, { "Access", oldAccess },
				{ "static", "false" }, { "final", "false" } };
		super.setTableItem(item_value_array);
		
		cb_Type.addItem("");
		for(String type: ConstantType.getTypeArrayList()){
			cb_Type.addItem(type);
		}
		
		cb_Access.addItem("");
		cb_Access.addItem("public");
		cb_Access.addItem("private");
		cb_Access.addItem("protected");
		
		cb_Static.addItem("true");
		cb_Static.addItem("false");

		cb_Final.addItem("true");
		cb_Final.addItem("false");

		table.addComboCell(2, new DefaultCellEditor(cb_Access));
		table.addComboCell(3, new DefaultCellEditor(cb_Static));
		table.addComboCell(4, new DefaultCellEditor(cb_Final));
		table.addComboCell(1, new DefaultCellEditor(cb_Type));
		
	}

	@Override
	public void setTableValue() {
		String name = element.getName();
		String type = ((FieldElement) element).getType().toString();
		this.table.getModel().setValueAt(name, 0, 1);
		this.table.getModel().setValueAt(type, 1, 1);
		String access = ((FieldElement) element).getAccess();
		this.table.getModel().setValueAt(access, 2, 1);
	}

	String oldname = "";
	String oldType = "";
	String oldAccess = "";

	@Override
	public void updateElement() {
		String newname = (String) this.table.getValueAt(0, 1);
		String newType = (String) this.table.getValueAt(1, 1);
		String newAccess = (String) this.table.getValueAt(2, 1);
		
		Boolean staticB = Boolean.valueOf( (String) this.table.getValueAt(3, 1));
		Boolean finalB = Boolean.valueOf( (String) this.table.getValueAt(4, 1));
		String staticString = "";
		String finalString = "";
		if(staticB){
			staticString = " static ";
		}else{
			staticString = "";
		}
		
		if(finalB){
			finalString = " final ";
		}else{
			finalString="";
		}
		
		String modifiers = staticString+finalString;
		((FieldElement) element).setModifiers(modifiers);
		
		if (newname != null && !oldname.equals(newname)) {
			element.setName(newname);
			oldname = newname;
		}

		if (newType != null && !oldType.equals(newType)) {
			((FieldElement) element).setType(newType);
			oldType = newType;
		}

		if (newAccess != null && !oldAccess.equals(newAccess)) {
			((FieldElement) element).setAccess(newAccess);
			oldAccess = newAccess;
		}
	}

}
