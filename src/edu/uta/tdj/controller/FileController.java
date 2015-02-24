package edu.uta.tdj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.file.FileSaver;

public class FileController implements ActionListener{
	
	
	FileSaver fSaver = new FileSaver();
	
	public FileController(){
		fSaver.setFilePath("C://tttt.java");
	}
	
	private ClassElement ceClassElement;
	
	public ClassElement getCeClassElement() {
		return ceClassElement;
	}

	public void setCeClassElement(ClassElement ceClassElement) {
		this.ceClassElement = ceClassElement;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ceClassElement = CodeController.getCode();
		fSaver.saveFile(ceClassElement);
		
	}

}
