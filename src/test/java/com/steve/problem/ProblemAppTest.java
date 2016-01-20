package com.steve.problem;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertNotNull;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Unit test for simple App.
 */
public class ProblemAppTest
{
	private JSONObject input;
	
	@Before
    public void setUp(){
        
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
    	input = obj;
    }
	
    @Test
    public void testCreation()
    {
    	// assert that PATH_TO_STORE exists
    	assertNotNull(System.getenv("PATH_TO_STORE"));
    	// expect exit code 1 which means creating succeeds
    	String jsonInput = input.toString();
    	String[] args = new String[4];
    	args[0] = "1";  // set mode to be create-mode
    	args[1] = "id3"; // set problem id to be id3
    	args[2] = "1"; // set problem type to be simpleProblem
    	args[3] = jsonInput; // set jsonInput
    	ProblemAPI.mainCall(args);
    }
    
    @Test
    public void testFetch()
    {
    	// expect exit code 3 which means fetching succeeds
    	String[] args = new String[4];
    	args[0] = "0";  // set mode to be create-mode
    	args[1] = "id3"; // set problem id to be id3
    	ProblemAPI.mainCall(args);
    }
}
