package parser;

public class InputIsTentative {

	protected static RefinedUserInput refineInput(String userInput) { 
		RefinedUserInput inputTentative = new RefinedUserInput();
		
		if(ParserDateLocal.hasTwoDateInputs(userInput)){
			return onlyOneTentative(userInput);
		}
		
		String specialContent  = ExtractSpecialContent.forTentative(userInput);

		if(specialContent.isEmpty()) {
			inputTentative.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputTentative;
		}
		
		inputTentative.setCommandType(EnumGroup.CommandType.TENTATIVE);
		inputTentative.setSpecialContent(specialContent);
		
		return inputTentative;

	}
	
	protected static RefinedUserInput onlyOneTentative(String userInput) {
		RefinedUserInput inputTentative = new RefinedUserInput();
		String title = Misc.extractTitle(userInput, "tentative");
		String startDate = ParserDateLocal.extractStartDate(userInput);
		String startTime = ParserTimeLocal.extractStartTime(userInput);
		String endDate = ParserDateLocal.extractEndDate(userInput);
		String endTime = ParserTimeLocal.extractEndTime(userInput);
		String priority = Misc.extractPriority(userInput);

		if(title.isEmpty() || startDate.isEmpty() || startTime.isEmpty() || endDate.isEmpty() || endTime.isEmpty()) {
			inputTentative.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputTentative;
		}
		
		inputTentative.setCommandType(EnumGroup.CommandType.ADD);
		inputTentative.setTitle(title);
		inputTentative.setStartDate(startDate);
		inputTentative.setStartTime(startTime);
		inputTentative.setEndDate(endDate);
		inputTentative.setEndTime(endTime);
		inputTentative.setPriority(priority);
		inputTentative.setAssignmentType(EnumGroup.AssignmentType.APPOINTMENT);

		return inputTentative;
	}
}
