package edu.uta.tdj.code.component;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * @author Fuqiang Zhang
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import edu.uta.tdj.code.proposal.ProposalButtonFactory;
import edu.uta.tdj.ui.forms.ClassForm;

/**
 * For a class
 * 
 * */

public class ClassElement extends Element {

	private ArrayList<FieldElement> fieldList;
	private ArrayList<MethodElement> methodList;

	private String modifiedString = "";

	public ClassElement(AST ast, String name) {
		super(ast);
		this.astNode = ast.newTypeDeclaration();
		setName(name);

		fieldList = new ArrayList<FieldElement>();
		methodList = new ArrayList<MethodElement>();

		this.setX(50);
		this.setY(50);
		this.form = new ClassForm();
		this.height = 20;
	}

	public void setName(String name) {
		this.name = name;
		this.width = name.length() * 5;
		((TypeDeclaration) astNode).setName(ast.newSimpleName(name));
	}

	public void addContructor(ModifierKeyword modifiers) {
		MethodElement me = new MethodElement(ast);
		me.setParent(this);
		me.setName(this.getName());
		me.createBlock();
		me.setModifiers(modifiers);
		((TypeDeclaration) astNode).bodyDeclarations().add(me.getAstNode());
	}

	public void addMethod(MethodElement me) {
		me.setParent(this);
		me.setX(x + 20);
		me.setY(y + height - 20);
		((TypeDeclaration) astNode).bodyDeclarations().add(me.getAstNode());
		methodList.add(me);
	}

	public void addMethod(String name, Type returnType,
			ModifierKeyword modifiers) {
		MethodElement me = new MethodElement(ast);
		me.setName(name);
		me.createBlock();
		me.setModifiers(modifiers);
		me.setReturnType(ast.newPrimitiveType(PrimitiveType.VOID));
		this.addMethod(me);
	}

	/**
	 * add field
	 * 
	 * 
	 * */
	public void addField(FieldElement fe) {
		fe.setParent(this);
		fe.setX(x + 20);
		fe.setY(y + (fieldList.size() + 1) * 20);
		((TypeDeclaration) astNode).bodyDeclarations().add(fe.getAstNode());
		fieldList.add(fe);
	}

	public void addField(String name, String type, ModifierKeyword modifiers) {
		FieldElement fe = new FieldElement(ast);
		fe.setName(name);
		fe.setType(type);
		fe.setModifiers(modifiers);
		addField(fe);
	}

	public void removeChild(Element element) {
		fieldList.remove(element);
		methodList.remove(element);
		element.getAstNode().delete();
	}

	/**
	 * should be changed and split into parts
	 * */
	@Override
	public void setModifiers(ModifierKeyword modifiers) {
		((TypeDeclaration) astNode).modifiers().add(ast.newModifier(modifiers));
		modifiedString = modifiedString + " " + modifiers.toString();
	}

	@Override
	public List<JButton> getButtons(ProposalButtonFactory pcComputer) {
		return pcComputer.getButtons(this);
	}

	@Override
	public ASTNode getAstNode() {
		// TODO Auto-generated method stub
		return astNode;
	}

	public void computeHeight() {
		height = 50;
		int i = 1;
		for (FieldElement ef : fieldList) {
			height = height + ef.getHeight();
			ef.setY(i * 20 + y);
			i++;
		}
		int lastHeight = i * 20;

		for (MethodElement me : methodList) {
			height = height + me.getHeight();
			me.setY(lastHeight + y);
			me.notifyObservers();
			lastHeight = me.getHeight() + lastHeight;
			i++;
		}
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		computeHeight();
		g.setColor(Color.black);
		g.drawString(this.toString(), x, y + 20);

		for (FieldElement ef : fieldList) {
			ef.draw(g);
		}

		for (MethodElement me : methodList) {
			me.draw(g);
		}
		g.drawString("}", x, this.height + y - 10);

	}

	public String toString() {
		return this.modifiedString + "  class  " + name + "  {";
	}

	@Override
	public Element getSelectedElement(int x_in, int y_in) {
		// TODO Auto-generated method stub
		if (this.isInelement(x_in, y_in)) {
			return this;
		} else {
			for (FieldElement fe : fieldList) {
				Element element = fe.getSelectedElement(x_in, y_in);
				if (element != null)
					return element;
			}

			for (MethodElement me : methodList) {
				Element element = me.getSelectedElement(x_in, y_in);
				if (element != null)
					return element;
			}
		}
		return null;
	}

	@Override
	public void unSelected() {
		this.setSelected(false);
		for (FieldElement fe : fieldList) {
			fe.unSelected();
		}

		for (MethodElement me : methodList) {
			me.unSelected();
		}
	}

	@Override
	public void addChild(Element element) {
		if (element instanceof MethodElement) {
			this.addMethod((MethodElement) element);
			return;
		}
		if (element instanceof FieldElement) {
			this.addField((FieldElement) element);
			return;
		}
	}
}
