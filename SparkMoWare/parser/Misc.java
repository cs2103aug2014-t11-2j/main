package parser;

import java.util.regex.Matcher;

/* For now, this class contains miscellaneous methods that will either
 * be moved into other classes, have its name changed, or even stay
 * as it is.
 */
public class Misc {
	
    protected static Boolean isFloatingAssignment(String userInput) {
        Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(userInput);
        Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(userInput);
        
        if(!dateMatcher.find() && !timeMatcher.find()) {
            return true;
        } else {
            return false;
        }
    }
	
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
		}
		return input;
	}
	
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
	
	protected static String removeId(String input) {
		Matcher idMatcher = ParserPatternLocal.idPattern.matcher(input);
		
		if(idMatcher.find()) {
			input = idMatcher.replaceFirst("");
		}
		return input.trim();
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
		Matcher idMatcher = ParserPatternLocal.idPattern.matcher(userInput);
		String id = new String();
		
		if(idMatcher.find()) {
			id = idMatcher.group();
		}
		
		return determineIdValidity(id);
	}
	
	protected static String determineIdValidity(String id) {
		
		if(id.length() != 12 || id.isEmpty()) {
			return "";
		}
		
		String datePortion = id.substring(0, 8);

		if(!ParserDateLocal.dateFormatValid(datePortion)) {
			return "";
		}
		return id;
	}

	protected static String extractPriority(String userInput) {
		Matcher notimportantMatcher = ParserPatternLocal.notImportantPattern.matcher(userInput);
		Matcher importantMatcher = ParserPatternLocal.importantPattern.matcher(userInput);
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
