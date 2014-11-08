package parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;

/**
 * Class containing all relevant methods involving dates.
 * 
 * @author Matthew Song
 *
 */
public class ParserDateLocal {

	private static final int SMALLER = -1;
	private static final int SAME = 0;
	private static final int LARGER = 1;

	/**
	 * Method compares the two date inputs to determine if they are equal, larger or smaller.
	 * The case are as follows:
	 * <br><br>
	 * -1 id dateA < dateB
	 * <br><br>
	 * 0 if dateA = dateB
	 * <br><br>
	 * 1 if dateA > dateB
	 * 
	 * @param dateA first date input to be compared.
	 * @param dateB second date input to be compared.
	 * @return int value representing result -1 id dateA < dateB, 0 if dateA = dateB and 1 if dateA > dateB
	 */
	protected static int dateComparator(String dateA, String dateB) {
		Matcher symbolMatcherA =  ParserPatternLocal.separatorPattern.matcher(dateA);
		Matcher symbolMatcherB =  ParserPatternLocal.separatorPattern.matcher(dateB);

		if(symbolMatcherA.find()) {
			dateA = symbolMatcherA.replaceAll("");
		}

		if(symbolMatcherB.find()) {
			dateB = symbolMatcherB.replaceAll("");
		}


		String yearA = dateA.trim().substring(4, 6);
		String yearB = dateB.trim().substring(4, 6);

		String monthA = dateA.trim().substring(2, 4);
		String monthB = dateB.trim().substring(2, 4);

		String dayA = dateA.trim().substring(0, 2);
		String dayB = dateB.trim().substring(0, 2);

		yearA = Misc.removeFrontZero(yearA);
		yearB = Misc.removeFrontZero(yearB);

		monthA = Misc.removeFrontZero(monthA);
		monthB = Misc.removeFrontZero(monthB);

		dayA = Misc.removeFrontZero(dayA);
		dayB = Misc.removeFrontZero(dayB);

		if (dateA.equals(dateB)) {
			return SAME;
		} else if (Integer.parseInt(yearA) > Integer.parseInt(yearB)) {
			return LARGER;
		} else if (Integer.parseInt(yearA) < Integer.parseInt(yearB)) {
			return SMALLER;
		} else if (Integer.parseInt(monthA) > Integer.parseInt(monthB)) {
			return LARGER;
		} else if (Integer.parseInt(monthA) < Integer.parseInt(monthB)) {
			return SMALLER;
		} else if (Integer.parseInt(dayA) > Integer.parseInt(dayB)) {
			return LARGER;
		}
		return SMALLER;
	}

	/**
	 * Method extracts the end date from the String input. If no date is detected, the system's current date is returned.
	 * If more than one date is found, the first date input is replaced, and the second is extracted.
	 * 
	 * @param input String to have the end date extracted from.
	 * @return end date String
	 */
	protected static String extractEndDate(String input) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(input);

		if(!dateMatcher.find()) {
			String date = ParserDateLocal.dateString();
			String newDateFormat = date.substring(0, 2) + "/" + date.substring(2, 4) + "/" + date.substring(4, 6);
			return newDateFormat;
		}

		if(hasTwoDateInputs(input)) {
			input = dateMatcher.replaceFirst("");
		}

		String endDate = extractStartDate(input);

