package parser;

import parser.EnumGroup.CommandType;

public class InputIsTentative {
	
	protected static RefinedUserInput refineInput(String userInput) {
		String specialContent = ExtractSpecialContent.forTentative(userInput);
		
		RefinedUserInput inputTentative =  new RefinedUserInput(
				CommandType.TENTATIVE, null,
				null, null,
				null, null,
				null, null,
				specialContent);
		
		return inputTentative;
	}
	
}
