package parser;

import java.util.regex.Matcher;

/**
 * Collection of miscellaneous methods used by the Parser.
 * @author Matthew Song
 *
 */
public class Misc {
	
	/**
	 * Method checks if input contains any date or time.
	 * 
	 * @param input String to be checked.
	 * @return true if no date or time inputs are detected.
	 */
    protected static Boolean isFloatingAssignment(String input) {
        Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(input);
        Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(input);
        
        if(!dateMatcher.find() && !timeMatcher.find()) {
            return true;
        } else {
            return false;
        }
    }
	
    /**
     * Method extracts title from input by removing unnecessary information. All dates and times,
     * the String command in the input and the priority.
     * 
     * @param input String to have title extracted from.
     * @param command String command to be removed.
     * @return title String
     */
	protected static String extractTitle(String input, String command) {
		input = ParserDateLocal.replaceAllDate(input);
		input = ParserTimeLocal.replaceAllTime(input);
		input = removeCommand(input, command);
		input = removePriority(input);
		
		input = input.trim();
		String[] temp = input.split(" ");
		
		if(temp.length == 0) {
			return "";
		}
		
		return refineString(temp);
	}
	
	/**
	 * Method removes the first instance of the String command matching the String input. If no
	 * match is found, the same input is returned.
	 * 
	 * @param input String to be changed.
	 * @param command String to be removed from input String.
	 * @return String after the removal of the String command.
	 */
	protected static String removeCommand(String input, String command) {
		if(command.equals("add")) {
			Matcher addMatcher = ParserPatternLocal.addPattern.matcher(input);
			
			return addMatcher.replaceFirst("").trim();
		} else if(command.equals("tentative")) {
			Matcher tentativeMatcher = ParserPatternLocal.tentativePattern.matcher(input);
			
			return tentativeMatcher.replaceFirst("").trim();
		} else if(command.equals("edit")) {
			Matcher editMatcher = ParserPatternLocal.editPattern.matcher(input);

			return editMatcher.replaceFirst("").trim();
		} else if(command.equals("confirm")) {
			Matcher confirmMatcher = ParserPatternLocal.confirmPattern.matcher(input);

			return confirmMatcher.replaceFirst("").trim();
		} else if(command.equals("delete")) {
			Matcher deleteMatcher = ParserPatternLocal.deletePattern.matcher(input);

			return deleteMatcher.replaceFirst("").trim();
		} else if(command.equals("clear")) {
			Matcher clearMatcher = ParserPatternLocal.clearPattern.matcher(input);
			
			return clearMatcher.replaceFirst("");
		} else if(command.equals("search")) {
			Matcher searchMatcher = ParserPatternLocal.searchPattern.matcher(input);
			
			return searchMatcher.replaceFirst("");
		} else if(command.equals("sort")) {
			Matcher sortMatcher = ParserPatternLocal.sortPattern.matcher(input);
			
			return sortMatcher.replaceFirst("");
		} else if(command.equals("filter")) {
			Matcher filterMatcher = ParserPatternLocal.filterPattern.matcher(input);
			
			return filterMatcher.replaceFirst("");
		} else if(command.equals("finish")) {
			Matcher finishMatcher = ParserPatternLocal.finishPattern.matcher(input);
			
			return finishMatcher.replaceFirst("");
		}
		return input;
	}
	
	/**
	 * Method removes any matching instances of priority.
	 * 
	 * @param input String to be changed.
	 * @return String after removal of priority.
	 */
	protected static String removePriority(String input) {
		Matcher importantMatcher = ParserPatternLocal.importantPattern.matcher(input);
		Matcher notImportantMatcher = ParserPatternLocal.notImportantPattern.matcher(input);
		
		if(notImportantMatcher.find()) {
			input = notImportantMatcher.replaceAll("");
		} else if(importantMatcher.find()) {
			input = importantMatcher.replaceAll("");
		}
		return input.trim();
	}
	
	/**
	 * Method removes any extra whitepaces between words.
	 * 
	 * @param unrefinedString String Array to be combined.
	 * @return String with only single whitespaces between the words.
	 */
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
	
	/**
	 * Method to extract priority. If not matching priority is found, returns not important.
	 * 
	 * @param input String to have priority extracted from.
	 * @return priority String
	 */
	protected static String extractPriority(String input) {
		Matcher notimportantMatcher = ParserPatternLocal.notImportantPattern.matcher(input);
		Matcher importantMatcher = ParserPatternLocal.importantPattern.matcher(input);
		String notImportant = "NIMPT";
		String important = "IMPT";
		
		if(notimportantMatcher.find()) {
			return notImportant;
		} else if(importantMatcher.find()) {
			return important;
		} else {
			return notImportant;
		}
	}

	/**
	 * Method removes the first instance of title from the String input.
	 * 
	 * @param input String to be changed.
	 * @return String after removal of the String title from input.
	 */
	protected static String removeEditTitle(String input) {
		Matcher titleMatcher = ParserPatternLocal.titlePattern.matcher(input);
		
		if(titleMatcher.find()) {
			input = titleMatcher.replaceFirst("");
		}
		return input;
	}
	
	/**
	 * Method removes zeroes until input is empty or encounters a non-zero character.
	 * 
	 * @param input String to be changed
	 * @return String with leading zeroes removed.
	 */
	protected static String removeFrontZero(String input) {
		while (input.length() > 0 && input.charAt(0) == '0') {
			input = input.substring(1);
		}
		return input;
	}
}
