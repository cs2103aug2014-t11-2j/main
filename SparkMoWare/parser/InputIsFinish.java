package parser;

import parser.EnumGroup.CommandType;

public class InputIsFinish {
	
	protected static RefinedUserInput refineInput(String userInput) {
		String id = Misc.extractId(userInput);
		
		RefinedUserInput inputFinish =  new RefinedUserInput(
				CommandType.DONE, id,
				null, null,
				null, null,
				null, null,
				null);
		
		return inputFinish;
	}
	
}
