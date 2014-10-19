package parser;

//incomplete
import parser.EnumGroup.CommandType;

public class InputIsFilter {
	
	protected static RefinedUserInput refineInput(String userInput) {
		
		RefinedUserInput inputFilter =  new RefinedUserInput(
				CommandType.FILTER, null,
				null, null,
				null, null,
				null, null,
				null);
		
		return inputFilter;
	}
	
}
