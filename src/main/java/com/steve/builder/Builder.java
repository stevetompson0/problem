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
	
	public void buildConstructor(PrintWriter output);
	
	public void buildHeader(PrintWriter output);
	
	public void buildGenerator(PrintWriter output);
	
	public void buildMain(PrintWriter output);
	
	public void generateProblem();

}
