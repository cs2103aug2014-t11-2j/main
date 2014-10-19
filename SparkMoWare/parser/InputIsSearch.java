package parser;

import parser.EnumGroup.CommandType;

public class InputIsSearch {
	
	protected static RefinedUserInput refineInput(String userInput) {
		String specialContent  = ExtractSpecialContent.forSearch(userInput);
		
		RefinedUserInput inputSearch =  new RefinedUserInput(
				CommandType.SEARCH, null,
				null, null,
				null, null,
				null, null,
				specialContent);
		
		return inputSearch;
	}
	
}
