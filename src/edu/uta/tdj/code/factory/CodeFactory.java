package edu.uta.tdj.code.factory;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.Type;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.ExpressionStatementElement;
import edu.uta.tdj.code.component.FieldElement;
import edu.uta.tdj.code.component.MethodElement;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * 
 * @author Fuqiang Zhang
 */

public class CodeFactory {

	private AST ast;

	private static CodeFactory instanceCodeFactory;

	private CodeFactory() {
	}

	public static CodeFactory getInstance() {
		if (instanceCodeFactory == null) {
			instanceCodeFactory = new CodeFactory();
		}
		return instanceCodeFactory;
	}

	public void setAST(AST ast) {
		this.ast = ast;
	}

	public CompilationUnit createCompilationUnit() {
		return ast.newCompilationUnit();
	}

	/**
	 * create a new class
	 * 
	 * @param name
	 *            : the name of the class
	 * @return the new created classelement
	 * */
	public ClassElement createClass(String name) {
		ClassElement ce = new ClassElement(ast, name);
		return ce;
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
		return me;
	}

	/**
	 * create a new ExpressionStatementElement
	 * 
	 * need to be discussed
	 * 
	 * */
	public ExpressionStatementElement createExpressionStatementElement() {
		ExpressionStatementElement eeElement = new ExpressionStatementElement(
				ast);
		eeElement.setLeft("test");
		eeElement.setOperation(Assignment.Operator.ASSIGN);
		eeElement.setRight("s");
		return eeElement;
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
}
