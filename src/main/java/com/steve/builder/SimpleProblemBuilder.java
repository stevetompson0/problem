package com.steve.builder;

import java.io.PrintWriter;

import com.steve.problem.Problem;

public class SimpleProblemBuilder implements Builder{
	private PrintWriter output;
	private Problem problem;
	
	public SimpleProblemBuilder(Problem problem) {
		this.problem = problem;
	}

	@Override
	public void buildConstructor(PrintWriter output, String filename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildHeader(PrintWriter output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildGenerator(PrintWriter output, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildMain(PrintWriter output, String classname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateProblem(String name) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
