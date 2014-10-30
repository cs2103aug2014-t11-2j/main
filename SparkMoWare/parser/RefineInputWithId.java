package parser;

public class RefineInputWithId {
	
	protected static RefinedUserInput inputIsDelete(String userInput) {
		String id = ParserIdLocal.extractId(userInput);
		RefinedUserInput inputDelete = new RefinedUserInput();
		
		if(id.isEmpty()) {
			inputDelete.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputDelete;
		}
		
		inputDelete.setCommandType(EnumGroup.CommandType.DELETE);
		inputDelete.setId(id);
		
		return inputDelete;
	}

	protected static RefinedUserInput inputIsFinish(String userInput) {
		RefinedUserInput inputFinish = new RefinedUserInput();
		String id = ParserIdLocal.extractId(userInput);		
		
		if(id.isEmpty()) {
			inputFinish.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputFinish;
		}
		
		inputFinish.setCommandType(EnumGroup.CommandType.DONE);
		inputFinish.setId(id);
		
		return inputFinish;
	}
}
