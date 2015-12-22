package com.steve.builder;

import java.io.PrintWriter;

/**
 * Builder -- interface for problem builder which is used to build and compile
 * 			  problem program
 * 
 * @author steve
 *
 */

public interface Builder {
	
	public void buildConstructor(PrintWriter output, String filename);
	
	public void buildHeader(PrintWriter output);
	
	public void buildGenerator(PrintWriter output, String name);
	
	public void buildMain(PrintWriter output, String classname);
	
	public void generateProblem(String name) throws Exception;

}
