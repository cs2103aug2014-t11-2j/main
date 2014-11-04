package parser;

public class Interpreter {

	//Method that will be called by the Logic
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
			return RefineInputWithSpecial.inputIsSort(userInput);

		case SEARCH:
			return RefineInputWithSpecial.inputIsSearch(userInput);

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
			return RefineInputWithSpecial.inputIsFilter(userInput);
		
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
		/*
		 * potential exception catching for invalid case
		 */
		case INVALID:
			return new RefinedUserInput();

		default:
			return new RefinedUserInput();
		}
	}
}