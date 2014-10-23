package storage;


public class AssignValidCheck {
	
	private static boolean checkValid = false;
	private static boolean checkType = false;
	private static boolean checkId = false;
	private static boolean checkPriority = false;
	private static boolean checkEndDate = false;
	private static boolean checkEndTime = false;
	private static boolean checkStartDate = false;
	private static boolean checkStartTime = false;
	
	protected static boolean checkAssignment(String[] line) {

		checkId = ValidityCheck._IDFormatValid(line[0]);
		checkType = ValidityCheck.validType(line[2]);
		checkPriority = ValidityCheck.priorityChecker(line[5]);

		if(checkType && checkId && checkPriority) {
			checkValid = true;
		}
		return checkValid;
	}

	protected static boolean checkTask(String[] line){
		
		checkId = ValidityCheck._IDFormatValid(line[0]);
		checkType = ValidityCheck.validType(line[2]);
		checkPriority = ValidityCheck.priorityChecker(line[7]);
		checkEndDate = ValidityCheck.dateFormatValid(line[3]);
		checkEndTime = ValidityCheck.timeFormatValid(line[4]);
		
		if(checkType && checkId && checkPriority && checkEndDate &&
				checkEndTime) {
			checkValid = true;
		}
		return checkValid;
	}

	protected static boolean checkAppointment(String[] line) {
		
		checkId = ValidityCheck._IDFormatValid(line[0]);
		checkType = ValidityCheck.validType(line[2]);
		checkPriority = ValidityCheck.priorityChecker(line[7]);
		checkEndDate = ValidityCheck.dateFormatValid(line[5]);
		checkEndTime = ValidityCheck.timeFormatValid(line[6]);
		checkStartDate = ValidityCheck.dateFormatValid(line[3]);
		checkStartTime = ValidityCheck.timeFormatValid(line[4]);
		
		if(checkType && checkId && checkPriority && checkEndDate &&
				checkEndTime && checkStartDate && checkStartTime) {
			checkValid = true;
		}
		return checkValid;
	}
}
