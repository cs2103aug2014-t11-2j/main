package parser;

//@author A0110788B
public class InputIsConfirm {
	
	/**
	 * Method creates a RefinedUserInput for the confirm command. If the input does not contain an index
	 * start date, start time, end date and end time, or any are in the incorrect format, an Invalid
	 * Format CommandType is returned.
	 * 
	 * @param userInput the String with all the relevant information to be extracted.
	 * @return a RefinedUserInput object for the confirm command.
	 */
	protected static RefinedUserInput refineInput(String userInput) {
		
		RefinedUserInput inputConfirm = new RefinedUserInput();
		int index = ParserIndexLocal.extractIndex(userInput, "confirm");
		String startDate = ParserDateLocal.extractStartDate(userInput);
		String startTime = ParserTimeLocal.extractStartTime(userInput);
		String endDate = ParserDateLocal.extractEndDate(userInput);
		String endTime = ParserTimeLocal.extractEndTime(userInput);
		
		if(index == -1 || startDate.isEmpty() || startTime.isEmpty() || endDate.isEmpty() || endTime.isEmpty()) {
			inputConfirm.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputConfirm;
		}
		
		inputConfirm.setCommandType(EnumGroup.CommandType.CONFIRM);
		inputConfirm.setIndex(index);
		inputConfirm.setStartDate(startDate);
		inputConfirm.setStartTime(startTime);
		inputConfirm.setEndDate(endDate);
		inputConfirm.setEndTime(endTime);
		
		return inputConfirm;
	}
}
