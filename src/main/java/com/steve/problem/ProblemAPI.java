package com.steve.problem;

// from builder get BuildPath
import com.steve.builder.SimpleProblemBuilder;
import com.steve.util.CommandUtils;

/**
 * Hello world!
 *
 */
public class ProblemAPI 
{
	// mode constant for fetch a problem instance
	public static final int FETCH_MODE = 0;
	// mode constant for creating a problem
	public static final int CREATE_MODE = 1;
	
	// exit status code
	public static final int CREATE_SUCCESS = 1;
	public static final int CREATE_ERROR = 2;
	public static final int FETCH_SUCCESS = 3;
	public static final int FETCH_ERROR = 4;

    public static int mainCall(String[] args)
    {
    	
    	int mode = Integer.parseInt(args[0]);
    	// get problem id
    	String name = args[1];
    	
    	// create problem 
    	if (mode == ProblemApp.CREATE_MODE) {
    		int type = Integer.parseInt(args[2]);
	    	String jsonInput = args[3];
	    	
	    	Problem problem;
				
			if (type == 1) {
				try {
					problem = new SimpleProblem(type, jsonInput, name);
					int result = 0;
					// parse the problem
					try {
						problem.parse();
					}
					catch (Exception e) {
						// TODO: log e
						result ++;
					}
					
					// build the problem
					result += problem.build();
					
					if (result > 0) {
						System.out.println("error");
						//TODO: log the error
						return CREATE_ERROR;
					}
					
				} catch (Exception e) {
					System.out.println("error");
					//TODO: log the error
					return ProblemApp.CREATE_ERROR;
				}
			}
			return ProblemApp.CREATE_SUCCESS;
    	}
    	// fetch problem instance
    	else if (mode == ProblemApp.FETCH_MODE) {
    		String byteLocation = SimpleProblemBuilder.PATH_TO_STORE + SimpleProblemBuilder.BYTE_DIRECTORY_NAME;
    		String command = String.format("java -cp $CLASSPATH:%s:%s %s", byteLocation, SimpleProblemBuilder.CLASS_PATH, name);
    		try {
				int result = CommandUtils.runProcessPrintSTDOUT(command);
				if (result != 0) {
					System.out.println("error");
					return FETCH_ERROR;
				}
				
			} catch (Exception e) {
				System.out.println("error");
				//TODO: log the error
				return ProblemApp.FETCH_ERROR;
			}
    		return ProblemApp.FETCH_SUCCESS;
    	}
    	return ProblemApp.FETCH_ERROR;
    }
}
