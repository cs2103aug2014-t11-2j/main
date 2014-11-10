package parser;

import java.util.Vector;
import java.util.regex.Matcher;

//@author A0110788B

/**
 * Class containing all relevant methods involving time.
 */
public class ParserTimeLocal {
	
	private static final int SMALLER = -1;
	private static final int SAME = 0;
	private static final int LARGER = 1;
	protected static String defaultEndTime = "2359";
	protected static String defaultStartTime = "0000";
	
	/**
	 * Method compares the two time inputs to determine if they are equal, larger or smaller.
	 * The case are as follows:
	 * <br><br>
	 * -1 id timeA < timeB
	 * <br><br>
	 * 0 if timeA = timeB
	 * <br><br>
	 * 1 if timeA > timeB
	 *  
	 * @param timeA first time input to be compared.
	 * @param timeB second time input to be compared.
	 * @return int value representing result -1 id timeA < timeB, 0 if timeA = timeB and 1 if timeA > timeB
	 */
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

	/**
	 * Method extracts the end time from the String input. If no time is detected, the default end
	 * time 2359 is entered. If more than one time is found, the first time input is replaced and
	 * the second is extracted.
	 * 
	 * @param input String to have the end time extracted from.
	 * @return end time String.
	 */
	protected static String extractEndTime(String input) {
		input = ParserDateLocal.replaceAllDate(input);
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(input);
		String endTime = new String();

		if(hasTwoTimeInputs(input)) {
			input = timeMatcher.replaceFirst("");
		}

		endTime = extractStartTime(input);

		if(!timeMatcher.find()) {
			endTime = ParserTimeLocal.defaultEndTime;
		}

		return endTime;
	}

	/**
	 * Method determines if input String has 2 time. Returns false otherwise.
	 * 
	 * @param input String to be checked.
	 * @return true if there are two time with the input.
	 */
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

	/**
	 * Method extracts the start time from the String input. If time extracted from input is in an invalid format,
	 * an empty String is returned.
	 * 
	 * @param input String to have start time extracted from.
	 * @return start time String.
	 */
	protected static String extractStartTime(String input) {
		input = ParserDateLocal.replaceAllDate(input);
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(input);
		String startTime = new String();

		if(timeMatcher.find()) {
			startTime = timeMatcher.group();
		} else if(!timeMatcher.find()) {
			startTime = ParserTimeLocal.defaultStartTime; 
		}

		return determineTimeValidity(startTime);
	}

	/**
	 * Method returns the inputTime String if format is valid, empty String otherwise.
	 * 
	 * @param inputTime String to be checked.
	 * @return the inputTime String if format is valid, empty String otherwise.
	 */
	public static String determineTimeValidity(String inputTime) {
        if(!timeFormatValid(inputTime)) {
        	return "";
		}
		return inputTime;
	}

	/**
	 * Method checks if time format is valid and returns true if so.
	 * 
	 * @param time String to be checked.
	 * @return true if time is valid.
	 */
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

	/**
	 * Method to check if time exists.
	 * 
	 * @param time String to be checked.
	 * @return true if time exists.
	 */
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
	/**
	 * Method replaces all time found.  
	 * <br><br>
	 * Flaw: Method replaces any and all numerical String portions found to have 4 digits.
	 * 
	 * @param input String to be changed.
	 * @return input String with time removed.
	 */
	protected static String replaceAllTime(String input) {
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(input);
		
			input = timeMatcher.replaceAll("");
			
		return input;
	}

	/**
	 * Method to extract tentative time.
	 * 
	 * @param input String to have tentative time extracted from.
	 * @return tentative time Vector.
	 */
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

