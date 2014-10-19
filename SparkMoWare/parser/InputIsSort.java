package parser;

import parser.EnumGroup.CommandType;

//Does not support within a period of time yet
public class InputIsSort {
	
	protected static RefinedUserInput refineInput(String userInput) {
		String specialContent = ExtractSpecialContent.forSort(userInput);
		
		RefinedUserInput inputSort =  new RefinedUserInput(
				CommandType.SORT, null,
				null, null,
				null, null,
				null, null,
				specialContent);
		
		return inputSort;
	}
	
}
