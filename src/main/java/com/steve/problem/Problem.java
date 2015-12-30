package com.steve.problem;

import java.util.List;

/**
 * Problem -- interface for problem
 * 
 * @author steve
 *
 */

public interface Problem {
	
	// get the json data for this problem
	public String getJson();
	
	// use parser to parse data
	public void parse();
	
	// use builder to build a java program
	// return 0 means succeeds, 1 means error
	public int build();

	// set variable List
	public void setVariable(List<String> variableList);

	// set generator List
	public void setGenerator(List<String> generatorList);

	// set body field list
	public void setFields(List<String> fieldList);

	// set body text List
	public void setText(List<String> textList);

	// set answer field list
	public void setAnswerFields(List<String> answerFieldList);

	// set answer text list
	public void setAnswerText(List<String> answerTextList);

	// get problem name
	public String getName();
	
	// get variable List
	public List<String> getVariable();
	
	// get generator List
	public List<String> getGenerator();
	
	// get body field list
	public List<String> getFields();
	
	// get body text List
	public List<String> getText();
	
	// get answer field list
	public List<String> getAnswerFields();
	
	// get answer text list
	public List<String> getAnswerText();
	
}
