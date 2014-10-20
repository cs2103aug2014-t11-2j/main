package parser;

import java.util.regex.Matcher;

public class ExtractSpecialContent {

	//This method may require refinement if the 3 clear patterns
	// (on, between, before) have more than one word
	protected static String forClear(String input) {
		input = ParserDateLocal.replaceAllDate(input);

		if(!InvalidSpecialContent.contentForClear(input)) {
			return "";
		}

		return input;
	}

	//untested method
	protected static String forSearch(String input) {
		Matcher searchMatcher = ParserPatternLocal.searchPattern.matcher(input);

		if(searchMatcher.find()) {
			String[] temp = ParserPatternLocal.searchPattern.split(input);

			return temp[1]; //might be 0
		} else {
			return "";
		}
	}

	//untested method
	protected static String forTentative(String input) {
		Matcher tentativeMatcher = ParserPatternLocal.tentativePattern.matcher(input);

		if(tentativeMatcher.find()) {
			String[] temp = ParserPatternLocal.tentativePattern.split(input);

			return temp[1]; //might be 0
		} else {
			return "";
		}
	}
	
	//untested method
	protected static String forSort(String input) {
		Matcher sortMatcher = ParserPatternLocal.sortPattern.matcher(input);

		if(sortMatcher.find()) {
			String[] temp = ParserPatternLocal.sortPattern.split(input);

			return temp[1]; //might be 0
		} else {
			return "";
		}
	}

	protected static String forFilter(String userInput) {
		// TODO Auto-generated method stub
		return null;
	}
}
