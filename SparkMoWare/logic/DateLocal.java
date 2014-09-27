package logic;

<<<<<<< HEAD
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.GregorianCalendar;

=======
/*
 * Prompt the user for a valid date
 * Check for date validity with the format <ddmmyyyy>
 * and
 * Check if the given date exists
 */
>>>>>>> dee22b8d549482c1fa434bc42f335a0f22c08a5f
public class DateLocal {
	
	protected static String determineDate(String inputDate) {
		
		while(!dateFormatValid(inputDate)) {//will continuously prompt user for correct date format currently no way to exit
			
			Message.printToUser(Message.INVALID_FORMAT);
			Message.printToUser(String.format(Message.FORMAT_PROMPT, "date"));
			inputDate = SparkMoVare.scanner.nextLine();
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

	protected static String dateString(){
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		Date todayDate = new Date();
		
		return dateFormat.format(todayDate);
	}
	
}
