package com.steve.TypeDefinition;

public class StringArray {
	protected String[] array1;
	public StringArray(){
		
	}
	public StringArray(String[] temp){
		array1 = temp;
	}
	
	public void setArray(String[] temp){
		array1 = temp;
	}
	
	public String[] getArray(){
		return array1;
	}
	
	public String toString(){
		String result = " \\{";
		result = result + array1[0];
		for (int i = 1; i < array1.length; i++){
			result = result + "," + array1[i];
		}
		result = result + "\\} ";
		return result;
	}
}
