package com.steve.TypeDefinition;

public class integerArray {
	protected int[] array1;
	public integerArray(){
		
	}
	public integerArray(int[] temp){
		array1=temp;
	}
	
	public void setArray(int[] temp){
		array1=temp;
	}
	
	public int[] getArray(){
		return array1;
	}
	
	public String toString(){
		String result = " \\{";
		result = result + array1[0];
		for (int i = 1; i < array1.length; i++){
			result = result + "," + array1[i];
		}
		result = result + "\\} ";
		System.out.println(result);
		return result;
	}
}
