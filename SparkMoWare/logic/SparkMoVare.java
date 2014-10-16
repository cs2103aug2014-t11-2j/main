package logic;

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
		CLEAR, UNDO, REDO, STATISTIC, EXIT, INVALID, DISPLAY, HELP 
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

		while (true) {
			Print.printToUser(Message.PROMPT);
			RefineInput.determineUserInput(InternalStorage.getScanner().nextLine());
			Print.printToUser(executeCommand(refinedUserInput[0]));
			if (getCommandType(refinedUserInput[0])!=CommandType.UNDO &&
					getCommandType(refinedUserInput[0]) != CommandType.REDO &&
					getCommandType(refinedUserInput[0]) != CommandType.INVALID &&
					getCommandType(refinedUserInput[0]) != CommandType.DISPLAY) {
				InternalStorage.pushHistory(InternalStorage.getBuffer());
				System.out.println("File saved");
			}
			Storage.saveFile(InternalStorage.getFilePath(), InternalStorage.getBuffer());
		}
	} 

	public static String executeCommand(String inputCommand) {

		CommandType command = getCommandType(inputCommand);

		if (command != CommandType.UNDO && command != CommandType.REDO ) {
			while (!InternalStorage.getFuture().empty()){
				InternalStorage.popFuture();
			}
		}

		switch (command) {
		case ADD:
			if(refinedUserInput[7].equals("APPT")) {
				
			return Add.addAppointment(refinedUserInput[1], refinedUserInput[2], refinedUserInput[7],
					refinedUserInput[3], refinedUserInput[4], refinedUserInput[5], refinedUserInput[6], 
					false, refinedUserInput[9]);
			} else if(refinedUserInput[7].equals("TASK")) {
				
				return Add.addTask(refinedUserInput[1], refinedUserInput[2], refinedUserInput[7],
						refinedUserInput[5], refinedUserInput[6], false, refinedUserInput[9]);
			}

		case EDIT:
			Edit.editAssignment(refinedUserInput);
			break;

		case DELETE:
			return Delete.delete(refinedUserInput[1]);

		case TENTATIVE:
			SetTentative.addTentative(refinedUserInput[8], refinedUserInput[2]);
			break;

		case CONFIRM:
			ConfirmTentative.confirmTentative(refinedUserInput[1], refinedUserInput[3],
					refinedUserInput[4], refinedUserInput[5], refinedUserInput[6]);
			break;

		case CLEAR:
			return Delete.deleteAll(refinedUserInput[8], refinedUserInput[5], refinedUserInput[3]);

		case SORT:
			Print.printList(Sort.sortRequired(refinedUserInput[8], refinedUserInput[3], refinedUserInput[5]));
			break;

		case SEARCH:
			Print.printList(SearchAll.searchAll(refinedUserInput[8]));
			break;

		case STATISTIC:
			Print.printToUser(Statistic.getStats());
			break;

		case UNDO:
			return RedoUndo.undo();

		case REDO:
			return RedoUndo.redo();

		case DISPLAY:
			Print.display();
			break;
		
		case FILTER:
			Print.printList(Filter.filterMain(refinedUserInput[8], refinedUserInput[3], refinedUserInput[5]));
			break;
		
		case EXIT:
			System.exit(SYSTEM_EXIT_NO_ERROR);
			break;

		case HELP:
			Print.printHelpList(HelpList.getHelpList());
			break;
			
		default:
			return "Invalid Command issued!";
		}
		return "";
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
		} else if (command.equalsIgnoreCase("help")) {
			return CommandType.HELP;
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
