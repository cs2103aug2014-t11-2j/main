package parser;

import java.util.regex.Matcher;

/**
 * 
 * @author Matthew Song
 *
 */
public class InputIsSearch {
	
	/**
	 * Method creates a RefinedUserInput for the search command. If no special content is
	 * found, an Invalid Format CommandType is returned.
	 * 
	 * @param userInput the String with all the relevant information to be extracted.
	 * @return a RefinedUserInput object for the filter command.
	 */
	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputSearch = new RefinedUserInput(); 
		String specialContent  = ExtractSpecialContent.forSearch(userInput);
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(specialContent);
		
		if(dateMatcher.find()) {
			specialContent = ParserDateLocal.extractEndDate(specialContent);
		}
		
		if(specialContent.isEmpty()) {
			inputSearch.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputSearch;
		}
		
		inputSearch.setCommandType(EnumGroup.CommandType.SEARCH);
		inputSearch.setSpecialContent(specialContent);
		
		return inputSearch;
	}
}
