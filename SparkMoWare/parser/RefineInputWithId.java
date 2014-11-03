package parser;

public class RefineInputWithId {

	protected static RefinedUserInput inputIsDelete(String userInput) {
		int id = -1;

		id = ParserIdLocal.extractId(userInput);
		RefinedUserInput inputDelete = new RefinedUserInput();

		if(id == -1) {
			inputDelete.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputDelete;
		} else {

			inputDelete.setCommandType(EnumGroup.CommandType.DELETE);
			inputDelete.setIndex(id);

			return inputDelete;
		}
	}

	protected static RefinedUserInput inputIsFinish(String userInput) {

		int id = -1;

		RefinedUserInput inputFinish = new RefinedUserInput();
		id = ParserIdLocal.extractId(userInput);		

		if(id == -1) {
			inputFinish.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputFinish;
		} else {

			inputFinish.setCommandType(EnumGroup.CommandType.DONE);
			inputFinish.setIndex(id);

			return inputFinish;
		}
	}
}
