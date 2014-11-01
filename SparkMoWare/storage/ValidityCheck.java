package storage;


public class ValidityCheck {

	private static final int SERIAL_LENGTH = 12;
	private static final boolean DEFAULT_NONE = false;
	private static final int SMALLER = -1;
	private static final int SAME = 0;
	private static final int LARGER = 1;
	
	protected static boolean validType(String assignType) {
		
		boolean typeChecked = DEFAULT_NONE;
		
		if(assignType.equalsIgnoreCase("task")) {
			typeChecked = true;
		} else if(assignType.equalsIgnoreCase("appointment")) {
			typeChecked = true;
		} else if(assignType.equalsIgnoreCase("assignment")) {
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
	
	protected static boolean _IDFormatValid(String id) {

		boolean idFormatValidity = true;

		if(id.length() != SERIAL_LENGTH) {
			idFormatValidity = false;
		} 
		/* else if(id.equalsIgnoreCase("exit")){ //Method for dealing with fatal error
			return true;
		}*/
		else if(id.matches("\\d+")) {
			
			if(!_IDExists(id)) {
				return false;
			}
		}
		return idFormatValidity;
	}

	private static boolean _IDExists(String id) {
		
		if(dateFormatValid(id.substring(0, 8))) {
			return true;
		} else{
			return false;
		}
	}
	
	protected static boolean priorityChecker(String priority) {
		boolean priorityChecked = false;
		
		if (priority.equalsIgnoreCase("NIMPT") || priority.equalsIgnoreCase("IMPT")) {
			priorityChecked = true;
		}
		return priorityChecked;
	}
	
	protected static boolean serialNumberComparator(String idA, String idB) {

		assert(idA.length() == 12 && idA.length() == 12);

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
}