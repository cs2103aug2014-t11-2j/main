package storage;

public class ValidityCheck {

	private static final int SERIAL_LENGTH = 12;
	private static final boolean DEFAULT_NONE = false;
	
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
}