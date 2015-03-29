package edu.uta.tdj.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyController {
	
	private static Properties prop = null;
	public static Properties getProperties(){
		if(null==prop){
			 prop = new Properties();
				try {
					prop.load(new FileInputStream(new File("build/config.properties")));
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		return prop;
	}
	
	public static void save(){
		try {
			prop.store(new FileOutputStream(new File("build/config.properties")), "");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
