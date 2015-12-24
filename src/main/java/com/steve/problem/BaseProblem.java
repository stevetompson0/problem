package com.steve.problem;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.steve.TypeDefinition.TypeDefinition;

/**
 * BaseProblem -- this class is used to as a base class for other kinds of
 * problem ; store an abstract problem and its source
 * 
 * It accepts a json format question and use a specific parser according to the
 * problem type to parse the problem. Then it use parsed data to generate a
 * problem program which could be used to generate a problem instance.
 * 
 * type: 1 simple blank filling problem 2 multiple choice problem
 * 
 * @author steve
 *
 */
public abstract class BaseProblem implements Problem {
	// type of the problem
	private int type; 	
	// json data that is passed in
	private String json_data; 	
	// hashed id for this problem
	private String name; 

	// variable declaration code
	private List<String> mVariables = new ArrayList<String>(); 
	
	// variable processing code
	private List<String> mGenerator = new ArrayList<String>(); 
	
	// variables in body
	private List<String> mFields = new ArrayList<String>(); 
	
	// text in body
	private List<String> mText = new ArrayList<String>(); 
	
	// variables in answer
	private List<String> mAnswerFields = new ArrayList<String>(); 
	
	// text in answer
	private List<String> mAnswerText = new ArrayList<String>(); 

	public BaseProblem(int type, String json_data, String name) {
		this.type = type;
		this.json_data = json_data;
		this.name = name;
	}

	// set variable List
	@Override
	public void setVariable(List<String> variableList) {
		this.mVariables = variableList;
	}

	// set generator List
	@Override
	public void setGenerator(List<String> generatorList) {
		this.mGenerator = generatorList;
	}

	// set body field list
	@Override
	public void setFields(List<String> fieldList) {
		this.mFields = fieldList;
	}

	// set body text List
	@Override
	public void setText(List<String> textList) {
		this.mText = textList;
	}

	// set answer field list
	@Override
	public void setAnswerFields(List<String> answerFieldList) {
		this.mAnswerFields = answerFieldList;
	}

	// set answer text list
	@Override
	public void setAnswerText(List<String> answerTextList) {
		this.mAnswerText = answerTextList;
	}

	// get original json data
	@Override
	public String getJson() {
		return json_data;
	}

	// get problem name
	@Override
	public String getName() {
		return this.name;
	}

	// get variable List
	@Override
	public List<String> getVariable() {
		return this.mVariables;
	}

	// get generator List
	@Override
	public List<String> getGenerator() {
		return this.mGenerator;
	}

	// get body field list
	@Override
	public List<String> getFields() {
		return this.mFields;
	}

	// get body text List
	@Override
	public List<String> getText() {
		return this.mText;
	}

	// get answer field list
	@Override
	public List<String> getAnswerFields() {
		return this.mAnswerFields;
	}

	// get answer text list
	@Override
	public List<String> getAnswerText() {
		return this.mAnswerText;
	}
	

	public void parse(File problem) throws Exception {

		if (!problem.exists()) {
			System.out.println("Error - This problem's txt file does not exist.");
			System.exit(0);
		}

		Scanner txt = new Scanner(problem);
		String mode = "";
		String buf = "";
		boolean reading_problem_text = true;
		String current_text = "";
		while (txt.hasNextLine()) {
			buf = txt.nextLine();
			if (buf.length() == 0)// empty line;
			{
				continue;
			}
			if (buf.charAt(0) == '_') {

				mode = buf;
				continue;
			} else if (mode.equals("_VARIABLE_")) {
				mVariables.add(buf);
			} else if (mode.equals("_GENERATOR_")) {
				mGenerator.add(buf);
			} else if (mode.equals("_PROBLEM_")) {
				int counter = 0;

				for (int i = 0; i < buf.length(); i++) {
					if (buf.charAt(i) == '$' && (i == 0 || buf.charAt(i - 1) != '\\')) {
						counter = (counter + 1) % 2;
						if (counter == 1) {
							reading_problem_text = true;
						} else {
							reading_problem_text = false;
						}

						if (reading_problem_text) {
							mText.add(current_text);
						} else {
							mFields.add(current_text);
						}
						current_text = "";
						continue;
					} else if ((buf.charAt(i) == '\\') && (i == 0 || buf.charAt(i - 1) != '\\')) {
						continue;
					}
					current_text += buf.charAt(i);
				}
			}

			if (mode.equals("_PROBLEM_")) {
				if (reading_problem_text) {
					mFields.add(current_text);
				} else {
					mText.add(current_text);
				}
			}
		}
		int temp1 = problem.getParent().lastIndexOf("\\");
		String tableName = problem.getParent().substring(temp1 + 1);
		String problemName = problem.getName().substring(0, problem.getName().length() - 4);

	}

	public void write(String name) throws Exception {
		generateProblem(name);
	}

