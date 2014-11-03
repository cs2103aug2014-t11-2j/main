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

		String yearA = dateA.trim().substring(4, 8);
		String yearB = dateB.trim().substring(4, 8);

		String monthA = dateA.trim().substring(2, 4);
		String monthB = dateB.trim().substring(2, 4);

		String dayA = dateA.trim().substring(0, 2);
		String dayB = dateB.trim().substring(0, 2);

		yearA = ParserIdLocal.removeFrontZero(yearA);
		yearB = ParserIdLocal.removeFrontZero(yearB);

		monthA = ParserIdLocal.removeFrontZero(monthA);
		monthB = ParserIdLocal.removeFrontZero(monthB);

		dayA = ParserIdLocal.removeFrontZero(dayA);
		dayB = ParserIdLocal.removeFrontZero(dayB);

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
		userInput = ParserIdLocal.removeId(userInput);
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
		userInput = ParserIdLocal.removeId(userInput);
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(userInput);
		String startDate = new String();
		String temp = new String();
		
		if(dateMatcher.find()) {
			
				if(dateMatcher.group(2).length() !=2) {
				startDate = "0".concat(dateMatcher.group(2));
			} else {
				startDate = dateMatcher.group(2);
			}
			
			if(dateMatcher.group(3).length() !=2) {
				temp = "0".concat(dateMatcher.group(3));
				startDate = startDate.concat(temp);
			} else {
				startDate = startDate.concat(dateMatcher.group(3));
			}
			startDate = startDate.concat(dateMatcher.group(4));
		}
		return determineDateValidity(startDate);
	}	
	
	public static String determineDateValidity(String inputDate) { 
        if(!dateFormatValid(inputDate)) {
				return "";
			}
		return inputDate;
	}

	public static boolean dateFormatValid(String date) {
		boolean validDateFormat = true;

		if(date.length() != 8) {
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
		
		int day = date / 1000000;
		int month = (date / 10000) % 100;
		int year = date % 10000;

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
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
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

	


	
	// unused methods in parser?
	// decrementing date
	protected static String updateDate(String date) {

		String[] endDate = new String[3];

		endDate[0] = date.substring(0, 2);
		endDate[1] = date.substring(2, 4);
		endDate[2] = date.substring(4, 8);
		
		int[] intEndDate = new int[3];
		String updatedDate = "";
		
		for(int dateCharCount = 0; dateCharCount < endDate.length; dateCharCount++) {
			intEndDate[dateCharCount] = Integer.parseInt(endDate[dateCharCount]); 
		}

		if ((intEndDate[0] - 1) == 0) {
			if ((intEndDate[1] - 1) == 0) {
				intEndDate[2]--;
				intEndDate[1] = 12;
				intEndDate[0] = 31;
			} else {
				intEndDate[1] = updateMonth(intEndDate[1], intEndDate[2]);
			}
		} else {
			intEndDate[0]--;
		}
		
		for(int dateIntCount = 0; dateIntCount < intEndDate.length; dateIntCount++) {
			updatedDate += String.valueOf(intEndDate[dateIntCount]);
		}
		
		return updatedDate;
	}

	private static int updateMonth(int intEndMonth, int intEndYear) {

		if(intEndMonth == 2){			
			if (intEndYear % 4 == 0){
				return 29;
			} else {
				return 28;
			}				
		} else if(intEndMonth == 4 || intEndMonth == 6 || intEndMonth == 9 || intEndMonth == 11) {
			return 30;
		} 
		return 31;	
	}

	
}