		return endDate;
	}

	/**
	 * Method determines if input String has 2, and only 2, dates. Returns false otherwise.
	 * 
	 * @param input String to be checked.
	 * @return true if there are two date within the input.
	 */
	protected static boolean hasTwoDateInputs(String input) {		
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(input);
		int n = 0;

		while(dateMatcher.find()) {
			input = dateMatcher.replaceFirst("");

			Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(input);
			input = timeMatcher.replaceFirst("");
			dateMatcher = ParserPatternLocal.datePattern.matcher(input);

			n++;
		}

		if(n > 1) {
			assert(n==2);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method extracts the start date from the String input. Method converts date input into the following format:
	 * DD/MM/YY. If date extracted from input is in an invalid format, an empty String is returned.
	 * 
	 * @param input String to have the start date extracted from.
	 * @return start date String.
	 */
	protected static String extractStartDate(String input) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(input);
		String startDate = new String();
		String temp = new String();
		String newDateFormat = new String();


		if(dateMatcher.find()) {
			String group2 = dateMatcher.group(2);
			String group3 = dateMatcher.group(3);

			if(group2.length() !=2) {
				startDate = "0".concat(dateMatcher.group(2));
			} else {
				startDate = dateMatcher.group(2);
			}

			if(group3.length() !=2) {
				temp = "0".concat(dateMatcher.group(3));
				startDate = startDate.concat(temp);
			} else {
				startDate = startDate.concat(dateMatcher.group(3));
			}
			startDate = startDate.concat(dateMatcher.group(4));
		}
		String date = determineDateValidity(startDate);

		if(!date.isEmpty()) {
			if(!date.contains("/")) {
				newDateFormat = date.substring(0, 2) + "/" + date.substring(2, 4) + "/" + date.substring(4, 6);
			}
		}

		return newDateFormat;
	}	

	/**
	 * Method returns the inputDate String if format is valid, empty String otherwise.
	 * 
	 * @param inputDate String to be checked.
	 * @return the inputDate String if format is valid, empty String otherwise.
	 */
	public static String determineDateValidity(String inputDate) { 
		if(!dateFormatValid(inputDate)) {
			return "";
		}
		return inputDate;
	}

/**
 * Method checks if the date format is valid and returns true if so.
 * 
 * @param date String to be checked.
 * @return true if date format is valid.
 */
	public static boolean dateFormatValid(String date) {
		boolean validDateFormat = true;

		if(date.length() != 6) {
			validDateFormat = false;
		} else if(!date.matches("[0-9]+")) {
			validDateFormat = false;
		} else if(!dateExists(Integer.parseInt(date))) {
			validDateFormat = false;
		} 
		return validDateFormat;
	}

	/**
	 * Method checks if date exists.
	 * 
	 * @param date String to be checked.
	 * @return true if date exists.
	 */
	protected static boolean dateExists(int date) {
		boolean leapYear = false;
		boolean dateExist = false;

		int day = date / 10000;
		int month = (date / 100) % 100;
		int year = date % 100;

		if(year % 4 == 0 && year % 100 != 0) {
			leapYear = true;
		}
		if(month > 12 || month < 1) {
			return false;
		}
		if(day < 29) {
			dateExist = true;
		} else if(day == 29 && month == 02 && leapYear) {
			dateExist = true;
		} else if(day <= 30 && month != 2){
			dateExist = true;
		} else if(day <= 31 && month != 2  && month != 4 && month != 6 && month != 9 && month != 11) {
			dateExist = true;
		}
		return dateExist;
	}

	/**
	 * Method returns system's current date.
	 * 
	 * @return system's current date String
	 */
	public static String dateString(){
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		Date todayDate = new Date();

		return dateFormat.format(todayDate);
	}

	/**
	 * Method replaces all dates found.  
	 * 
	 * @param input String to be changed
	 * @return input String with dates removed
	 */
	protected static String replaceAllDate(String input) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(input);

		input = dateMatcher.replaceAll("");

		return input;
	}

	/**
	 * Method to extract tentative dates.
	 * 
	 * @param input String to have tentative dates extracted from.
	 * @return tentative dates Vector.
	 */
	protected static Vector<String> extractTentativeDates(String input) {
		Vector<String> tentativeDates = new Vector<String> ();
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(input);
		String temp = new String();

		while(dateMatcher.find()) {
			tentativeDates.add(extractEndDate(dateMatcher.group()));
			input = dateMatcher.replaceFirst("");

			Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(input);
			input = timeMatcher.replaceFirst("");

			dateMatcher = ParserPatternLocal.datePattern.matcher(input);
		}

		return tentativeDates;
	}
}
