package parser;

public class Interpreter {

	//Method that will be called by the Logic
	public static String[] reader(String userInput) {
		
		switch (ParserCommandLocal.getCommandType(userInput)) {
		case ADD:
			return InputIsAdd.refineInput(userInput);
			
		case EDIT:
			InputIsEdit.executeCommand(userInput);
			break;

		case DELETE:

			break;

		case TENTATIVE:
			
			break;

		case CONFIRM:
			
			break;

		case CLEAR:
			break;

		case SORT:
			
			break;

		case SEARCH:
			
			break;

		case STATISTIC:
			
			break;

		case UNDO:
			
			break;

		case REDO:

			break;

		case DISPLAY:
			
			break;
		
		case FILTER:
			
			break;
		
		case EXIT:
			
			break;

		case HELP:
			

		default:
			
		}
		return null;
	}
}
