package edu.uta.tdj.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.uta.tdj.code.project.PackageElement;
import edu.uta.tdj.code.project.ProjectElement;

/**
 * <project> <name>Test</name> <path>G:\testWorkspace/Test</path> <packages>
 * <package> <name>testS</name> <path>G:\testWorkspace/Test/src/testS</path>
 * </package> </packages> </project>
 */

public class ProjectFactory {


	public static ProjectElement getProject(File file) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db = null;
		Document doc = null;
		InputStream is = null;

		ProjectElement pElement = null;

		String projectName = "";
		String projectPath = "";
		try {
			db = dbf.newDocumentBuilder();
			is = new FileInputStream(file);
			doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("project");
			if (nodes == null) {
				throw new Exception("null nodes with tagName project");
			}
			Element project = (Element) nodes.item(0);
			Element name = (Element) project.getElementsByTagName("name").item(
					0);
			Element path = (Element) project.getElementsByTagName("path").item(
					0);
			projectName = name.getTextContent();
			projectPath = path.getTextContent();

			pElement = new ProjectElement(projectPath);

			Element packagesElement = (Element) project.getElementsByTagName(
					"packages").item(0);
			NodeList packagesList = packagesElement
					.getElementsByTagName("package");
			for (int i = 0; i < packagesList.getLength(); i++) {
				Element packageElement = (Element) packagesList.item(i);
				Element packagenameElement = (Element) packageElement
						.getElementsByTagName("name").item(0);
				Element packagepathElement = (Element) packageElement
						.getElementsByTagName("path").item(0);
				String packageName = packagenameElement.getTextContent();
				String packagePath = packagepathElement.getTextContent();
				System.out.println(packageName);
				PackageElement pe = new PackageElement(pElement, packageName);
				pElement.addPackage(pe);
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pElement;
	}
}
