package parser;

import parser.EnumGroup.CommandType;

public class InputIsConfirm {
	
	protected static RefinedUserInput refineInput(String userInput) {
		String id = Misc.extractId(userInput);
		String date = ParserDateLocal.extractStartDate(userInput);
		
		if(date == null) {
			return new RefinedUserInput(1);
		}
		
		String time = ParserTimeLocal.extractStartTime(userInput);
		
		if(time == null) {
			return new RefinedUserInput(1);
		}
		
		RefinedUserInput inputConfirm =  new RefinedUserInput(
				CommandType.CONFIRM, id,
				null, date,
				time, null,
				null, null,
				null);
		
		return inputConfirm;
	}
}
