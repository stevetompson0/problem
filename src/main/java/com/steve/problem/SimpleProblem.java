package com.steve.problem;

import com.steve.parser.Parser;
import com.steve.parser.SimpleProblemParser;

/**
 * SimpleProblem -- simple parameterized problem with parameterized blank
 * 					filling answer
 * 
 * @author steve
 *
 */

public class SimpleProblem extends BaseProblem implements Problem{
	private Parser parser = new SimpleProblemParser(this);
	
	private SimpleProblem(int type, String json_data, String name) {
		super(type, json_data, name);
	}

	@Override
	public void parse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void build() {
		// TODO Auto-generated method stub
		
	}
	
	
}
