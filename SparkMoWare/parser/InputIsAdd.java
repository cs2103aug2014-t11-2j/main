package parser;

public class InputIsAdd {

	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputAdd = new RefinedUserInput();
		String title = Misc.extractTitle(userInput, "add");
		String priority = Misc.extractPriority(userInput);
		
		if(Misc.isFloatingAssignment(userInput)) {
			inputAdd.setCommandType(EnumGroup.CommandType.ADD);
			inputAdd.setTitle(title);
			inputAdd.setPriority(priority);
			inputAdd.setAssignmentType(EnumGroup.AssignmentType.ASSIGNMENT);
			return inputAdd;
		}
		
		String endDate = ParserDateLocal.extractEndDate(userInput);
		String endTime = ParserTimeLocal.extractEndTime(userInput);
		
		if(title.isEmpty() || endDate.isEmpty() || endTime.isEmpty()) {
			inputAdd.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputAdd;
		}

		if(ParserDateLocal.hasTwoDateInputs(userInput)){			
			String startDate = ParserDateLocal.extractStartDate(userInput);
			String startTime = ParserTimeLocal.extractStartTime(userInput);
			
			if(startDate.isEmpty() || startTime.isEmpty()) {
				inputAdd.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputAdd;
			}

			inputAdd.setCommandType(EnumGroup.CommandType.ADD);
			inputAdd.setTitle(title);
			inputAdd.setStartDate(startDate);
			inputAdd.setStartTime(startTime);
			inputAdd.setEndDate(endDate);
			inputAdd.setEndTime(endTime);
			inputAdd.setPriority(priority);
			inputAdd.setAssignmentType(EnumGroup.AssignmentType.APPOINTMENT);

		} else {
			
			inputAdd.setCommandType(EnumGroup.CommandType.ADD);
			inputAdd.setTitle(title);
			inputAdd.setEndDate(endDate);
			inputAdd.setEndTime(endTime);
			inputAdd.setPriority(priority);
			inputAdd.setAssignmentType(EnumGroup.AssignmentType.TASK);
		}

		return inputAdd;
	}
}