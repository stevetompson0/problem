# problem

Abstract Problem Generator

Setup follow Linux System Variables:
  1. PATH_TO_STORE=<path-to-your-source-and-bytecode-directory> // i.e "/Users/steve/problem/"

  How to set Environment Variables:
  http://stackoverflow.com/questions/603785/environment-variables-in-mac-os-x
  
Usage:
  1. Create a new abstract problem
  java -jar <path-to-jar-with-dependency> 1 <problem-id> <problem-type> <json_problem_string>
  
  problem-type: 1 -- simple problem

i.e: 

java -jar problem-0.0.1-SNAPSHOT-jar-with-dependencies.jar 1 id9 1 "{\"GENERATOR\":[\"a1 = RandomPackage.RandomNum(1, 12);\",\"a2 = RandomPackage.RandomNum(1, 12);\",\"a3 = a1 * a2;\"],\"ANSWER\":\"The result\",\"VARIABLE\":[\"integer a1\",\"integer a2\",\"integer a3\"],\"BODY\":\"\"}"

  2. Fetch a problem instance
  java -jar <path-to-jar-with-dependency> 0 <problem-id>
  java -jar problem-0.0.1-SNAPSHOT-jar-with-dependencies.jar 0 id9


