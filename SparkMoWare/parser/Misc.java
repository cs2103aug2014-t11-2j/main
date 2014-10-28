package parser;

import java.util.regex.Matcher;

/* For now, this class contains miscellaneous methods that will either
 * be moved into other classes, have its name changed, or even stay
 * as it is.
 */
public class Misc {
	
	protected static String extractTitle(String userInput, String command) {
		
		userInput = ParserDateLocal.replaceAllDate(userInput);
		userInput = ParserTimeLocal.replaceAllTime(userInput);
		userInput = removeCommand(userInput, command);
		userInput = removePriority(userInput);
		userInput.trim();
		String[] temp = userInput.split(" ");
		
		if(temp.length == 0) {
			return "";
		}
		
		return refineString(temp);
	}

	protected static String removeCommand(String input, String command) {
		if(command.equals("add")) {
			Matcher addMatcher = ParserPatternLocal.addPattern.matcher(input);
			
			return addMatcher.replaceFirst("");
		} else if(command.equals("tentative")) {
			Matcher tentativeMatcher = ParserPatternLocal.tentativePattern.matcher(input);
			
			return tentativeMatcher.replaceFirst("");
		}
		return input;
	}
	
	protected static String removePriority(String input) {
		Matcher importantMatcher = ParserPatternLocal.importantPattern.matcher(input);
		
		if(importantMatcher.find()) {
			input = importantMatcher.replaceAll("");
		}
		return input;
	}
	
	protected static String refineString(String [] unrefinedString) {
		int length = unrefinedString.length;
		String refinedString = new String();
		
		for(int counter = 0; counter<length; counter ++) {
			unrefinedString[counter] = unrefinedString[counter].trim();
			refinedString = refinedString.concat(unrefinedString[counter]);
			
			if(!unrefinedString[counter].isEmpty()){
				refinedString = refinedString.concat(" ");
			}
		}
		return refinedString.trim();
	}
	

	protected static String extractId(String userInput) {
		userInput = ParserDateLocal.replaceAllDate(userInput);
		userInput = ParserTimeLocal.replaceAllTime(userInput);
		
		return determineIdValidity(userInput);
	}
	
	protected static String determineIdValidity(String id) {
		String datePortion = id.substring(0, 7);

		if(!ParserDateLocal.dateFormatValid(datePortion)) {
			return "";
		}
		return datePortion;
	}

	protected static String extractPriority(String userInput) {
		Matcher importantMatcher = ParserPatternLocal.importantPattern.matcher(userInput);
		String priority = "NMPT";
		
		if(importantMatcher.find()) {
			priority = "IMPT";
		}
			return priority;
	}

	//This is a different determine validity method compared to the others
	public static boolean determinePriorityValidity(String input) {
		Matcher importantMatcher = ParserPatternLocal.importantPattern.matcher(input);
		Matcher notImportantMatcher = ParserPatternLocal.notImportantPattern.matcher(input);
		
		if(!importantMatcher.matches() && !notImportantMatcher.matches()) {
			return false;
		} else {
			return true;
		}
	}
}
