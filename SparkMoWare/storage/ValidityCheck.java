package storage;


public class ValidityCheck {

	private static final boolean DEFAULT_NONE = false;
	private static final int SMALLER = -1;
	private static final int SAME = 0;
	private static final int LARGER = 1;
	
	protected static boolean validType(String assignType) {
		
		boolean typeChecked = DEFAULT_NONE;
		
		if(assignType.equalsIgnoreCase("task")) {
			typeChecked = true;
		} else if(assignType.equalsIgnoreCase("appt")) {
			typeChecked = true;
		} else if(assignType.equalsIgnoreCase("asgn")) {
			typeChecked = true;
		} else if(assignType.equalsIgnoreCase("tntv")) {
			typeChecked = true;
		}
		return typeChecked;
	}

	protected static boolean dateFormatValid(String date) {

		boolean validDateFormat = true;

		if (date.length() != 8) {
			validDateFormat = false;
		} else if (!date.matches("[0-9]+")) {
			validDateFormat = false;
		} else if (!dateExists(Integer.parseInt(date))) {
			validDateFormat = false;
		}
		return validDateFormat;
	}

	private static boolean dateExists(int date) {

		boolean leapYear = false;
		boolean dateExist = false;

		int day = date / 1000000;
		int month = (date / 10000) % 100;
		int year = date % 10000;

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

	protected static boolean timeFormatValid(String time) {

		boolean timeValidity = true;

		if (time.length() != 4) {
			timeValidity = false;
		} else if (!time.matches("[0-9]+")) {
			timeValidity = false;
		} else if (!timeExists(Integer.parseInt(time))) {
			timeValidity = false;
		}
		return timeValidity;
	}

	private static boolean timeExists(int time) {

		boolean timeExist = false;

		int min = time % 100;
		int hr = time / 100;

		if (min <= 59 && min >= 0) {
			timeExist = true;
		} else if (hr <= 23 && hr >= 0) {
			timeExist = true;
		}
		return timeExist;
	}
	
	protected static boolean priorityChecker(String priority) {
		
		boolean priorityChecked = false;
		
		if (priority.equalsIgnoreCase("NIMPT") || priority.equalsIgnoreCase("IMPT")) {
			priorityChecked = true;
		}
		return priorityChecked;
	}
	
	protected static boolean serialNumberComparator(String idA, String idB) {

		int checkDate = dateComparator(idA.substring(0, 8), idB.substring(0, 8));
		boolean serialCheck = false;

		if (checkDate == SAME) {
			// check Sn
			idA = removeFrontZero(idA.substring(8));

			idB = removeFrontZero(idB.substring(8));

			if (Integer.parseInt(idA) > Integer.parseInt(idB)) {
				serialCheck = true;
			}
		} else if (checkDate == LARGER) {
			serialCheck = true;
		}
		return serialCheck;
	}
	
	private static int dateComparator(String dateA, String dateB) {

		String yearA = dateA.trim().substring(4, 8);
		String yearB = dateB.trim().substring(4, 8);

		String monthA = dateA.trim().substring(2, 4);
		String monthB = dateB.trim().substring(2, 4);

		String dayA = dateA.trim().substring(0, 2);
		String dayB = dateB.trim().substring(0, 2);

		yearA = removeFrontZero(yearA);
		yearB = removeFrontZero(yearB);

		monthA = removeFrontZero(monthA);
		monthB = removeFrontZero(monthB);

		dayA = removeFrontZero(dayA);
		dayB = removeFrontZero(dayB);

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
	
	private static String removeFrontZero(String input) {
		
		while (input.length() > 0 && input.charAt(0) == '0') {
			input = input.substring(1);
		}
		return input;
	}
	
	protected static boolean checkDates(String dates) {
		
		boolean checkDates = false;
		String[] datesSlot = dates.split(",");
		int count = 0;
		
		for(int i = 0; i < datesSlot.length; i++) {
			datesSlot[i].trim();
		}
		
		datesSlot[0] = datesSlot[0].substring(1, 8);
		datesSlot[datesSlot.length - 1] = datesSlot[datesSlot.length - 1].substring(0, 8);
		
		do {
			checkDates = dateFormatValid(datesSlot[count]);
			count++;
		} while(checkDates || count == datesSlot.length);
		
		return checkDates; 
	}
	
	protected static boolean checkTimes(String times) {
		
		boolean checkTimes = false;
		String[] timesSlot = times.split(",");
		int count = 0;
		
		for(int i = 0; i < timesSlot.length; i++) {
			timesSlot[i].trim();
		}
		
		timesSlot[0] = timesSlot[0].substring(1, 5);
		timesSlot[timesSlot.length - 1] = timesSlot[timesSlot.length - 1].substring(0, 4);
		
		do {
			checkTimes = dateFormatValid(timesSlot[count]);
			count++;
		} while(checkTimes || count == timesSlot.length);
		 
		return checkTimes;
	}
}