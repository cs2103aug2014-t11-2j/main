package storage;

//@author A0111572R

public class AssignValidCheck {
	
	private static boolean checkValid = false;
	private static boolean checkType = false;
	private static boolean checkIndex = false;
	private static boolean checkPriority = false;
	private static boolean checkEndDate = false;
	private static boolean checkEndTime = false;
	private static boolean checkStartDate = false;
	private static boolean checkStartTime = false;
	
	protected static boolean checkAssignment(String[] line) {

		checkIndex = ValidityCheck.indexFormat(line[0]);
		checkType = ValidityCheck.validType(line[1]);
		checkPriority = ValidityCheck.priorityChecker(line[5]);

		if(checkType && checkIndex && checkPriority) {
			checkValid = true;
		}
		return checkValid;
	}

	protected static boolean checkTask(String[] line){
		
		checkIndex = ValidityCheck.indexFormat(line[0]);
		checkType = ValidityCheck.validType(line[1]);
		checkEndDate = ValidityCheck.dateFormatValid(line[3]);
		checkEndTime = ValidityCheck.timeFormatValid(line[4]);
		checkPriority = ValidityCheck.priorityChecker(line[7]);
		
		if(checkType && checkIndex && checkPriority && checkEndDate &&
				checkEndTime) {
			checkValid = true;
		}
		return checkValid;
	}

	protected static boolean checkAppointment(String[] line) {
		
		checkIndex = ValidityCheck.indexFormat(line[0]);
		checkType = ValidityCheck.validType(line[1]);
		checkEndDate = ValidityCheck.dateFormatValid(line[5]);
		checkEndTime = ValidityCheck.timeFormatValid(line[6]);
		checkStartDate = ValidityCheck.dateFormatValid(line[3]);
		checkStartTime = ValidityCheck.timeFormatValid(line[4]);
		checkPriority = ValidityCheck.priorityChecker(line[9]);
		
		if(checkType && checkIndex && checkPriority
				&& checkEndDate && checkEndTime && checkStartDate && checkStartTime) {
			checkValid = true;
		}
		return checkValid;
	}
	
	protected static boolean checkTentative(String[] line) {
		
		checkIndex = ValidityCheck.indexFormat(line[0]);
		checkType = ValidityCheck.validType(line[1]);
		checkPriority = ValidityCheck.priorityChecker(line[9]);
		checkStartDate = ValidityCheck.checkDates(line[3]);
		checkStartTime = ValidityCheck.checkTimes(line[4]);
		checkEndDate = ValidityCheck.checkDates(line[5]);
		checkEndTime = ValidityCheck.checkTimes(line[6]);
		
		if(checkType && checkIndex && checkPriority	&& checkEndDate && checkEndTime
				&& checkStartDate && checkStartTime) {
			checkValid = true;
		}
		return checkValid;
	}
}