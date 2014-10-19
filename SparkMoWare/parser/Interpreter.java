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
			return InputIsDelete.refineInput(userInput);

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
			return InputIsStatistic.refineInput(userInput);

		case UNDO:
			return InputIsUndo.refineInput(userInput);

		case REDO:
			return InputIsRedo.refineInput(userInput);

		case DISPLAY:
			return InputIsDisplay.refineInput(userInput);
		
		case FILTER:
			return InputIsFilter.refineInput(userInput);
		
		case EXIT:
			return InputIsExit.refineInput(userInput);

		case HELP:
			return InputIsHelp.refineInput(userInput);

		default:
			return null;
		}
	}
}
