package parser;

import java.util.regex.Matcher;

/**
 * 
 * @author Matthew Song
 *
 */
public class InvalidSpecialContent {

	/**
	 * Method checks if content matches the available choices for the clear command  
	 * 
	 * @param input String to be checked.
	 * @return Boolean true if the input matches any of the available choices for the clear command.
	 */
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