	void buildConstructor(PrintWriter output, String filename) { // Level
																	// output.println("\t//Difficulty
																	// level");
																	// output.println("\tprotected
																	// int level
																	// = " +
																	// mLevel +
																	// ";");

		// Coverage
		/*
		 * output.println("\t//knowledge covered"); output.print(
		 * "\tprotected String[] coverage = "); StringBuilder coverage = new
		 * StringBuilder(); coverage.append("{"); for (int
		 * i=0;i<mTopicsCovered.size();i++){
		 * coverage.append("\""+mTopicsCovered.get(i)+"\","); } int num =
		 * coverage.length(); coverage.setCharAt(num-1, '}');
		 * output.println(coverage+";");
		 */
		// Variables
		for (int i = 0; i < mVariables.size(); i++) {
			String temp = mVariables.get(i).toString();
			int index = temp.indexOf(" ");
			output.println("\tprotected " + TypeDefinition.type(temp.substring(0, index)) + " "
					+ temp.substring(index + 1) + ";");
		}

		// Constructor
		output.println("\t//empty constructor");
		output.println("\tpublic " + filename + "(){}");

	}

	void buildHeader(PrintWriter output) {
		output.print("/* The problem is automatically generated. ");

		int counter = 0;

		output.println("*/");
		output.println("import RandomPackage.RandomPackage;");
		output.println("import TypeDefinition.*;");
		output.println("import java.io.PrintWriter;");
		output.println("import java.io.File;");
		output.println("import GraphvizAPI.GraphViz;");
		output.println("import TreeDrawer.*;");
		output.println("import RandomGraph.*;");

	}

	void buildGenerator(PrintWriter output, String name) {
		output.println("\tpublic void generate() throws Exception");
		output.println("\t{");
		output.println("");
		for (int i = 0; i < mGenerator.size(); i++) {
			String v = (String) mGenerator.get(i);
			output.println("\t\t" + v);
		}

		output.println("\t\tFile txt= new File(\"" + name + "\");");
		output.println("\t\tPrintWriter output= new PrintWriter(txt);");

		for (int i = 0; i < mText.size(); i++) {
			String temp = mText.get(i).toString();
			temp = temp.replaceAll("��", "\\\\\\\\texttt{`}");
			temp = temp.replaceAll("��", "\\\\\\\\texttt{��}");
			temp = temp.replaceAll("��", "\\\\\\\\textquotedblleft ");
			temp = temp.replaceAll("��", "\\\\\\\\textquotedblright ");
			temp = temp.replaceAll("\"", "\\\\\\\\textquotedblright ");
			mText.set(i, temp.replaceAll("��", "'"));
		}

		for (int i = 0; i < mText.size(); i++) {
			output.println("\t\toutput.print(\"" + ((String) mText.get(i)) + "\");");
			if (i < mFields.size()) {
				String variable = (String) mFields.get(i);
				output.println("\t\toutput.print(" + variable + ");");
			}
		}

		if (mText.size() == 0) {
			for (int i = 0; i < mFields.size(); i++) {
				String temp = mFields.get(i).toString();
				temp = temp.replaceAll("��", "\\\\\\\\texttt{`}");
				temp = temp.replaceAll("��", "\\\\\\\\texttt{��}");
				temp = temp.replaceAll("��", "\\\\\\\\textquotedblleft ");
				temp = temp.replaceAll("��", "\\\\\\\\textquotedblright ");
				temp = temp.replaceAll("\"", "\\\\\\\\textquotedblright ");

				mFields.set(i, temp.replaceAll("��", "'"));
			}
			output.println("\t\toutput.print(\"" + mFields.get(0).toString() + "\");");
		}

		output.println("\t\toutput.println(\"\");");

		output.println("\toutput.close();");

		output.println("\t}");
	}

	void buildMain(PrintWriter output, String classname) {
		output.println("\tpublic static void main(String[] args) throws Exception");
		output.println("\t{");
		output.println("\t\t " + classname + " v_" + classname + "= new " + classname + "();");
		output.println("\t\t " + "v_" + classname + ".generate();");
		output.println("\t}");
	}

	void generateProblem(String name) throws Exception {
		File java = new File("javaProblem/" + name + ".java");
		if (java.exists()) {
			System.out.println("Error - This problem's java file exists.");
			System.exit(0);
		}

		// create the folder if needed
		int index = name.lastIndexOf("/");
		String folderpath = name.substring(0, index);
		String filename = name.substring(index + 1);
		File folder = new File("javaProblem/" + folderpath);

		if (folder.exists() == false) {
			folder.mkdirs();
		}
		// String absolutePath=folder.getAbsolutePath();
		// String fullPath=absolutePath+"\\"+filename+".txt";
		// fullPath=fullPath.replaceAll("\\\\", "\\\\\\\\");

		String fullPath = "C:/Users/Stevetompson0/Desktop/ProblemGenerator/temp/" + filename + ".txt";

		PrintWriter output = new PrintWriter(java);

		// read in the txt file
		buildHeader(output);
		output.println("public class " + filename + " {");
		// build constructors, and other methods in a similar way
		buildConstructor(output, filename);
		buildGenerator(output, fullPath);
		buildMain(output, filename);
		output.print("}");
		output.close();

	}
}
