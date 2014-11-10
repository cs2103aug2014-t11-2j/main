package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import logic.Assignment.AssignmentType;

//@author A0117057J

public class DateLocal {

	protected static String dateString(){

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		Date todayDate = new Date();

		return dateFormat.format(todayDate);
	}

	protected static String updateDate(String date) {

		String[] endDate = date.split("/");

		int[] intEndDate = new int[3];

		intEndDate = dateConversionToInt(endDate);
		
		if ((intEndDate[0] - 1) == 0) {
			if ((intEndDate[1] - 1) == 0) {
				intEndDate[2]--;
				intEndDate[1] = 12;
				intEndDate[0] = 31;

			} else {
				intEndDate[0] = updateByMonth(intEndDate[1], intEndDate[2]);
				intEndDate[1]--;
			}
		} else { 
			intEndDate[0]--;
		}
		return convertDateBack(intEndDate);
	}
	
	private static int[] dateConversionToInt(String[] endDate) {
		int[] intEndDate = new int[3];
		
		for(int dateCharCount = 0; dateCharCount < endDate.length; dateCharCount++) {
			endDate[dateCharCount] = Comparator.removeFrontZero(endDate[dateCharCount]);
			intEndDate[dateCharCount] = Integer.parseInt(endDate[dateCharCount]); 
		}
		return intEndDate;
	}
	
	private static String convertDateBack(int[] intEndDate) {
		
		String updatedDate = "";
		
		for(int dateIntCount = 0; dateIntCount < intEndDate.length; dateIntCount++) {

			String newDate;

			if(intEndDate[dateIntCount] < 10) {
				newDate = "0" + Integer.toString(intEndDate[dateIntCount]);
			} else {
				newDate = Integer.toString(intEndDate[dateIntCount]);
			}

			if(!(dateIntCount == intEndDate.length - 1)) {
				newDate += "/";
			}
			updatedDate += newDate;
		}
		return updatedDate;
	}
	
	private static int updateByMonth(int intEndMonth, int intEndYear) {

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

	protected static String getStartDate() {

		String startDate = "01/01/01";

		ListIterator<Assignment> listIterate = InternalStorage.getBuffer().listIterator();

		while(listIterate.hasNext()) {
			Assignment assignment = listIterate.next();

			startDate = firstDate(assignment);
		}
		return startDate;
	}
	
	private static String firstDate(Assignment assignment) {
		
		String startDate = "01/01/01";
		
		if(assignment.equals(AssignmentType.TASK)) {
			Task firstTask = ((Task) assignment);
			startDate = firstTask.getEndDate();
			
		} else if(assignment.equals(AssignmentType.APPT)) {
			Appointment firstAppointment = ((Appointment) assignment);
			startDate = firstAppointment.getStartDate();
		}
		return startDate;
	}

	protected static boolean dateFormatValid(String date) {

		boolean validDateFormat = true;

		if (date.length() != 8) {
			validDateFormat = false;
		} else if (!date.contains("/")) {
			validDateFormat = false;
		} else if (!dateExists(date)) {
			validDateFormat = false;
		}
		return validDateFormat;
	}

	private static boolean dateExists(String date) {

		int day = dateConversion(date, 0, 2);
		int month = dateConversion(date, 3, 5);
		int year = dateConversion(date, 6, 8);

		return checkDateExist(day, month, year);
	}

	private static int dateConversion(String date, int lower, int higher) {
		
		int number;

		String dateString = date.substring(lower, higher);
		number = Integer.parseInt(dateString);

		return number;
	}

	private static boolean checkDateExist(int day, int month, int year) {

		boolean leapYear = false;
		boolean dateExist = false;

		if (year % 4 == 0) {
			leapYear = true;
		}
		if (month > 12 || month < 1) {
			dateExist = false;
		}
		if (day < 29) {
			dateExist = true;
		} else if (day == 29 && month == 02 && leapYear) {
			dateExist = true;
		} else if (day <= 30 && month != 2) {
			dateExist = true;
		} else if (day <= 31 && month != 2 && month != 4 && month != 6
				&& month != 9 && month != 11) {
			dateExist = true;
		}
		return dateExist;
	}
}