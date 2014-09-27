package logic;

import java.util.Scanner;

public class DateLocal {
	
	protected static Scanner scanner = new Scanner(System.in);
	
	protected static String determineDate(String inputDate) {
		
		while(!dateFormatValid(inputDate)) {//will continuously prompt user for correct date format currently no way to exit
			
			Message.printToUser(Message.INVALID_FORMAT);
			Message.printToUser(String.format(Message.FORMAT_PROMPT, "date"));
			inputDate = scanner.nextLine();
		}
		return inputDate;
	}

	protected static boolean dateFormatValid(String date) {
		
		if(date.length() != 6) {
			return false;
		} else if(date.matches(".*\\D+.*")) { //not sure if this checks if there are any chars in the input
			return false;
		} else if(!dateExists(Integer.parseInt(date))) {
			return false;
		} else {
			return true;
		}
	}
	
	// date format is in ddmmyyyy
	protected static boolean dateExists(int date) {
		
		boolean leapYear = false;
		
		int day = date / 10000;
		int month = (date % 10000) / 100;
		int year = date % 100;

		if(year % 4 == 0) {
			leapYear = true;
		}
		if(month > 12 || month < 1) {
			return false;
		}

		if(day < 29) {
			return true;
		} else if(day == 29 && month == 02 && leapYear) {
			return true;
		} else if(day == 30 && month != 2){
			return true;
		} else if(day == 31 && month != 2  && month != 4 && month != 6 && month != 9 && month != 11) {
			return true;
		}
		return false;	
	}
}
