package parser;

import logic.Assignment.AssignmentType;

//@author A0110788B
//@author A0117057J (exception handling)
public class InputIsAdd {

	/**
	 * Method creates a RefinedUserInput for the add command. The cases are as follow:
	 * <br><br>
	 * 1. Floating Assignment: no date and time are detected in the input. If the title is empty, an Invalid
	 * Format CommandType is returned. 
	 * <br><br>
	 * 2. Assignment: A single date and/or time input is detected in the input. If the input date and/or time
	 * is in an invalid format, an Invalid Format CommandType is returned.
	 * <br><br>
	 * 3. Appointment: Two date inputs are detected in the input. If any of the input dates and/or times are
	 * in an invalid format, an Invalid Format CommandType is returned. 
	 * 
	 * @param userInput the String with all the relevant information to be extracted.
	 * @return a RefinedUserInput object for the add command.
	 */
	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputAdd = new RefinedUserInput();
		String title = Misc.extractTitle(userInput, "add");
		String endDate = ParserDateLocal.extractEndDate(userInput);
		String endTime = ParserTimeLocal.extractEndTime(userInput);
		String priority = Misc.extractPriority(userInput);
		Exception exception = null;
		
		try {
			if (title.isEmpty()) {
				throw exception;
			}

			if (Misc.isFloatingAssignment(userInput)) {
				inputAdd.setCommandType(EnumGroup.CommandType.ADD);
				inputAdd.setTitle(title);
				inputAdd.setPriority(priority);
				inputAdd.setAssignmentType(AssignmentType.ASGN);
				return inputAdd;
			}

			if (endDate.isEmpty() || endTime.isEmpty()) {
				throw exception;
			}

			if (ParserDateLocal.hasTwoDateInputs(userInput)) {
				String startDate = ParserDateLocal.extractStartDate(userInput);
				String startTime = ParserTimeLocal.extractStartTime(userInput);

				if (startDate.isEmpty() || startTime.isEmpty()) {
					throw exception;
				}

				inputAdd.setStartTime(startTime);
				inputAdd.setEndTime(endTime);

				if (ParserDateLocal.dateComparator(startDate, endDate) == -1) {
					inputAdd.setStartDate(startDate);
					inputAdd.setEndDate(endDate);

				} else if (ParserDateLocal.dateComparator(startDate, endDate) == 1) {
					inputAdd.setStartDate(endDate);
					inputAdd.setEndDate(startDate);

				} else {

					inputAdd.setStartDate(startDate);
					inputAdd.setEndDate(endDate);

					if (ParserTimeLocal.timeComparator(startTime, endTime) == -1) {
						inputAdd.setStartTime(startTime);
						inputAdd.setEndTime(endTime);

					} else if (ParserTimeLocal.timeComparator(startTime,
							endTime) == 1) {
						inputAdd.setStartTime(endTime);
						inputAdd.setEndTime(startTime);

					} else {
						throw exception;
					}
				}

				inputAdd.setCommandType(EnumGroup.CommandType.ADD);
				inputAdd.setTitle(title);
				inputAdd.setPriority(priority);
				inputAdd.setAssignmentType(AssignmentType.APPT);

			} else {

				inputAdd.setCommandType(EnumGroup.CommandType.ADD);
				inputAdd.setTitle(title);
				inputAdd.setEndDate(endDate);
				inputAdd.setEndTime(endTime);
				inputAdd.setPriority(priority);
				inputAdd.setAssignmentType(AssignmentType.TASK);
			}
		} catch (Exception e) {
			inputAdd.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputAdd;
		}

		return inputAdd;
	}
}