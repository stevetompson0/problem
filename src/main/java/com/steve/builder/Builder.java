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
	
	// build java source constructor
	public void buildConstructor(PrintWriter output);
	
	// build java source header
	public void buildHeader(PrintWriter output);
	
	// build java source generating function
	public void buildGenerator(PrintWriter output);
	
	// build java source main function
	public void buildMain(PrintWriter output);
	
	// use the above functions to build a java source and compile it
	// return 0 means succeeds, return 1 means error
	public int generateProblem();

}
