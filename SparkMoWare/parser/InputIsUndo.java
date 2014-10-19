package parser;

import parser.EnumGroup.CommandType;

public class InputIsUndo {
	
	protected static RefinedUserInput refineInput(String userInput) {
		
		RefinedUserInput inputUndo =  new RefinedUserInput(
				CommandType.UNDO, null,
				null, null,
				null, null,
				null, null,
				null);
		
		return inputUndo;
	}
}
