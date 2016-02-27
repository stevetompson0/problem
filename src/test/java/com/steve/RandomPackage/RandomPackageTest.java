package com.steve.RandomPackage;

import org.junit.Test;

public class RandomPackageTest {
	
	@Test
	public void testPythonCode() {
		String code = "x = Array.random(length=4, valrange=range(100))";
		String result = RandomPackage.RandomStructure(code);
		
		System.out.println(result);
	}

}
