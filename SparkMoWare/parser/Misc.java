package parser;

import java.util.Scanner;
import java.util.regex.Matcher;

/* For now, this class contains miscellaneous methods that will either
 * be moved into other classes, have its name changed, or even stay
 * as it is.
 */
public class Misc {
	
	protected static String extractTitle(String userInput) {
		
		userInput = ParserDateLocal.replaceAllDate(userInput);
		userInput = ParserTimeLocal.replaceAllTime(userInput);
		userInput = userInput.replace("add", "");
		userInput.trim();
		String[] temp = userInput.split(" ");
		
		//should have a step to prompt for title
		if(temp.length == 0) {
			return "";
		}
		
		return refineString(temp);
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
	
		//checking if Id exists should be done by logic
		//Parser has no access to Buffer.
	protected static String extractId(String userInput) {
		userInput = ParserDateLocal.replaceAllDate(userInput);
		userInput = ParserTimeLocal.replaceAllTime(userInput);
		
		return determineIdValidity(userInput);
	}
	
	protected static String determineIdValidity(String id) {
		Scanner sc = new Scanner(System.in); 
		String datePortion = id.substring(0, 7);

		while(!ParserDateLocal.dateFormatValid(datePortion)) {
			Message.printToUser(Message.INVALID_FORMAT);
			Message.printToUser(String.format(Message.FORMAT_PROMPT, "id"));
			id = sc.nextLine();
			datePortion = id.substring(0, 7);

			Matcher rejectMatcher = ParserPatternLocal.rejectPattern.matcher(datePortion);

			if(rejectMatcher.find()) {
				sc.close();
				return "";
			}
		}
		sc.close();
		return datePortion;
	}
	
}
