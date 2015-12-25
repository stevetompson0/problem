package com.steve.problem;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.steve.builder.Builder;
import com.steve.builder.SimpleProblemBuilder;
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
	private Builder builder;
	
	public SimpleProblem(int type, String json_data, String name) throws IOException, ParseException {
		super(type, json_data, name);
		this.parser = new SimpleProblemParser(this);
		this.builder = new SimpleProblemBuilder(this);
	}

	@Override
	public void parse() {
		parser.parseProblem();
		
	}

	@Override
	public void build() {
		builder.generateProblem();
	}
	
	
}
