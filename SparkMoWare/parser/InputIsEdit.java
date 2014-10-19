package parser;

import parser.EnumGroup.CommandType;

//incomplete
public class InputIsEdit {
	
	protected static RefinedUserInput refineInput(String userInput) {
		String id = Misc.extractId(userInput);
		
		RefinedUserInput inputDisplay =  new RefinedUserInput(
				CommandType.DISPLAY, null,
				null, null,
				null, null,
				null, null,
				null);
		
		return inputDisplay;
	}
	
}
