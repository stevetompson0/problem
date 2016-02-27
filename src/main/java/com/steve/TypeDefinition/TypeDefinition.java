package com.steve.TypeDefinition;

public class TypeDefinition {
	public static String type(String name){
		
		if (name.equals("integer")){
			return "int";
		}
		else if (name.equals("integerArray")){
			return "integerArray";
		}
		else if (name.equals("double")){
			return "double";
		}
		else if (name.equals("string")){
			return "String";
		}
		else if (name.equals("char")){
			return "char";
		}
		else if (name.equals("stringArray")){
			return "StringArray";
		}
		else if (name.equals("doubleArray")){
			return "doubleArray";
		}
		else if (name.equals("AVLTree")){
			return "AVLTree";
		}
		else if (name.equals("UDGraph")){
			return "UDGraph";
		}
		else if (name.equals("Heap")){
			return "Heap";
		}
		else if (name.equals("Tree")){
			return "Tree";
		}
		else if (name.equals("DGraph")){
			return "DGraph";
		}
		else if (name.equals("RandomStructure")) {
			return "String";
		}
		else {
			return "object";
		}
	}
}

