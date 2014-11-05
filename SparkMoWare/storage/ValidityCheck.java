package storage;


public class ValidityCheck {

	private static final boolean DEFAULT_NONE = false;

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

	protected static boolean indexFormat(String index) {

		boolean checkIndex = false;

		if(index.matches("\\d+")) {
			if(Integer.parseInt(index) > 0) {
				checkIndex = true;
			}
		}
		return checkIndex;
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

	protected static boolean checkDates(String dates) {

		boolean checkDates = false;

		if(!dates.contains("[") && !dates.contains("]")) {
			return checkDates;
			
		} else {
			String[] datesSlot = dates.split(",");
			int count = 0;

			for(int i = 0; i < datesSlot.length; i++) {
				datesSlot[i].trim();
			}
			if(datesSlot.length > 1) {
				datesSlot[0] = datesSlot[0].substring(1, 9);
				datesSlot[datesSlot.length - 1] = datesSlot[datesSlot.length - 1].substring(1, 9);

				while(checkDates && count < datesSlot.length) {
					checkDates = dateFormatValid(datesSlot[count]);
					count++;
				}
			} else {
				datesSlot[0] = datesSlot[0].substring(1,9);
				checkDates = dateFormatValid(datesSlot[0]);
			}
			return checkDates;
		}
	}

	protected static boolean checkTimes(String times) {

		boolean checkTimes = false;
		
		if(!times.contains("[") && !times.contains("]")) {
			return checkTimes;
			
		} else {
			String[] timesSlot = times.split(",");
			int count = 0;

			for(int i = 0; i < timesSlot.length; i++) {
				timesSlot[i].trim();
			}
			if(timesSlot.length > 1) {
				timesSlot[0] = timesSlot[0].substring(1, 5);
				timesSlot[timesSlot.length - 1] = timesSlot[timesSlot.length - 1].substring(1, 5);

				while( checkTimes && count < timesSlot.length){
					checkTimes = timeFormatValid(timesSlot[count]);
					count++;
				}
			} else {
				timesSlot[0] = timesSlot[0].substring(1, 5);
				checkTimes = timeFormatValid(timesSlot[0]);
			}
			return checkTimes;
		}
	}
}