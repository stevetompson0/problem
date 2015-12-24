package com.steve.parser;

import java.util.List;

/**
 * Parser -- interface for problem parser
 * 
 * @author steve
 *
 */
public interface Parser {
	// parse the given problem's variable section
	public void parseVariable();
	
	// parse the given problem's generator section
	public void parseGenerator();
	
	// parse the given problem's body fields and text section
	public void parseBody();
	
	// parse the given problem's answer fields and text section
	public void parseAnswer();
	
}
