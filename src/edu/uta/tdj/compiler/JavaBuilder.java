package edu.uta.tdj.compiler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.taskdefs.Javac.ImplementationSpecificArgument;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.StringUtils;

import edu.uta.tdj.code.component.ClassElement;
import edu.uta.tdj.code.component.ComplieUnitElement;
import edu.uta.tdj.code.component.MethodElement;
import edu.uta.tdj.code.project.PackageElement;
import edu.uta.tdj.code.project.ProjectElement;
import edu.uta.tdj.controller.ProjectController;
import edu.uta.tdj.ui.ConsolPanel;

//import edu.uta.tdj.code.project.PackageElement;
//import edu.uta.tdj.code.project.ProjectElement;

public class JavaBuilder {


	public JavaBuilder() {
	}

	public static void execute(ProjectElement projectElement) {
		projectElement.save();
		BuilderCreator bc = new BuilderCreator();
		String buildPath = bc.createBuildXML(projectElement);
		System.out.println(buildPath);
		exeBuildFile(buildPath, 3);
		try {
			String mainclassStr = projectElement.getMainclass().getPackage().getName() + "." + projectElement.getMainclass().getName();
			ConsoleSimulator.RunCode(projectElement.getPath()
					+ "/build/classes/ " + mainclassStr);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void exeBuildFile(File buildFile, int level) {
		Project p = new Project();
		// 添加日志输出
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		// 输出信息级别
		consoleLogger.setMessageOutputLevel(level);
		p.addBuildListener(consoleLogger);
		try {
			p.fireBuildStarted();
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			helper.parse(p, buildFile);
			p.executeTarget(p.getDefaultTarget());
			p.fireBuildFinished(null);
		} catch (BuildException e) {
			p.fireBuildFinished(e);
		}
	}

	public static void exeBuildFile(String build, int level) {
		File buildFile = new File(build);
		exeBuildFile(buildFile, level);
	}

}