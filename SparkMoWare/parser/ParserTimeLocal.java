package parser;

import java.util.Vector;
import java.util.regex.Matcher;

public class ParserTimeLocal {
	
	private static final int SMALLER = -1;
	private static final int SAME = 0;
	private static final int LARGER = 1;
	protected static String defaultEndTime = "2359";
	protected static String defaultStartTime = "0000";
	
	// Comparison for Time.
	// -1 (A < B), 0 (A = B), 1 (A > B)
	protected static int timeComparator(String timeA, String timeB) {

		String hourA = timeA.trim().substring(0, 2);
		String hourB = timeB.trim().substring(0, 2);

		String minA = timeA.trim().substring(2, 4);
		String minB = timeB.trim().substring(2, 4);

		if(hourA.equals(hourB)) {
			if(!minA.equals(minB)) {

				if(minA.equals("00") && !minB.equals("00")) {
					return SMALLER;
				} else if(!minA.equals("00") && minB.equals("00")) {
					return LARGER;
				} else if(!minA.equals("00") && !minB.equals("00")) {
					minA = Misc.removeFrontZero(minA);
					minB = Misc.removeFrontZero(minB);

					if(Integer.parseInt(minA) > Integer.parseInt(minB)) {
						return LARGER;
					} else {
						return SMALLER;
					}
				}
			} else {
				return SAME;
			}
		} else if(hourA.equals("00") && !hourB.equals("00")) {
			return SMALLER;
		} else if(!hourA.equals("00") && hourB.equals("00")) {
			return LARGER;
		} else if(!hourA.equals("00") && !hourB.equals("00")) {
			hourA = Misc.removeFrontZero(hourA);
			hourB = Misc.removeFrontZero(hourB);

			if(Integer.parseInt(hourA) > Integer.parseInt(hourB)) {
				return LARGER;
			} else {
				return SMALLER;
			}
		}
		return SAME;
	}
	
	/* Still haven't dealt with following inputs: 
	 * [add] [start date] [start time] [end date]
	 * [add] [start date] [end date] [end time]
	 * Parser cannot distinguish between the two YET
	 */
	protected static String extractEndTime(String userInput) {
		//userInput = ParserIdLocal.removeId(userInput);
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
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(input);
		int n = 0;
		
		while(timeMatcher.find()) {
			input = timeMatcher.replaceFirst("");
			timeMatcher = ParserPatternLocal.timePattern.matcher(input);
			n++;
		}

		if(n > 1) {
			return true;
		} else {
			return false;
		}
	}

	protected static String extractStartTime(String userInput) {
		//userInput = ParserIdLocal.removeId(userInput);
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
        if(!timeFormatValid(inputTime)) {
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
		
			input = timeMatcher.replaceAll("");
			
		return input;
	}

	public static Vector<String> extractTentativeTimes(String input) {
		Vector<String> tentativeTimes = new Vector<String> ();
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(input);
		input = dateMatcher.replaceFirst("");
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(input);
		
		while(timeMatcher.find()) {
			tentativeTimes.add(timeMatcher.group());
			input = timeMatcher.replaceFirst("");
			
			dateMatcher = ParserPatternLocal.datePattern.matcher(input);
			
			if(dateMatcher.find()) {
				input = dateMatcher.replaceFirst("");
			}
			
			timeMatcher = ParserPatternLocal.timePattern.matcher(input);
		}
		
		return tentativeTimes;
	}
	
	
}

