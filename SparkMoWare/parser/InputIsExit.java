package parser;

import parser.EnumGroup.CommandType;

public class InputIsExit {
	
	protected static RefinedUserInput refineInput(String userInput) {
		
		RefinedUserInput inputExit =  new RefinedUserInput(
				CommandType.EXIT, null,
				null, null,
				null, null,
				null, null,
				null);
		
		return inputExit;
	}
}
