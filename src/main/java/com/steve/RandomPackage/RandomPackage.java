package com.steve.RandomPackage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.steve.builder.SimpleProblemBuilder;
import com.steve.problem.ProblemAPI;
import com.steve.problem.ProblemApp;
import com.steve.util.CommandUtils;

public class RandomPackage {
	public static int RandomOdd(int a, int b) {
		int m = (int) Math.ceil((a - 1) / 2);
		int n = (int) Math.floor((b - 1) / 2);
		int k = m + (int) Math.floor(Math.random() * (n - m)) + 1;
		return 2 * k + 1;
	}

	public static int RandomEven(int a, int b) {
		int m = (int) Math.ceil(a / 2);
		int n = (int) Math.floor(b / 2);
		int k = m + (int) Math.floor(Math.random() * (n - m)) + 1;
		return 2 * k;
	}

	public static int[] RandomArray(int a, int b, int k) {
		int[] array = new int[k];
		for (int i = 0; i < k; i++) {
			array[i] = (int) ((int) a + Math.floor(Math.random() * (b - a + 1)));
		}
		return array;
	}

	public static int RandomNum(int a, int b) {
		return a + (int) Math.floor(Math.random() * (b - a + 1));
	}

	public static double RandomDouble(int a, int b, int n) {

		double result = a + Math.random() * (b - a);
		result = Double.valueOf(String.format("%." + n + "f", result));
		return result;
	}

	public static double RandomDouble(double a, double b, int n) {

		double result = a + Math.random() * (b - a);
		result = Double.valueOf(String.format("%." + n + "f", result));
		return result;
	}
	
	/**
	 * use pseudo to generate a random structure in json format
	 * @param code -- pseudo code
	 * @return String of json format of this random structure
	 */
	public static String RandomStructure(String code) {
		final String PYTHON_LOCATION = "/home/ec2-user/pseudoCompiler/pseudoStr.py";
		
		// redirect system out 
    	ByteArrayOutputStream writeTo = new ByteArrayOutputStream();
    	PrintStream out = System.out;
    	System.setOut(new PrintStream(writeTo));
    	String jsonResult = "";
    	try
    	{
    		String[] cmdarray = new String[3];
    		cmdarray[0] = "/usr/bin/python3";
    		cmdarray[1] = PYTHON_LOCATION;
    		cmdarray[2] = code;
    		try {
    			CommandUtils.runProcessAndArgumentsPrintSTDOUT(cmdarray);
    		} catch (Exception e) {
    			e.printStackTrace();
    			System.out.println("error");
    		}
    		
    		jsonResult = new String(writeTo.toByteArray(), java.nio.charset.StandardCharsets.UTF_8);
    	}
    	finally
    	{
    	    System.setOut(out);
    	}
    	
		return String.format("<graph graph-data=\"%s\"/>", jsonResult);
	}

}
