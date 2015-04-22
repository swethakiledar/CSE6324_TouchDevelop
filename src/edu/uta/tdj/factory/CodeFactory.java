package edu.uta.tdj.factory;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.Type;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.component.FieldElement;
import edu.uta.tdj.code.component.MethodElement;
import edu.uta.tdj.code.component.expression.ExpressionElement;
import edu.uta.tdj.code.component.expression.InfixExpressionElement;
import edu.uta.tdj.code.component.expression.MethodInvocationElement;
import edu.uta.tdj.code.component.statment.IfStatementElement;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class CodeFactory {

	private AST ast;

	public CodeFactory() {
	}

	public void setAST(AST ast) {
		this.ast = ast;
	}

	public CompilationUnit createCompilationUnit() {
		return ast.newCompilationUnit();
	}

	/**
	 * create a new public class
	 * 
	 * @param name
	 *            : the name of the class
	 * @return the new created classelement
	 * */
	public ClassElement createClass(String name) {
		ClassElement ce = this
				.createClass(name, ModifierKeyword.PUBLIC_KEYWORD);
		return ce;
	}

	/**
	 * create a new class
	 * 
	 * @param name
	 *            : the name of the class
	 * @return the new created classelement
	 * */
	public ClassElement createClass(String name, ModifierKeyword modifier) {
		ClassElement ce = new ClassElement(ast);
		ce.setName(name);
		ce.setModifiers(modifier);
		ce.getForm().setElement(ce);
		return ce;
	}

	public ComplieUnitElement createComplieUnitElement(String name) {
		ComplieUnitElement cue = new ComplieUnitElement(ast);
		ClassElement ce = createClass(name);
		cue.addChild(ce);
		cue.setPublicClass(ce);
		return cue;
	}

	/**
	 * create a new ifstatementelement
	 * */

	public IfStatementElement createIfStatementElement() {
		IfStatementElement ifStatementElement = new IfStatementElement(ast);
		return ifStatementElement;
	}

	public ExpressionElement createExpressionElement() {
		ExpressionElement ee = new ExpressionElement(ast);
		return ee;

	}

	/**
	 * create a new InfixExpressionElement
	 * */

	public InfixExpressionElement createInfixExpressionElement() {
		InfixExpressionElement infixExpressionElement = new InfixExpressionElement(
				ast);
		return infixExpressionElement;
	}

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
	public MethodElement createMethodElement(String name,
			ModifierKeyword modifier, Type returnType) {
		MethodElement me = new MethodElement(ast);
		me.setName(name);
		me.createBlock();
		me.setModifiers(modifier);
		me.setReturnType(returnType);
		me.getForm().setElement(me);
		return me;
	}

	public MethodElement createDefaultMethodElement() {
		MethodElement me = createMethodElement("newMethod",
				ModifierKeyword.PUBLIC_KEYWORD,
				ast.newPrimitiveType(PrimitiveType.VOID));
		me.setName("newMethod");
		return me;
	}

	/**
	 * create a main method
	 * 
	 * */
	public MethodElement createMainMethodElement() {
		MethodElement me = createMethodElement("main",
				ModifierKeyword.PUBLIC_KEYWORD,
				ast.newPrimitiveType(PrimitiveType.VOID));
		me.setModifiers(ModifierKeyword.STATIC_KEYWORD);
		me.addParam("args", ast.newArrayType(ast.newSimpleType(ast
				.newSimpleName("String"))));
		me.setName("main");
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
			ModifierKeyword modifiers) {
		FieldElement fe = new FieldElement(ast);
		fe.setName(name);
		fe.setType(type);
		fe.setModifiers(modifiers);
		return fe;
	}

	public FieldElement createFieldElement() {
		FieldElement fe = new FieldElement(ast);
		return fe;
	}

	/**
	 * create a System.out.println();
	 * */

	public MethodInvocationElement createMethodInvocationElement(String value) {
		MethodInvocationElement mie = new MethodInvocationElement(ast);
		mie.setArguments(value);
		return mie;
	}

}
