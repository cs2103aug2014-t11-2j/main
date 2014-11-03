package parser;

public class InputIsConfirm {
	
	protected static RefinedUserInput refineInput(String userInput) {
		
		int id = -1;
		RefinedUserInput inputConfirm = new RefinedUserInput();
		id = ParserIdLocal.extractId(userInput);
		String startDate = ParserDateLocal.extractStartDate(userInput);
		String startTime = ParserTimeLocal.extractStartTime(userInput);
		String endDate = ParserDateLocal.extractEndDate(userInput);
		String endTime = ParserTimeLocal.extractEndTime(userInput);
		
		if(id == -1 || startDate.isEmpty() || startTime.isEmpty() || endDate.isEmpty() || endTime.isEmpty()) {
			inputConfirm.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputConfirm;
		}
		
		inputConfirm.setCommandType(EnumGroup.CommandType.CONFIRM);
		inputConfirm.setIndex(id);
		inputConfirm.setStartDate(startDate);
		inputConfirm.setStartTime(startTime);
		inputConfirm.setEndDate(endDate);
		inputConfirm.setEndTime(endTime);
		
		return inputConfirm;
	}
}
