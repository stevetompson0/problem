package com.steve.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import com.steve.TypeDefinition.TypeDefinition;
import com.steve.problem.Problem;
import static com.steve.problem.SimpleProblem.BODY;
import static com.steve.problem.SimpleProblem.ANSWER;
import com.steve.util.CommandUtils;

/**
 * SimpleProblemBuilder -- Used to generate java source code from parsed problem,
 *     					   also used to compile generated source to bytecode
 * @author steve
 *
 */
public class SimpleProblemBuilder implements Builder{
	// dependency class path
	public static final String CLASS_PATH = "target/classes";
	// path for storage directory
	public static final String PATH_TO_STORE = "/Users/steve/problem/";
	// directory name to store source
	private static final String SOURCE_DIRECTORY_NAME = "source";
	// directory name to store bytecode
	public static final String BYTE_DIRECTORY_NAME = "byte";
	// File of the general storage directory
	private static final File STORE_DIRECTORY = new File(SimpleProblemBuilder.PATH_TO_STORE);
	// File of the source storage directory
	private static final File SOURCE_DIRECTORY = 
			new File(STORE_DIRECTORY, SimpleProblemBuilder.SOURCE_DIRECTORY_NAME);
	
	private PrintWriter output;
	private Problem problem;
	
	public SimpleProblemBuilder(Problem problem) throws FileNotFoundException {
		this.problem = problem;
		String sourceName = problem.getName() + ".java";
		File outputFile = new File(SimpleProblemBuilder.SOURCE_DIRECTORY, sourceName);
		output = new PrintWriter(outputFile);
	}

	/**
	 * buildConstructor -- used to build constructor of the java source
	 */
	@Override
	public void buildConstructor(PrintWriter output) {
		// Variables
		List<String> mVariables = problem.getVariable();
		String name = problem.getName();
		
		for (int i = 0; i < mVariables.size(); i++) {
			String temp = mVariables.get(i).toString();
			int index = temp.indexOf(" ");
			output.println("\tprotected " + TypeDefinition.type(temp.substring(0, index)) + " "
					+ temp.substring(index + 1) + ";");
		}
		
		// Constructor
		output.println("\t//empty constructor");
		output.println("\tpublic " + name + "(){}");
	}
	
	/**
	 * buildHeader -- used to build header of the java source
	 */
	@Override
	public void buildHeader(PrintWriter output) {
		output.print("/* The problem is automatically generated. ");
		output.println("*/");
		
		output.println("import com.steve.RandomPackage.RandomPackage;");
		output.println("import java.io.PrintWriter;");
	}
	
	/**
	 * buildGenerator -- used to build generating function of java source
	 */
	@Override
	public void buildGenerator(PrintWriter output) {
		buildJSONGenerator(output);
	}
	
	/**
	 * buildJSONGenerator -- generator used to output JSON problem instance to
	 * 						 System.out
	 * 
	 * JSON outputs is a JSON object contains 'BODY' and 'ANSWER' part
	 * 
	 * @param output PrintWriter to the source output file
	 */
	private void buildJSONGenerator(PrintWriter output) {
		List<String> mGenerator = problem.getGenerator();
		List<String> mText = problem.getText();
		List<String> mFields = problem.getFields();
		List<String> mAnswerText = problem.getAnswerText();
		List<String> mAnswerFields = problem.getAnswerFields();
		
		// create JSON generator
		output.println("\tpublic void generateJSON() throws Exception");
		output.println("\t{");
		output.println("");
		
		for (int i = 0; i < mGenerator.size(); i++) {
			String v = (String) mGenerator.get(i);
			output.println("\t\t" + v);
		}

		output.println("\t\tPrintWriter output= new PrintWriter(System.out);");
		
		//start to output BODY JSON
		output.println("\t\toutput.print(\"{'" + BODY + "': \\\"\");");

		for (int i = 0; i < mText.size(); i++) {
			output.println("\t\toutput.print(\"" + ((String) mText.get(i)) + "\");");
			if (i < mFields.size()) {
				String variable = (String) mFields.get(i);
				output.println("\t\toutput.print(" + variable + ");");
			}
		}
		
		output.println("\t\toutput.print(\"\\\", \");");
		
		//start to output ANSWER JSON
		output.println("\t\toutput.print(\"'" + ANSWER + "': \\\"\");");
		
		for (int i = 0; i < mAnswerText.size(); i++) {
			output.println("\t\toutput.print(\"" + ((String) mAnswerText.get(i)) + "\");");
			if (i < mAnswerFields.size()) {
				String variable = (String) mAnswerFields.get(i);
				output.println("\t\toutput.print(" + variable + ");");
			}
		}
		
		output.println("\t\toutput.print(\"\\\"}\");");
		

		output.println("\t\toutput.println();");

		output.println("\t\toutput.close();");

		output.println("\t}");	
	}

	@Override
	public void buildMain(PrintWriter output) {
		String classname = problem.getName();
		
		output.println("\tpublic static void main(String[] args) throws Exception");
		output.println("\t{");
		output.println("\t\t " + classname + " v_" + classname + "= new " + classname + "();");
		output.println("\t\t " + "v_" + classname + ".generateJSON();");
		output.println("\t}");
	}

	@Override
	public void generateProblem() {
		String name = problem.getName();
		buildHeader(output);
		
		output.println("public class " + name + " {");
		buildConstructor(output);
		buildGenerator(output);
		buildMain(output);
		
		output.println("}");
		output.close();
		String byteDirectory = SimpleProblemBuilder.PATH_TO_STORE + SimpleProblemBuilder.BYTE_DIRECTORY_NAME;
		String sourceFile = SimpleProblemBuilder.PATH_TO_STORE + SimpleProblemBuilder.SOURCE_DIRECTORY_NAME + "/"
				+ problem.getName() + ".java";
		String command = String.format("javac -cp %s -d %s %s", SimpleProblemBuilder.CLASS_PATH, byteDirectory, sourceFile);
		try {
			int result = CommandUtils.runProcess(command);
			// check whether it succeeds
			if (result != 0) {
				//TODO: log the error
				System.out.println("compile fails");
			}
			else {
				System.out.println("compile succeeds");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
