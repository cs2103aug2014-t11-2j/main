package parser;

import parser.EnumGroup.AssignmentType;
import parser.EnumGroup.CommandType;

public class InputIsClear {
	
	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputClear =  new RefinedUserInput(
				CommandType.CLEAR, null,
				null, extractStartDate(userInput),
				null, extractEndDate(userInput),
				null, null,
				extractSpecialContent);
		
		return inputClear;
	}
	
}
