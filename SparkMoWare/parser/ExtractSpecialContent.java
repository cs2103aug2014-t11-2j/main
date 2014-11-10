package parser;

import java.util.regex.Matcher;

//@author A0110788B

/**
 * The ExtractSpecial Content class is used for the following methods: Clear, Search, Sort and Filter.
 */
public class ExtractSpecialContent {

	/**
	 * Method extracts a String from the input and checks its validity for the clear command.
	 * If content is invalid or empty, an empty String is returned.
	 * @param input the String to be checked for special content.
	 * @return the special content extracted from the given input.
	 */
	protected static String forClear(String input) {
		Matcher clearMatcher = ParserPatternLocal.clearPattern.matcher(input);
		
		input = ParserDateLocal.replaceAllDate(input);
		
		if(clearMatcher.find()) {
			input = Misc.removeCommand(input, "clear");

			input = input.trim();
		} else {
			return "";
		}

		if(!InvalidSpecialContent.contentForClear(input)) {
			return "";
		}

		return input.trim();
	}

	/**
	 * Method extracts a String from the input for search command. 
	 * @param input the String to be checked for special content.
	 * @return the special content extracted from the given input.
	 */
	protected static String forSearch(String input) {
		Matcher searchMatcher = ParserPatternLocal.searchPattern.matcher(input);

		if(searchMatcher.find()) {
			input = Misc.removeCommand(input, "search");

			return input.trim();
		} else {
			return "";
		}
	}
	
	/**
	 * Method extracts a String from the input for sort command. 
	 * @param input the String to be checked for special content.
	 * @return the special content extracted from the given input.
	 */
	protected static String forSort(String input) {
		Matcher sortMatcher = ParserPatternLocal.sortPattern.matcher(input);

		if(sortMatcher.find()) {
			input = Misc.removeCommand(input, "sort");

			return input.trim();
		} else {
			return "";
		}
	}

	/**
	 * Method extracts a String from the input for filter command. 
	 * @param input the String to be checked for special content.
	 * @return the special content extracted from the given input.
	 */
	protected static String forFilter(String input) {
		Matcher filterMatcher = ParserPatternLocal.filterPattern.matcher(input);
		
		if(filterMatcher.find()) {
			input = Misc.removeCommand(input, "filter");

			return input.trim();
		} else {
			return "";
		}
	}
}
