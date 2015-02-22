package edu.utd.tdj.code.proposal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import edu.uta.tdj.code.component.*;

/**
 * 2015 2015Äê2ÔÂ22ÈÕ
 * @author Fuqiang Zhang
 */

public class ProposalComputer {

	private ComputedElement computedElement;
	private CompilationUnit cu;

	public void setComputedElement(ComputedElement cElement) {
		this.computedElement = cElement;
	}

	public void setCompilationUnit(CompilationUnit cu) {
		this.cu = cu;
	}
	
	public List getProposal(Element element){
		if(element instanceof ExpressionStatementElement){
			return getProposal((ExpressionStatement)((ExpressionStatementElement)element).getAstNode());
		}
		if(element instanceof MethodElement){
			return getProposal((MethodDeclaration)((MethodElement)element).getAstNode());
		}
		return null;
	}
	
	
	
	
	
	
	/**
	 * proposal for the statement
	 * 
	 * instance: user want to input an expression statement (p.display()) first
	 * input a 'p' the return all the methods of p's class
	 * */
	public List getProposal(ExpressionStatement s) {

		System.out.println("Searching for the proposal...");
		List list = new ArrayList();
		List<TypeDeclaration> types = cu.types();
		
		String leftString = (((Assignment) s.getExpression()).getLeftHandSide())
				.toString();
		// type declaration is the defined classes in the CompilationUnit
		
		// here is not correct 
		for (TypeDeclaration typeDec : types) {
			// find the 'p' declaration
			for (FieldDeclaration fd : typeDec.getFields()) {
				// find the p
				if (((VariableDeclarationFragment) fd.fragments().get(0))
						.getName().toString().equals(leftString)) {
					for (TypeDeclaration td : types) {
						// find p's type
						if (td.getName().toString()
								.equals(fd.getType().toString())) {
							System.out.println("get it");
							// find all the methods of the p's type
							MethodDeclaration[] md = td.getMethods();
							list.addAll(Arrays.asList(md));
						}
					}
				}
			}
		}

		return list;
	}

	/**
	 * proposal for the method (cursor in the method)
	 * 
	 * insert statements of while,
	 * 						for,
	 * 						if,
	 * 						do...
	 * 
	 *  					params
	 *  					fields
	 *  					methods
	 * 
	 * */
	public List getProposal(MethodDeclaration md) {
		List list = new ArrayList();
		
		//params
		List parList = md.parameters();
		list.addAll(parList);
		//fields
		TypeDeclaration tDeclaration = (TypeDeclaration) md.getParent();

		List fieldList = Arrays.asList(tDeclaration.getFields());
		list.addAll(fieldList);
		//methods
		List methodList= Arrays.asList(tDeclaration.getMethods());
		list.addAll(methodList);
		//other statements
		
		List structList = new ArrayList();
		structList.add(cu.getAST().newWhileStatement());
		structList.add(cu.getAST().newIfStatement());
		structList.add(cu.getAST().newForStatement());
		structList.add(cu.getAST().newDoStatement());
		
		list.addAll(structList);
		
		return list;
	}

}
