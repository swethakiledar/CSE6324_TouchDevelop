package edu.uta.tdj.factory;


import edu.uta.tdj.code.component.BlockElement;
import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.component.ExpressionStatement;
import edu.uta.tdj.code.component.FieldElement;
import edu.uta.tdj.code.component.IfStatement;
import edu.uta.tdj.code.component.MethodElement;
import edu.uta.tdj.code.component.WhileStatement;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class CodeFactory {


	public CodeFactory() {
	}



	/**
	 * create a new public class
	 * 
	 * @param name
	 *            : the name of the class
	 * @return the new created classelement
	 * */
	public ClassElement createClass(String name) {
		ClassElement ce = this.createClass(name, "public");
		return ce;
	}

	/**
	 * create a new class
	 * 
	 * @param name
	 *            : the name of the class
	 * @return the new created classelement
	 * */
	public ClassElement createClass(String name, String modifier) {
		ClassElement ce = new ClassElement();
		ce.setName(name);
		ce.setModifiers(modifier);
		ce.getForm().setElement(ce);
		return ce;
	}

	public ComplieUnitElement createComplieUnitElement(String name) {
		ComplieUnitElement cue = new ComplieUnitElement();
		ClassElement ce = createClass(name);
		cue.addChild(ce);
		cue.setPublicClass(ce);
		return cue;
	}

	/**
	 * create a new ifstatementelement
	 * */

//	public IfStatementElement createIfStatementElement() {
//		IfStatementElement ifStatementElement = new IfStatementElement(ast);
//		return ifStatementElement;
//	}
//
//	public ForStatementElement createForStatementElement() {
//		ForStatementElement forStatementElement = new ForStatementElement(ast);
//
//		return forStatementElement;
//
//	}
//
//	public ExpressionElement createExpressionElement() {
//		ExpressionElement ee = new ExpressionElement(ast);
//		return ee;
//
//	}

	/**
	 * create a new InfixExpressionElement
	 * */

//	public InfixExpressionElement createInfixExpressionElement() {
//		InfixExpressionElement infixExpressionElement = new InfixExpressionElement(
//				ast);
//		return infixExpressionElement;
//	}

	/**
	 * create a new method
	 * 
	 * @param name
	 *            : the name of the method
	 * @param modifier
	 *            : the modifier
	 * @param returnType
	 *            : the return type of the method
	 * @return the new created method element
	 * */
	public MethodElement createMethodElement(String name, String access,
			String returnType,String modifier) {
		MethodElement me = new MethodElement();
		me.setName(name);
		me.setAccess(access);
		me.setModifiers(modifier);
		me.setReturnType(returnType);
		me.getForm().setElement(me);
		return me;
	}

	public MethodElement createDefaultMethodElement() {
		MethodElement me = createMethodElement("newMethod", "public", "void","");
		me.setName("newMethod");
		return me;
	}

	/**
	 * create a main method
	 * 
	 * */
	public MethodElement createMainMethodElement() {
		MethodElement me = createMethodElement("main", "public", "void","static");
		me.addParam("args", "String[]");
		System.out.println(me.toString());
		return me;
	}

	/**
	 * create a new FieldElement
	 * 
	 * @param name
	 *            : the name
	 * @param type
	 *            : the type
	 * @param modifiers
	 *            : the modify
	 * @return the new created FieldElement
	 * 
	 * */
	public FieldElement createFieldElement(String name, String type,
			String modifiers) {
		FieldElement fe = new FieldElement();
		fe.setName(name);
		fe.setType(type);
		fe.setModifiers(modifiers);
		return fe;
	}

	public FieldElement createFieldElement() {
		FieldElement fe = new FieldElement();
		return fe;
	}


//	public IfStatementElement createIfStatementElement() {
//		IfStatementElement ifStatementElement = new IfStatementElement(ast);
//		return ifStatementElement;
//	}

	public IfStatement createIfStatementElement() {
		IfStatement ifs = new IfStatement();
		return ifs;
	}
	
	public BlockElement createBlockElement(){
		return new BlockElement();
	}
	

	/**
	 * create a System.out.println();
	 * */
	
	
	/**
	 * create an ExpressionStatement
	 * */
	public ExpressionStatement createExpressionStatement(){
		ExpressionStatement es = new ExpressionStatement();
		return es;
	}



	public WhileStatement createWhileStatementElement() {
		// TODO Auto-generated method stub
		return new WhileStatement();
	}
	
	
//	public MethodInvocationElement createMethodInvocationElement(String value) {
//		MethodInvocationElement mie = new MethodInvocationElement(ast);
//		mie.setArguments(value);
//		return mie;
//	}

}
