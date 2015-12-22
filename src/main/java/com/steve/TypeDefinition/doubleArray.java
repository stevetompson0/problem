package com.steve.TypeDefinition;

public class doubleArray {
	protected double[] array1;
	public doubleArray(){
		
	}
	public doubleArray(double[] temp){
		array1 = temp;
	}
	
	public void setArray(double[] temp){
		array1 = temp;
	}
	
	public double[] getArray(){
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
