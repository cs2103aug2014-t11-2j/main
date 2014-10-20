package parser;

public class InputIsFinish {
	
	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputFinish = new RefinedUserInput();
		String id = Misc.extractId(userInput);		
		
		if(id.isEmpty()) {
			return inputFinish;
		}
		
		inputFinish.setCommandType(EnumGroup.CommandType.DONE);
		inputFinish.setId(id);
		
		return inputFinish;
	}
}
