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
	public List<String> parseVariable();
	
	// parse the given problem's generator section
	public List<String> parseGenerator();
	
	// parse the given problem's body fields section
	public List<String> parseFields();
	
	// parse the given problem's body text section
	public List<String> parseText();
	
	// parse the given problem's answer fields section
	public List<String> parseAnswerFields();
	
	// parse the given problem's answer text section
	public List<String> parseAnswerText();
	
}
