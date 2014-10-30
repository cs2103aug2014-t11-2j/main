package parser;

import java.util.regex.Matcher;

public class InputIsClear {
	
	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputClear = new RefinedUserInput();
		String specialContent = ExtractSpecialContent.forClear(userInput);
		String endDate = ParserDateLocal.extractEndDate(userInput);
		
		if(specialContent.isEmpty() || endDate.isEmpty()){
			inputClear.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputClear;
		}
		
		Matcher onMatcher = ParserPatternLocal.onPattern.matcher(specialContent);
		Matcher beforeMatcher = ParserPatternLocal.beforePattern.matcher(specialContent);
		Matcher betweenMatcher = ParserPatternLocal.betweenPattern.matcher(specialContent);

		if(onMatcher.find() || beforeMatcher.find()) {
			
			inputClear.setCommandType(EnumGroup.CommandType.CLEAR);
			inputClear.setEndDate(endDate);
			inputClear.setSpecialContent(specialContent);

		} else if (betweenMatcher.find()) { 
			String startDate = ParserDateLocal.extractStartDate(userInput);

			if(startDate.isEmpty()){
				inputClear.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputClear;
			}

			inputClear.setCommandType(EnumGroup.CommandType.CLEAR);
			inputClear.setStartDate(startDate);
			inputClear.setEndDate(endDate);
			inputClear.setSpecialContent(specialContent);

		}
		return inputClear;
	}
}
