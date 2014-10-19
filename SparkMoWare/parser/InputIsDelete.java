package parser;

import parser.EnumGroup.CommandType;

public class InputIsDelete {
	
	protected static RefinedUserInput refineInput(String userInput) {
		String id = Misc.extractId(userInput);
		
		RefinedUserInput inputDelete =  new RefinedUserInput(
				CommandType.CONFIRM, id,
				null, null,
				null, null,
				null, null,
				null);
		
		return inputDelete;
	}

}
