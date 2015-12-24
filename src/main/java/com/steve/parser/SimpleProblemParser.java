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

/**
 * SimpleProblemParser -- used to parse simple problem; can be extended to
 * 						  parse complicated problem
 * @author steve
 *
 */
public class SimpleProblemParser implements Parser{
	// These constants are keys in JSON from problem
	private static final String VARIABLE = "VARIABLE";
	private static final String GENERATOR = "GENERATOR";
	private static final String BODY = "BODY";
	private static final String ANSWER = "ANSWER";
	
	// problem -- Problem instance for this parser
	private Problem problem;
	
	// jsonParser -- JSONParser instance used to parse json from problem
	private JSONParser jsonParser = new JSONParser();
	
	// jsonObj -- JSONObject returned from jsonParser
	private JSONObject jsonObj;
	
	public SimpleProblemParser(Problem problem) throws IOException, ParseException {
		this.problem = problem;
		this.jsonObj = (JSONObject) jsonParser.parse(new BufferedReader(new StringReader(this.problem.getJson())));
	}

	/**
	 * parseVariable -- use JSONParser to parse JSONArray to ArrayList and 
	 * 					set variableList for the problem
	 */
	@Override
	public void parseVariable() {
		this.problem.setVariable(jsonArrayConvertToList(SimpleProblemParser.VARIABLE));
	}

	@Override
	public void parseGenerator() {
		this.problem.setGenerator(jsonArrayConvertToList(SimpleProblemParser.GENERATOR));
	}

	@Override
	public void parseBody() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parseAnswer() {
		// TODO Auto-generated method stub
		
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
	
	
}
