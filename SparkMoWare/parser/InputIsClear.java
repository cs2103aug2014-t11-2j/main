package parser;

import java.util.regex.Matcher;

public class InputIsClear {
	
	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputClear = new RefinedUserInput();
		String specialContent = ExtractSpecialContent.forClear(userInput);
		
		if(specialContent.isEmpty()){
			return inputClear;
		}
			
		String endDate = ParserDateLocal.extractEndDate(userInput);
		
		if(endDate.isEmpty()){
			return inputClear;
		}
		
		Matcher onMatcher = ParserPatternLocal.onPattern.matcher(specialContent);
		Matcher beforeMatcher = ParserPatternLocal.onPattern.matcher(specialContent);
		Matcher betweenMatcher = ParserPatternLocal.onPattern.matcher(specialContent);

		if(onMatcher.matches() || beforeMatcher.matches()) {
			
			inputClear.setCommandType(EnumGroup.CommandType.CLEAR);
			inputClear.setEndDate(endDate);
			inputClear.setSpecialContent(specialContent);

		} else if (betweenMatcher.matches()) { 
			String startDate = ParserDateLocal.extractStartDate(userInput);

			if(startDate.isEmpty()){
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
