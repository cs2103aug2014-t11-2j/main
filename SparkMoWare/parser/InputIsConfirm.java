package parser;

public class InputIsConfirm {
	
	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputConfirm = new RefinedUserInput();
		String id = Misc.extractId(Misc.removeCommand(userInput, "confirm"));
		String date = ParserDateLocal.extractStartDate(userInput);
		String time = ParserTimeLocal.extractStartTime(userInput);
		
		if(id.isEmpty() || date.isEmpty() || time.isEmpty()) {
			inputConfirm.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputConfirm;
		}
		
		inputConfirm.setCommandType(EnumGroup.CommandType.CONFIRM);
		inputConfirm.setId(id);
		inputConfirm.setStartDate(date);
		inputConfirm.setStartTime(time);
		
		return inputConfirm;
	}
}
