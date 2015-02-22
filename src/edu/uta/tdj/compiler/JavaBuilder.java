package edu.uta.tdj.compiler;
import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.taskdefs.Javac.ImplementationSpecificArgument;
import org.apache.tools.ant.types.Path;

public class JavaBuilder {
	
	public static void main(String[] args){
		JavaBuilder jb = new JavaBuilder();
		jb.execute(new Path(null, ""), new File("c://test"));
	}
	
    public JavaBuilder() {
    }
    /**
     * @param path: the ClassPath
     * @param output: the destDir
     * */
    public void execute(Path classPath, File output) {
        Project currentProject = new Project();
        //the absolute dir of the project
        currentProject.setBaseDir(new File("G:/UTA_Courses/Java/test2"));

        Javac compileJava = new Javac();
        compileJava.setProject(currentProject);
        // call the JDT compiler
        compileJava.setCompiler("org.eclipse.jdt.core.JDTCompilerAdapter");
        compileJava.setClasspath(classPath);
        compileJava.setEncoding("UTF-8");

        // relative path of src
        compileJava.setSrcdir(new Path(currentProject, "src"));
        // destination path of the .class files ()
        compileJava.setDestdir(output);
        compileJava.setTarget("1.6");
        compileJava.setSource("1.6");
        compileJava.setNowarn(true);
        compileJava.setDebug(true);
        compileJava.setDebugLevel("lines,source");

        ImplementationSpecificArgument arg = compileJava.createCompilerArg();
        arg.setLine(" -Xlint");

        compileJava.execute();

    }
}