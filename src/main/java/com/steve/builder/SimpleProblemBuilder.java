package com.steve.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.steve.problem.Problem;

public class SimpleProblemBuilder implements Builder{
	private PrintWriter output;
	private Problem problem;
	
	public SimpleProblemBuilder(Problem problem) throws FileNotFoundException {
		this.problem = problem;
		File outputFile = new File(problem.getName() + ".java");
		output = new PrintWriter(outputFile);
	}

	@Override
	public void buildConstructor(PrintWriter output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildHeader(PrintWriter output) {
		output.println("test");
	}

	@Override
	public void buildGenerator(PrintWriter output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildMain(PrintWriter output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateProblem() {
		buildHeader(output);
		buildConstructor(output);
		
		output.close();
	}

}
