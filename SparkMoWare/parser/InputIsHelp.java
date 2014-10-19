package parser;

import parser.EnumGroup.CommandType;

public class InputIsHelp {
	
	protected static RefinedUserInput refineInput(String userInput) {
		
		RefinedUserInput inputHelp =  new RefinedUserInput(
				CommandType.HELP, null,
				null, null,
				null, null,
				null, null,
				null);
		
		return inputHelp;
	}
	
}
