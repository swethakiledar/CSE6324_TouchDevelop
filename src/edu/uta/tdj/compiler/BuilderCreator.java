package edu.uta.tdj.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import edu.uta.tdj.code.project.ProjectElement;

public class BuilderCreator {

	public String createBuildXML(ProjectElement pe) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db = null;
		Document doc = null;
		InputStream is = null;
		String buildPath = "";
		try {
			db = dbf.newDocumentBuilder();
			is = new FileInputStream(new File("build/build.xml"));
			doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("project");
			Element node = (Element) nodes.item(0);
			node.setAttribute("name",pe.getName());
			node.setAttribute("basedir", pe.getPath());
			
			Element mainclass = (Element) node.getElementsByTagName("property").item(0);
			mainclass.setAttribute("value", pe.getMainclass());
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			buildPath = pe.getPath() + "/build.xml";
			StreamResult result = new StreamResult(buildPath);
			transformer.transform(source, result);
			return buildPath;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "build/build.xml";
	}
	
//	<?xml version="1.0" encoding="UTF-8" ?>
//	<project name="testssszzzdddd" default="run" basedir="g:/testWorkspace/testssszzzdddd/">
//		<property name="src" value="src" />
//		<property name="dest" value="bin" />
//		<property name="hello_jar" value="hello1.jar" />
//		<target name="init">
//			<mkdir dir="${dest}" />
//		</target>
//		<target name="compile" depends="init">
//			<javac srcdir="${src}" destdir="${dest}" />
//		</target>
//		<target name="build" depends="compile">
//			<jar jarfile="${hello_jar}" basedir="${dest}" />
//		</target>
//		<target name="run" depends="build">
//			<java classname="test.ant.HelloWorld" classpath="${hello_jar}" />
//		</target>
//		<target name="clean">
//			<delete dir="${dest}" />
//			<delete file="${hello_jar}" />
//		</target>
//		<target name="rerun" depends="clean,run">
//			<ant target="clean" />
//			<ant target="run" />
//		</target>
//	</project>

}
