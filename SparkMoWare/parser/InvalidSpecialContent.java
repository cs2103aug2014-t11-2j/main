package parser;

import java.util.regex.Matcher;

public class InvalidSpecialContent {

	protected static Boolean contentForClear(String input) {
		input = input.trim();
		Matcher onMatcher = ParserPatternLocal.onPattern.matcher(input);
		
		if(onMatcher.matches()){
			return true;
		}
		
		Matcher beforeMatcher = ParserPatternLocal.beforePattern.matcher(input);
				
		if(beforeMatcher.matches()) {
			return true;
		}
		
		Matcher betweenMatcher = ParserPatternLocal.betweenPattern.matcher(input);
		
		if(betweenMatcher.matches()) {
			return true;
		}
		
		return false;
	}
}
