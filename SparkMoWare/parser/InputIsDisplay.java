package parser;

import parser.EnumGroup.CommandType;

public class InputIsDisplay {
	
	protected static RefinedUserInput refineInput(String userInput) {
		
		RefinedUserInput inputDisplay =  new RefinedUserInput(
				CommandType.DISPLAY, null,
				null, null,
				null, null,
				null, null,
				null);
		
		return inputDisplay;
	}
	
}
