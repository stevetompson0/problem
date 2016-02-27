package com.steve.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandUtils {
	public static int runProcess(String command) throws IOException, InterruptedException {
		Process pro = Runtime.getRuntime().exec(command);
		printLines(command + " stdout:", pro.getInputStream());
		printLines(command + " stderr:", pro.getErrorStream());
		pro.waitFor();
		System.out.println(command + " exitValue() " + pro.exitValue());
		return pro.exitValue();
	}
	
	public static int runProcessPrintSTDOUT(String command) throws IOException, InterruptedException {
		Process pro = Runtime.getRuntime().exec(command);
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		pro.waitFor();
		return pro.exitValue();
	}
	
	public static int runProcessAndArgumentsPrintSTDOUT(String[] cmdarray) throws IOException, InterruptedException {
		Process pro = Runtime.getRuntime().exec(cmdarray);
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		pro.waitFor();
		return pro.exitValue();
	}

	public static void printLines(String name, InputStream ins) throws IOException {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		while ((line = in.readLine()) != null) {
			System.out.println(name + " " + line);
		}
	}

}
