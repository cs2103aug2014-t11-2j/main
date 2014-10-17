package logic;

import java.util.LinkedList;

import storage.Storage;

public class SparkMoVare {

	protected static final int SYSTEM_EXIT_NO_ERROR = 0;
	protected static final int SYSTEM_EXIT_ERROR = -1;

	protected static String[] refinedUserInput = new String[10];

	/* each index of refinedUserinput represent something
	 * 0:The command string
	 * 1:Serial Number (S/N) of the Assignment ASSUMPTION: serial number length is at most 12 digits DD/MM/YYYY/0000
	 * 2:Title of the Assignment
	 * 3:Start Date
	 * 4:Start Time
	 * 5:End Date
	 * 6:End Time
	 * 7:Type: Task(0), Appointment(1) and Tentative(2)
	 * 8:For command types: delete all (on, before, during)
	 *					   edit which is to be edited, such as title or start date
	 *					   tentative (number of days)
	 *					   sort and search by date, serial number, etc.
	 * 9:Priority 
	 */


	enum CommandType {
		ADD, EDIT, DELETE, TENTATIVE, CONFIRM, SORT, SEARCH, FILTER,
		CLEAR, UNDO, REDO, STATISTIC, EXIT, INVALID, DISPLAY
	}

	//Fundamentally the same as CommandType, but without single word commands 

	//    public static void main(String[] args) {
	//
	//        Print.printToUser(Message.WELCOME);
	//        Storage.openFile(InternalStorage.getFilePath(),Id.getLatestSerialNumber(), InternalStorage.getBuffer());
	//    	HelpList.openFile("HelpList.txt");
	//        toDoManager();
	//    }


	public static void toDoManager() {

		Output returnOutput = new Output();
		while (true) {
			Print.printToUser(Message.PROMPT);
			RefineInput.determineUserInput(InternalStorage.getScanner().nextLine());
			executeCommand(refinedUserInput[0]);
			if (getCommandType(refinedUserInput[0])!=CommandType.UNDO &&
					getCommandType(refinedUserInput[0]) != CommandType.REDO &&
					getCommandType(refinedUserInput[0]) != CommandType.INVALID &&
					getCommandType(refinedUserInput[0]) != CommandType.DISPLAY) {
				InternalStorage.pushHistory(InternalStorage.getBuffer());
				returnOutput = executeCommand(refinedUserInput.toString());
				System.out.println("File saved");
			}
			Storage.saveFile(InternalStorage.getFilePath(), InternalStorage.getBuffer());
		}		
		
	} 

