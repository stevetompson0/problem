package com.steve.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.steve.problem.Problem;
import com.steve.problem.SimpleProblem;

/**
 * SimpleProblemParser -- used to parse simple problem; can be extended to
 * 						  parse complicated problem
 * @author steve
 *
 */
public class SimpleProblemParser implements Parser{
	
	// problem -- Problem instance for this parser
	private Problem problem;
	
	// jsonParser -- JSONParser instance used to parse json from problem
	private JSONParser jsonParser = new JSONParser();
	
	// jsonObj -- JSONObject returned from jsonParser
	private JSONObject jsonObj;
	
	public SimpleProblemParser(Problem problem) throws ParseException, IOException {
	    
		this.problem = problem;
		this.jsonObj = (JSONObject) jsonParser.parse(new BufferedReader(new StringReader(this.problem.getJson())));
	}

	/**
	 * parseVariable -- use JSONParser to parse JSONArray to ArrayList and 
	 * 					set variableList for the problem
	 */
	@Override
	public void parseVariable() {
		this.problem.setVariable(jsonArrayConvertToList(SimpleProblem.VARIABLE));
	}

	@Override
	public void parseGenerator() {
		this.problem.setGenerator(jsonArrayConvertToList(SimpleProblem.GENERATOR));
	}

	@Override
	public void parseBody() {
		String buf = (String) jsonObj.get(SimpleProblem.BODY);
		List<String> fields = new ArrayList<String>();
		List<String> text = new ArrayList<String>();
		
		parserTextAndFields(buf, text, fields);
		
		this.problem.setText(text);
		this.problem.setFields(fields);
	}

	@Override
	public void parseAnswer() {
		String buf = (String) jsonObj.get(SimpleProblem.ANSWER);
		List<String> fields = new ArrayList<String>();
		List<String> text = new ArrayList<String>();
		
		parserTextAndFields(buf, text, fields);
		
		this.problem.setAnswerText(text);
		this.problem.setAnswerFields(fields);
		
	}
	
	@Override
	public void parseProblem() {
		parseVariable();
		parseGenerator();
		parseBody();
		parseAnswer();
	}
	
	/** jsonArrayConvertToList -- used to convert JSON array to ArrayList
	 * 
	 * @param key: key for the JSONArray in the jsonObj
	 * @return ArrayList converted from JSONArray
	 */
	private List<String> jsonArrayConvertToList(String key) {
		List<String> list = new ArrayList<String>();
		JSONArray array = (JSONArray) jsonObj.get(key);
		for (Object line: array) {
			list.add(line.toString());
		}
		return list;
	}
	
	/**
	 * parserTextAndFields -- helper function used to parser body and answer
	 * 						  to List
	 * @param buf: string which stores origin text and fields
	 * @param text: List to store text
	 * @param field: List to store fields
	 */
	private void parserTextAndFields(String buf, List<String> text, List<String> fields) {
		// index of reading character
		int counter = 0;

		// boolean indicating whether we are reading fields or texts
		boolean reading_problem_text = true;

		// store current text buffer
		String current_text = "";

		for (int i = 0; i < buf.length(); i++) {
			if (buf.charAt(i) == '$' && (i == 0 || buf.charAt(i - 1) != '\\')) {
				counter = (counter + 1) % 2;
				if (counter == 1) {
					reading_problem_text = true;
				} else {
					reading_problem_text = false;
				}

				if (reading_problem_text) {
					text.add(current_text);
				} else {
					fields.add(current_text);
				}
				current_text = "";
				continue;
			} else if ((buf.charAt(i) == '\\') && (i == 0 || buf.charAt(i - 1) != '\\')) {
				continue;
			}
			current_text += buf.charAt(i);
		}

		if (!reading_problem_text) {
			fields.add(current_text);
		} else {
			text.add(current_text);
		}
	}

	
}
