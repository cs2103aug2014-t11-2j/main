package storage;


public class AssignValidCheck {
	
	private static boolean checkValid = false;
	private static boolean checkType = false;
	private static boolean checkIndex = false;
	private static boolean checkDateCreation = false;
	private static boolean checkPriority = false;
	private static boolean checkEndDate = false;
	private static boolean checkEndTime = false;
	private static boolean checkStartDate = false;
	private static boolean checkStartTime = false;
	
	protected static boolean checkAssignment(String[] line) {

		checkDateCreation = ValidityCheck.dateFormatValid(line[0]);
		if(line[1].matches("\\d+")) {
			if(Integer.parseInt(line[1]) > 0) {
				checkIndex = true;
			}
		}
		checkType = ValidityCheck.validType(line[3]);
		checkPriority = ValidityCheck.priorityChecker(line[6]);

		if(checkType && checkDateCreation && checkIndex && checkPriority) {
			checkValid = true;
		}
		return checkValid;
	}

	protected static boolean checkTask(String[] line){
		
		checkDateCreation = ValidityCheck.dateFormatValid(line[0]);
		if(line[1].matches("\\d+")) {
			if(Integer.parseInt(line[1]) > 0) {
				checkIndex = true;
			}
		}
		checkType = ValidityCheck.validType(line[3]);
		checkEndDate = ValidityCheck.dateFormatValid(line[4]);
		checkEndTime = ValidityCheck.timeFormatValid(line[5]);
		checkPriority = ValidityCheck.priorityChecker(line[8]);
		
		if(checkType && checkDateCreation && checkIndex && checkPriority && checkEndDate &&
				checkEndTime) {
			checkValid = true;
		}
		return checkValid;
	}

	protected static boolean checkAppointment(String[] line) {
		
		checkDateCreation = ValidityCheck.dateFormatValid(line[0]);
		if(line[1].matches("\\d+")) {
			if(Integer.parseInt(line[1]) > 0) {
				checkIndex = true;
			}
		}
		checkType = ValidityCheck.validType(line[3]);
		checkEndDate = ValidityCheck.dateFormatValid(line[6]);
		checkEndTime = ValidityCheck.timeFormatValid(line[7]);
		checkStartDate = ValidityCheck.dateFormatValid(line[4]);
		checkStartTime = ValidityCheck.timeFormatValid(line[5]);
		checkPriority = ValidityCheck.priorityChecker(line[10]);
		
		if(checkType && checkDateCreation && checkIndex && checkPriority
				&& checkEndDate && checkEndTime && checkStartDate && checkStartTime) {
			checkValid = true;
		}
		return checkValid;
	}
	
	protected static boolean checkTentative(String[] line) {
		
		checkDateCreation = ValidityCheck.dateFormatValid(line[0]);
		if(line[1].matches("\\d+")) {
			if(Integer.parseInt(line[1]) > 0) {
				checkIndex = true;
			}
		}
		checkType = ValidityCheck.validType(line[3]);
		checkPriority = ValidityCheck.priorityChecker(line[10]);
		checkStartDate = ValidityCheck.checkDates(line[4]);
		checkStartTime = ValidityCheck.checkTimes(line[5]);
		checkEndDate = ValidityCheck.checkDates(line[6]);
		checkEndTime = ValidityCheck.checkTimes(line[7]);
		
		if(checkType && checkDateCreation && checkIndex && checkPriority
				&& checkEndDate && checkEndTime && checkStartDate && checkStartTime) {
			checkValid = true;
		}
		return checkValid;
	}
	

}
