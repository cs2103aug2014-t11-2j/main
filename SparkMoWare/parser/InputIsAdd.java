package parser;

public class InputIsAdd {

	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputAdd = new RefinedUserInput();
		String title = Misc.extractTitle(userInput);

		if(title.isEmpty()) {
			return inputAdd;
		}

		String endDate = ParserDateLocal.extractEndDate(userInput);		

		if(endDate.isEmpty()) {
			return inputAdd;
		}

		String endTime = ParserTimeLocal.extractEndTime(userInput);

		if(endTime.isEmpty()) {
			return inputAdd;
		}

		if(ParserDateLocal.hasTwoDateInputs(userInput)){			
			String startDate = ParserDateLocal.extractStartDate(userInput);

			if(startDate.isEmpty()) {
				return inputAdd;
			}
			
			String startTime = ParserTimeLocal.extractStartTime(userInput);
			
			if(startTime.isEmpty()) {
				return inputAdd;
			}

			inputAdd.setCommandType(EnumGroup.CommandType.ADD);
			inputAdd.setTitle(title);
			inputAdd.setStartDate(startDate);
			inputAdd.setStartTime(startTime);
			inputAdd.setEndDate(endDate);
			inputAdd.setEndTime(endTime);
			inputAdd.setAssignmentType(EnumGroup.AssignmentType.APPOINTMENT);

		} else {
			
			inputAdd.setCommandType(EnumGroup.CommandType.ADD);
			inputAdd.setTitle(title);
			inputAdd.setEndDate(endDate);
			inputAdd.setEndTime(endTime);
			inputAdd.setAssignmentType(EnumGroup.AssignmentType.APPOINTMENT);
		}
		
		return inputAdd;
	}
}