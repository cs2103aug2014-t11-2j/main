package parser;

/**
 * Class containing the various RefinedUserInput object involving only index.
 * 
 * @author Matthew Song
 *
 */
public class RefineInputWithIndex {

	/**
	 * Method creates a RefinedUserInput for the delete command. If no index is found (index = -1),
	 * an Invalid Format CommandType is returned.
	 * 
	 * @param userInput the String with all the relevant information to be extracted.
	 * @return a RefinedUserInput object for the delete command.
	 */
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

	/**
	 * Method creates a RefinedUserInput for the finish command. If no index is found (index = -1),
	 * an Invalid Format CommandType is returned.
	 * 
	 * @param userInput the String with all the relevant information to be extracted.
	 * @return a RefinedUserInput object for the finish command.
	 */
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
