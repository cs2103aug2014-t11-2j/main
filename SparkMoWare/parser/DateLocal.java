package parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
 * Prompt the user for a valid date
 * Check for date validity with the format <ddmmyyyy>
 * and
 * Check if the given date exists
 */

public class DateLocal {
	
	private static Scanner scanner = new Scanner(System.in);
	
	protected static String determineDate(String inputDate) {
		
		// will continuously prompt user for correct date format currently no way to exit
		while(!dateFormatValid(inputDate)) {
			
			Message.printToUser(Message.INVALID_FORMAT);
			Message.printToUser(String.format(Message.FORMAT_PROMPT, "date"));
			inputDate = scanner.nextLine();
		}
		return inputDate;
	}

	protected static boolean dateFormatValid(String date) {
		
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
