package parser;

/**
 * 
 * @author Matthew Song
 *
 */
public class Interpreter {

	/**
	 * Method determines which is the command to be executed. If no match is found, a default is returned
	 * 
	 * @param userInput the String with all the relevant information to be extracted.
	 * @return a RefinedUserInput object.
	 */
	public static RefinedUserInput reader(String userInput) {
		
		switch (Determine.getCommandType(userInput)) {
		case ADD:
			return InputIsAdd.refineInput(userInput);
			
		case EDIT:
			return InputIsEdit.refineInput(userInput);

		case DELETE:
			return RefineInputWithIndex.inputIsDelete(userInput);

		case TENTATIVE:
			return InputIsTentative.refineInput(userInput);

		case CONFIRM:
			return InputIsConfirm.refineInput(userInput);

		case CLEAR:
			return InputIsClear.refineInput(userInput);

		case SORT:
			return InputIsSort.refineInput(userInput);

		case SEARCH:
			return InputIsSearch.refineInput(userInput);

		case STATISTIC:
			RefinedUserInput inputStatistic = new RefinedUserInput();
			inputStatistic.setCommandType(EnumGroup.CommandType.STATISTIC);
			return inputStatistic;

		case UNDO:
			RefinedUserInput inputUndo = new RefinedUserInput();
			inputUndo.setCommandType(EnumGroup.CommandType.UNDO);
			return inputUndo;

		case REDO:
			RefinedUserInput inputRedo = new RefinedUserInput();
			inputRedo.setCommandType(EnumGroup.CommandType.REDO);
			return inputRedo;

		case DISPLAY:
			RefinedUserInput inputDisplay = new RefinedUserInput();
			inputDisplay.setCommandType(EnumGroup.CommandType.DISPLAY);
			return inputDisplay;
		
		case FILTER:
			return InputIsFilter.refineInput(userInput);
		
		case EXIT:
			RefinedUserInput inputExit = new RefinedUserInput();
			inputExit.setCommandType(EnumGroup.CommandType.EXIT);
			return inputExit;

		case HELP:
			RefinedUserInput inputHelp = new RefinedUserInput();
			inputHelp.setCommandType(EnumGroup.CommandType.HELP);
			return inputHelp;
			
		case DONE:
			return RefineInputWithIndex.inputIsFinish(userInput);

		default:
			return new RefinedUserInput();
			
		}
	}
}