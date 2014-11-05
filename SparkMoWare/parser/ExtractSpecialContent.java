package parser;

import java.util.regex.Matcher;

public class ExtractSpecialContent {

	//This method may require refinement if the 3 clear patterns
	// (on, between, before) have more than one word
	protected static String forClear(String input) {
		input = ParserDateLocal.replaceAllDate(input);
		input = Misc.removeCommand(input, "clear");

		if(!InvalidSpecialContent.contentForClear(input)) {
			return "";
		}

		return input.trim();
	}

	protected static String forSearch(String input) {
		Matcher searchMatcher = ParserPatternLocal.searchPattern.matcher(input);

		if(searchMatcher.find()) {
			input = Misc.removeCommand(input, "search");

			return input.trim();
		} else {
			return "";
		}
	}
	
	protected static String forSort(String input) {
		Matcher sortMatcher = ParserPatternLocal.sortPattern.matcher(input);

		if(sortMatcher.find()) {
			input = Misc.removeCommand(input, "sort");

			return input.trim();
		} else {
			return "";
		}
	}

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
