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

}
