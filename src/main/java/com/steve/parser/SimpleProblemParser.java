package com.steve.parser;

import java.util.List;

import com.steve.problem.Problem;

public class SimpleProblemParser implements Parser{
	private Problem problem;
	
	public SimpleProblemParser(Problem problem) {
		this.problem = problem;
	}

	@Override
	public List<String> parseVariable() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<String> parseGenerator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> parseFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> parseText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> parseAnswerFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<String> parseAnswerText() {
		// TODO Auto-generated method stub
		return null;
	}

}
