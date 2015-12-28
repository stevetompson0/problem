package com.steve.problem;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * Hello world!
 *
 */
public class ProblemApp 
{
    public static void main( String[] args )
    {
    	JSONObject obj = new JSONObject();
    	// put variable json list
    	JSONArray variableList = new JSONArray();
    	variableList.add("integer a1");
    	variableList.add("integer a2");
    	variableList.add("integer a3");
    	obj.put("VARIABLE", variableList);
    	
    	JSONArray generatorList = new JSONArray();
    	generatorList.add("a1 = RandomPackage.RandomNum(1, 12);");
    	generatorList.add("a2 = RandomPackage.RandomNum(1, 12);");
    	generatorList.add("a3 = a1 * a2;");
    	obj.put("GENERATOR", generatorList);
    	
    	obj.put("BODY", "What is the result of $a1$ * $a2$?");
    	
    	obj.put("ANSWER", "The result is $a3$.");
    	obj.put("TYPE", 1);
    	
    	
    	System.out.println(obj.toString());
    	
    	JSONParser jsonParser = new JSONParser();
    	
    	Problem problem;
    	
    	try {
			Object input = jsonParser.parse(new BufferedReader(new StringReader(obj.toString())));
			JSONObject jsonInput = (JSONObject) input;
			JSONArray jsonArray = (JSONArray) jsonInput.get("GENERATOR");
			for (Object item: jsonArray) {
				System.out.println(item);
			}
			
			if ((long) jsonInput.get("TYPE") == 1) {
				problem = new SimpleProblem(1, obj.toString(), "id2");
				problem.parse();
				List<String> variables = problem.getAnswerText();
				for (String item: variables) {
					System.out.println(item);
				}
				problem.build();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
