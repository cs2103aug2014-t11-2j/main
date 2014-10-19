package parser;

import parser.EnumGroup.CommandType;

public class InputIsRedo {
	
	protected static RefinedUserInput refineInput(String userInput) {
		
		RefinedUserInput inputRedo =  new RefinedUserInput(
				CommandType.REDO, null,
				null, null,
				null, null,
				null, null,
				null);
		
		return inputRedo;
	}
	
}
