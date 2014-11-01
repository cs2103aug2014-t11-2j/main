package parser;

public class InputIsConfirm {
	
	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputConfirm = new RefinedUserInput();
		String id = ParserIdLocal.extractId(userInput);
		String startDate = ParserDateLocal.extractStartDate(userInput);
		String startTime = ParserTimeLocal.extractStartTime(userInput);
		String endDate = ParserDateLocal.extractEndDate(userInput);
		String endTime = ParserTimeLocal.extractEndTime(userInput);
		
		if(id.isEmpty() || startDate.isEmpty() || startTime.isEmpty() || endDate.isEmpty() || endTime.isEmpty()) {
			inputConfirm.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputConfirm;
		}
		
		inputConfirm.setCommandType(EnumGroup.CommandType.CONFIRM);
		inputConfirm.setId(id);
		inputConfirm.setStartDate(startDate);
		inputConfirm.setStartTime(startTime);
		inputConfirm.setEndDate(endDate);
		inputConfirm.setEndTime(endTime);
		
		return inputConfirm;
	}
}
