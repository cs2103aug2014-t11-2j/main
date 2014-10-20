package parser;

public class RefineInputWithId {
	
	protected static RefinedUserInput inputIsDelete(String userInput) {
		String id = Misc.extractId(userInput);
		RefinedUserInput inputDelete = new RefinedUserInput();
		
		if(id.isEmpty()) {
			return inputDelete;
		}
		
		inputDelete.setCommandType(EnumGroup.CommandType.DELETE);
		
		return inputDelete;
	}
	
	protected static RefinedUserInput inputIsEdit(String userInput) {
		/* if() {
			
		}
		 When the user types [edit id], what will be shown to the user is everything in the buffer
		 * the user has access to/can edit.
		 * [edit title date time]
		 * 
		 */
		
		String id = Misc.extractId(userInput);
		RefinedUserInput inputEdit = new RefinedUserInput();
		
		if(id.isEmpty()) {// <-- need change. Instead, if id isnt there, it means its new content
			//checking id exists is handled by logic.
			return inputEdit;
		}
		
		inputEdit.setCommandType(EnumGroup.CommandType.EDIT);
		
		return inputEdit;
	}

	protected static RefinedUserInput inputIsFinish(String userInput) {
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