	public static Output executeCommand(String inputCommand) {

		Output returnOutput = new Output();

		CommandType command = getCommandType(inputCommand);

		if (command != CommandType.UNDO && command != CommandType.REDO ) {
			while (!InternalStorage.getFuture().empty()){
				InternalStorage.popFuture();
			}
		}

		switch (command) {
		case ADD:
			Add.addSomething(refinedUserInput);

			returnOutput.setReturnBuffer(InternalStorage.getBuffer());
			returnOutput.setFeedback(Message.ADDED);
			returnOutput.setTotalAssignment(returnOutput.getTotalAssignment() + 1);
			returnOutput.setIsStats(false);

			return returnOutput;

		case EDIT:
			Edit.editAssignment(refinedUserInput);

			returnOutput.setReturnBuffer(InternalStorage.getBuffer());
			returnOutput.setFeedback(Message.EDITED);
			returnOutput.setIsStats(false);

			return returnOutput;

		case DELETE:
			for(int index=0; index<InternalStorage.getBuffer().size(); index++){
				if(InternalStorage.getBuffer().get(index).getId().equals(refinedUserInput[1])){
					if(InternalStorage.getBuffer().get(index).getIsDone()){
						returnOutput.setTotalCompleted(returnOutput.getTotalCompleted()-1);
					}
					if(InternalStorage.getBuffer().get(index).getIsOnTime()){
						returnOutput.setTotalOnTime(returnOutput.getTotalOnTime()-1);
					}
				}
			}
			
			Delete.delete(refinedUserInput[1]);

			returnOutput.setReturnBuffer(InternalStorage.getBuffer());
			returnOutput.setFeedback(Message.DELETED);
			returnOutput.setTotalAssignment(returnOutput.getTotalAssignment() - 1);
			returnOutput.setIsStats(false);

			return returnOutput;
			/*
		case TENTATIVE:
			SetTentative.addTentative(refinedUserInput[8], refinedUserInput[2]);
			break;

		case CONFIRM:
			ConfirmTentative.confirmTentative(refinedUserInput[1], refinedUserInput[3],
					refinedUserInput[4], refinedUserInput[5], refinedUserInput[6]);
			break;
			 */
		case CLEAR:
			int assignmentLeft = 0;

			assignmentLeft = Delete.deleteAll(refinedUserInput[8], refinedUserInput[5], refinedUserInput[3]);

			returnOutput.setReturnBuffer(InternalStorage.getBuffer());
			returnOutput.setFeedback(Message.DELETE_ALL);
			returnOutput.setTotalAssignment(assignmentLeft);
			returnOutput.setIsStats(false);

			return returnOutput;

		case SORT:
			returnOutput.setReturnBuffer(Sort.sortRequired(refinedUserInput[8], 
					refinedUserInput[3], refinedUserInput[5]));
			returnOutput.setFeedback(Message.SORT);
			returnOutput.setIsStats(false);

			return returnOutput;

		case SEARCH:
			returnOutput.setReturnBuffer(SearchAll.searchAll(refinedUserInput[8]));
			returnOutput.setFeedback(Message.SEARCH);
			returnOutput.setIsStats(false);

			return returnOutput;

		case STATISTIC:
			returnOutput.setReturnBuffer(InternalStorage.getBuffer());
			returnOutput.setIsStats(true);
			returnOutput.setFeedback(Message.STATISTIC);

			return returnOutput;
			/*
		case UNDO:
			return RedoUndo.undo();

		case REDO:
			return RedoUndo.redo();
			 */
		case DISPLAY:
			returnOutput.setReturnBuffer(InternalStorage.getBuffer());
			returnOutput.setFeedback(Message.DISPLAY);
			returnOutput.setIsStats(false);

			return returnOutput;

		case FILTER:
			returnOutput.setReturnBuffer(Filter.filterMain(refinedUserInput[8], refinedUserInput[3], refinedUserInput[5]));
			returnOutput.setFeedback(Message.FILTER);
			returnOutput.setIsStats(false);
			
			return returnOutput;

		case EXIT:
			System.exit(SYSTEM_EXIT_NO_ERROR);
			break;
			
		default:
			LinkedList<Assignment> noAssignment = new LinkedList<Assignment>();
			returnOutput.setReturnBuffer(noAssignment);
			returnOutput.setFeedback(Message.INVALID_COMMAND);
			returnOutput.setIsStats(false);
			
			return returnOutput;
		}
		return returnOutput;
	}

	// Returns commandType back to the system before executing the logics
	public static CommandType getCommandType(String command) {

		if (command.equalsIgnoreCase("add")) {
			return CommandType.ADD;
		} else if(command.equalsIgnoreCase("tentative")) {
			return CommandType.TENTATIVE;
		} else if (command.equalsIgnoreCase("confirm")) {
			return CommandType.CONFIRM;
		} else if (command.equalsIgnoreCase("delete")) {
			return CommandType.DELETE;
		} else if (command.equalsIgnoreCase("search")) {
			return CommandType.SEARCH;
		} else if (command.equalsIgnoreCase("edit")) {
			return CommandType.EDIT;
		} else if (command.equalsIgnoreCase("clear")) {
			return CommandType.CLEAR;
		} else if (command.equalsIgnoreCase("sort")) {
			return CommandType.SORT;
		} else if (command.equalsIgnoreCase("statistic")) {
			return CommandType.STATISTIC;
		} else if (command.equalsIgnoreCase("undo")) {
			return CommandType.UNDO;
		} else if (command.equalsIgnoreCase("redo")) {
			return CommandType.REDO;
		} else if (command.equalsIgnoreCase("exit")) {
			return CommandType.EXIT;
		} else if (command.equalsIgnoreCase("display")){
			return CommandType.DISPLAY;
		} else if (command.equalsIgnoreCase("filter")) {
			return CommandType.FILTER;
		} else {
			return CommandType.INVALID;
		}		
	}
}
