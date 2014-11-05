package parser;

public class RefineInputWithIndex {

	protected static RefinedUserInput inputIsDelete(String userInput) {
		int index = ParserIndexLocal.extractIndex(userInput, "delete");
		RefinedUserInput inputDelete = new RefinedUserInput();

		if(index == -1) {
			inputDelete.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputDelete;
		} else {

			inputDelete.setCommandType(EnumGroup.CommandType.DELETE);
			inputDelete.setIndex(index);

			return inputDelete;
		}
	}

	protected static RefinedUserInput inputIsFinish(String userInput) {
		RefinedUserInput inputFinish = new RefinedUserInput();
		int index = ParserIndexLocal.extractIndex(userInput, "finish");		

		if(index == -1) {
			inputFinish.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputFinish;
		} else {

			inputFinish.setCommandType(EnumGroup.CommandType.DONE);
			inputFinish.setIndex(index);

			return inputFinish;
		}
	}
}
