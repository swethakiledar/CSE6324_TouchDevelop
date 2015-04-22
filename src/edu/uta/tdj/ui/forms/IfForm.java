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
import javax.swing.text.TableView.TableCell;

import org.eclipse.jdt.core.dom.Assignment.Operator;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;

import edu.uta.tdj.code.component.statment.IfStatementElement;
import edu.uta.tdj.factory.ASTTranfer;

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
	InfixExpression ife = null;

	@Override
	public void updateElement() {
		// TODO Auto-generated method stub
		if (comboBox.getSelectedIndex() == 0 && table.getSelectedColumn() == 1
				&& table.getSelectedRow() == 0) {
			String[][] item_value_array = {
					{ "type", "boolean" },
					{
							"boolean",
							((IfStatement) element.getAstNode())
									.getExpression().toString() } };
			super.setTableItem(item_value_array);

		}
		if (comboBox.getSelectedIndex() == 0 && table.getSelectedColumn() == 1
				&& table.getSelectedRow() == 1) {
			((IfStatement) element.getAstNode()).setExpression(element
					.getAstNode().getAST()
					.newSimpleName((String) table.getValueAt(1, 1)));
		}

		if (comboBox.getSelectedIndex() == 1 && table.getSelectedColumn() == 1) {
			if (table.getSelectedRow() == 0) {
				if (((IfStatement) element.getAstNode()).getExpression() instanceof InfixExpression) {

					String[] left = {
							"left",
							((InfixExpression) ((IfStatement) element
									.getAstNode()).getExpression())
									.getLeftOperand().toString() };
					String[] right = {
							"right",
							((InfixExpression) ((IfStatement) element
									.getAstNode()).getExpression())
									.getRightOperand().toString() };
					String[] operator = {
							"operator",
							((InfixExpression) ((IfStatement) element
									.getAstNode()).getExpression())
									.getOperator().toString() };

					String[][] item_value_array = { { "type", "expression" },
							left, operator, right };
					super.setTableItem(item_value_array);
				} else {
					String[] left = { "left", "" };
					String[] right = { "right", "" };
					String[] operator = { "operator", "==" };
					String[][] item_value_array = { { "type", "expression" },
							left, operator, right };
					super.setTableItem(item_value_array);
				}
				table.addComboCell(2, new DefaultCellEditor(cb_Operator));
			}
			if (table.getSelectedRow() == 1) {
				if (((IfStatement) element.getAstNode()).getExpression() instanceof InfixExpression) {
					((InfixExpression) ((IfStatement) element.getAstNode())
							.getExpression()).setLeftOperand(element
							.getAstNode().getAST()
							.newSimpleName((String) table.getValueAt(1, 1)));
				} else {
					if (ife == null) {
						ife = element.getAstNode().getAST()
								.newInfixExpression();
					}
					ife.setLeftOperand(element.getAstNode().getAST()
							.newSimpleName((String) table.getValueAt(1, 1)));
					((IfStatementElement) element).setExpression(ife);
				}
			}

			if (table.getSelectedRow() == 2) {
				if (((IfStatement) element.getAstNode()).getExpression() instanceof InfixExpression) {
					((InfixExpression) ((IfStatement) element.getAstNode())
							.getExpression()).setOperator(ASTTranfer
							.getInfixExpressionOperator((String) table
									.getValueAt(2, 1)));
				}
			}

			if (table.getSelectedRow() == 3) {
				if (((IfStatement) element.getAstNode()).getExpression() instanceof InfixExpression) {
					((InfixExpression) ((IfStatement) element.getAstNode())
							.getExpression()).setRightOperand(element
							.getAstNode().getAST()
							.newSimpleName((String) table.getValueAt(3, 1)));
				} else {
					if (ife == null) {
						ife = element.getAstNode().getAST()
								.newInfixExpression();
					}
					ife.setRightOperand(element.getAstNode().getAST()
							.newSimpleName((String) table.getValueAt(3, 1)));
					((IfStatementElement) element).setExpression(ife);
				}
			}
		}
	}
}
