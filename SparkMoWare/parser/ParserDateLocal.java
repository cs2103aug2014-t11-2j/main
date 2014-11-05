package parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;

public class ParserDateLocal {

	private static final int SMALLER = -1;
	private static final int SAME = 0;
	private static final int LARGER = 1;

	// Comparison for Date.
	// -1 (A < B), 0 (A = B), 1 (A > B) 
	protected static int dateComparator(String dateA, String dateB) {

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

	protected static String extractEndDate(String userInput) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(userInput);

		if(!dateMatcher.find()) {
			return ParserDateLocal.dateString();
		}

		if(hasTwoDateInputs(userInput)) {
			userInput = dateMatcher.replaceFirst("");
		}

		String endDate = extractStartDate(userInput);

		return endDate;
	}

	//Checks if there are 2 date inputs
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

	protected static String extractStartDate(String userInput) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(userInput);
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
			newDateFormat = date.substring(0, 2) + "/" + date.substring(2, 4) + "/" + date.substring(4, 6);	
		}
		
		return newDateFormat;
	}	

	public static String determineDateValidity(String inputDate) { 
		if(!dateFormatValid(inputDate)) {
			return "";
		}
		return inputDate;
	}

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

	protected static boolean dateExists(int date) {
		boolean leapYear = false;
		boolean dateExist = false;

		int day = date / 10000;
		int month = (date / 100) % 100;
		int year = date % 100;

		if(year % 4 == 0) {
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

	public static String dateString(){
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		Date todayDate = new Date();

		return dateFormat.format(todayDate);
	}

	protected static String replaceAllDate(String input) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(input);

		input = dateMatcher.replaceAll("");

		return input;
	}

	protected static Vector<String> extractTentativeDates(String input) {
		Vector<String> tentativeDates = new Vector<String> ();
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(input);
		String temp = new String();

		while(dateMatcher.find()) {
			//temp = extractEndDate(dateMatcher.group());
			tentativeDates.add(extractEndDate(dateMatcher.group()));
			input = dateMatcher.replaceFirst("");

			Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(input);
			input = timeMatcher.replaceFirst("");

			dateMatcher = ParserPatternLocal.datePattern.matcher(input);
		}

		return tentativeDates;
	}
}
