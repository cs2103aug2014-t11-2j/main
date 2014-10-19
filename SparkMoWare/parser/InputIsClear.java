package parser;

import java.util.regex.Matcher;

import parser.EnumGroup.CommandType;

public class InputIsClear {
	
	protected static RefinedUserInput refineInput(String userInput) {
		String specialContent = ExtractSpecialContent.forClear(userInput);
		String endDate = ParserDateLocal.extractEndDate(userInput);
		Matcher onMatcher = ParserPatternLocal.onPattern.matcher(specialContent);
		Matcher beforeMatcher = ParserPatternLocal.onPattern.matcher(specialContent);
		Matcher betweenMatcher = ParserPatternLocal.onPattern.matcher(specialContent);
		
		if(specialContent == null || endDate == null){
			return new RefinedUserInput(1);
		}
		
		if(onMatcher.matches() || beforeMatcher.matches()) {
			RefinedUserInput inputClear =  new RefinedUserInput(
					CommandType.CLEAR, null,
					null, null,
					null, endDate,
					null, null,
					specialContent);
			
			return inputClear;
			
		} else if (betweenMatcher.matches()) { 
			String startDate = ParserDateLocal.extractStartDate(userInput);
			
		RefinedUserInput inputClear =  new RefinedUserInput(
				CommandType.CLEAR, null,
				null, null,
				null, endDate,
				null, null,
				specialContent);
		
		return inputClear;
		
		} else {
			return  new RefinedUserInput(1);
		}
	}
}
