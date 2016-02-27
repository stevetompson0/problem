package com.steve.RandomPackage;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.Test;

public class GraphToByteTest {
	@Test
	public void testStringToByte() {
		String temp = "askdjaskdjk\"\nsajdkas";
		byte[] array = temp.getBytes(StandardCharsets.UTF_8);
		System.out.println(Arrays.toString(array));
	}

}
