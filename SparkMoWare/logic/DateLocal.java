package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import logic.Assignment.AssignmentType;

public class DateLocal {
	
	protected static String dateString(){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		Date todayDate = new Date();
		
		return dateFormat.format(todayDate);
	}
	
	protected static String updateDate(String date) {

		String[] endDate = new String[3];

		endDate[0] = date.substring(0, 2);
		endDate[1] = date.substring(3, 5);
		endDate[2] = date.substring(6, 8);
		
		int[] intEndDate = new int[3];
		String updatedDate = "";
		
		for(int dateCharCount = 0; dateCharCount < endDate.length; dateCharCount++) {
			endDate[dateCharCount] = Comparator.removeFrontZero(endDate[dateCharCount]);
			intEndDate[dateCharCount] = Integer.parseInt(endDate[dateCharCount]); 
		}
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
		
		String startDate = "31/12/2013";
		
		if(InternalStorage.getBuffer().getFirst().equals(AssignmentType.TASK)) {
			Task firstTask = ((Task) InternalStorage.getBuffer().getFirst());
			startDate = firstTask.getEndDate();
		} else if(InternalStorage.getBuffer().getFirst().equals(AssignmentType.APPT)) {
			
			Appointment firstAppointment = ((Appointment) InternalStorage.getBuffer().getFirst());
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

		boolean leapYear = false;
		boolean dateExist = false;

		String dayString = date.substring(0, 2);
		String monthString = date.substring(3, 5);
		String yearString = date.substring(6, 8);

		int day = Integer.parseInt(dayString);
		int month = Integer.parseInt(monthString);
		int year = Integer.parseInt(yearString);
		
		if (year % 4 == 0) {
			leapYear = true;
		}
		if (month > 12 || month < 1) {
			return false;
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