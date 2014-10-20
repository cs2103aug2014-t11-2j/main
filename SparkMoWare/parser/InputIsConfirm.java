package parser;

public class InputIsConfirm {
	
	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputConfirm = new RefinedUserInput();
		String id = Misc.extractId(userInput);
		
		if(id.isEmpty()) {
			return inputConfirm;
		}
		
		String date = ParserDateLocal.extractStartDate(userInput);
		
		if(date.isEmpty()) {
			return inputConfirm;
		}
		
		String time = ParserTimeLocal.extractStartTime(userInput);
		
		if(time.isEmpty()) {
			return inputConfirm;
		}
		
		inputConfirm.setCommandType(EnumGroup.CommandType.CONFIRM);
		inputConfirm.setId(id);
		inputConfirm.setStartDate(date);
		inputConfirm.setStartTime(time);
		
		return inputConfirm;
	}
}
