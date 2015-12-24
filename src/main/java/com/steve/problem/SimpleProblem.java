package com.steve.problem;

import java.io.IOException;

import org.json.simple.parser.ParseException;

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
	private Parser parser;
	
	public SimpleProblem(int type, String json_data, String name) throws IOException, ParseException {
		super(type, json_data, name);
		this.parser = new SimpleProblemParser(this);
	}

	@Override
	public void parse() {
		
		parser.parseVariable();
		parser.parseGenerator();
		parser.parseBody();
		parser.parseAnswer();
		
	}

	@Override
	public void build() {
		// TODO Auto-generated method stub
		
	}
	
	
}
