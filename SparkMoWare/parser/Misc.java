package parser;

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
			return null;
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
	
		//check Id validity not implemented
		//checking if Id exists should be done by logic
		//Parser has no access to Buffer.
	protected static String extractId(String userInput) {
		userInput = ParserDateLocal.replaceAllDate(userInput);
		userInput = ParserTimeLocal.replaceAllTime(userInput);
		
		return userInput;
	}
	
}
