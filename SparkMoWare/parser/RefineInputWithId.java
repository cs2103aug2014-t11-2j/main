package parser;

public class RefineInputWithId {
	
	protected static RefinedUserInput inputIsDelete(String userInput) {
		String id = Misc.extractId(Misc.removeCommand(userInput, "delete"));
		RefinedUserInput inputDelete = new RefinedUserInput();
		
		if(id.isEmpty()) {
			inputDelete.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputDelete;
		}
		
		inputDelete.setCommandType(EnumGroup.CommandType.DELETE);
		inputDelete.setId(id);
		
		return inputDelete;
	}
	
	protected static RefinedUserInput inputIsEdit(String userInput) {
		String id = Misc.extractId(Misc.removeCommand(userInput, "edit"));
		RefinedUserInput inputEdit = new RefinedUserInput();
		
		if(id.isEmpty()) {
			inputEdit.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputEdit;
		}
		
		inputEdit.setCommandType(EnumGroup.CommandType.EDIT);
		inputEdit.setId(id);
		
		return inputEdit;
	}

	protected static RefinedUserInput inputIsFinish(String userInput) {
		RefinedUserInput inputFinish = new RefinedUserInput();
		String id = Misc.extractId(Misc.removeCommand(userInput, "delete"));	
		
		if(id.isEmpty()) {
			inputFinish.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputFinish;
		}
		
		inputFinish.setCommandType(EnumGroup.CommandType.DONE);
		inputFinish.setId(id);
		
		return inputFinish;
	}
}
