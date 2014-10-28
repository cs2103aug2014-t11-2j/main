package parser;

import java.util.regex.Matcher;

/*
 * This is to check time format and its validity
 * Prompt the user until the user input the correct format <hhmm>
 */
public class ParserTimeLocal {
	
	protected static String defaultEndTime = "2359";
	protected static String defaultStartTime = "0000";
	
	/* Still haven't dealt with following inputs: 
	 * [add] [start date] [start time] [end date]
	 * [add] [start date] [end date] [end time]
	 * Parser cannot distinguish between the two YET
	 */
	protected static String extractEndTime(String userInput) {
		userInput = ParserDateLocal.replaceAllDate(userInput);
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(userInput);
		String endTime = new String();

		//this is assuming for appointment creation, user either has two time inputs
		//or no time inputs
		if(hasTwoTimeInputs(userInput)) {
			userInput = timeMatcher.replaceFirst("");
		}

		endTime = extractStartTime(userInput);

		if(!timeMatcher.find()) {
			endTime = ParserTimeLocal.defaultEndTime;
		}

		return endTime;
	}

	protected static boolean hasTwoTimeInputs(String input) {
		String[] temp = ParserPatternLocal.timePattern.split(input);

		if(temp.length >= 2) {
			return true;
		} else {
			return false;
		}
	}

	protected static String extractStartTime(String userInput) {
		userInput = ParserDateLocal.replaceAllDate(userInput);
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(userInput);
		String startTime = new String();

		if(timeMatcher.find()) {
			startTime = timeMatcher.group();
		} else if(!timeMatcher.find()) {
			startTime = ParserTimeLocal.defaultStartTime; 
		}

		return determineTimeValidity(startTime);
	}

	public static String determineTimeValidity(String inputTime) {
        if(!timeFormatValid(inputTime) && !inputTime.equalsIgnoreCase("default")) {
			return "";
		}
		return inputTime;
	}

	public static boolean timeFormatValid(String time) {
		boolean timeValidity = true;

		if(time.length() != 4) {
			timeValidity = false;
		} else if(!time.matches("[0-9]+")) {
			timeValidity = false;
		} else if(!timeExists(Integer.parseInt(time))) {
			timeValidity = false;
		}
		return timeValidity;
	}

	public static boolean timeExists(int time) {
		boolean timeExist = false;

		int min = time % 100;
		int hr = time / 100;

		if(min <= 59 && min >= 0 && hr <= 23 && hr >= 0) {
			timeExist = true;
		}
		return timeExist;
	}

	//DESIGN FLAW: method will replace any and all 4 number input
	//for eg. the year part of date or even in a long sequence of numbers
	protected static String replaceAllTime(String input) {
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(input);
		
		//if(timeMatcher.requireEnd()) {
			input = timeMatcher.replaceAll("");
		//}
		return input;
	}
}

