package logic;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import storage.HelpList;
import storage.Storage;

public class SparkMoVare {

	protected static final int SYSTEM_EXIT_NO_ERROR = 0;
	protected static final int SYSTEM_EXIT_ERROR = 1;

	protected static Stack< LinkedList<Assignment>> actionHistory = new Stack< LinkedList<Assignment>>();
	protected static Stack< LinkedList<Assignment>> actionFuture = new Stack< LinkedList<Assignment>>();
	protected static LinkedList<Assignment> buffer = new LinkedList<Assignment>();
	private static Scanner scanner = new Scanner(System.in);
	protected static String filePath = "Storage.txt";

	protected static int counter = 0;
	protected static int size = 0;

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
	public static Object SparkMoVare;

	enum CommandType {
		ADD, EDIT, DELETE, TENTATIVE, CONFIRM, SORT, SEARCH, FILTER,
		CLEAR, UNDO, REDO, STATISTIC, EXIT, INVALID, DISPLAY, HELP 
	}

	//Fundamentally the same as CommandType, but without single word commands 
	
    public static void main(String[] args) {

        Print.printToUser(Message.WELCOME);
        Storage.openFile(filePath,Id.getLatestSerialNumber(), buffer);
    	HelpList.openFile("HelpList.txt");
        toDoManager();
    }


	public static void toDoManager() {

		while (true) {
			Print.printToUser(Message.PROMPT);
			RefineInput.determineUserInput(scanner.nextLine());
			Print.printToUser(executeCommand(refinedUserInput[0]));
			if (getCommandType(refinedUserInput[0])!=CommandType.UNDO &&
					getCommandType(refinedUserInput[0]) != CommandType.REDO &&
					getCommandType(refinedUserInput[0]) != CommandType.INVALID &&
					getCommandType(refinedUserInput[0]) != CommandType.DISPLAY) {
				actionHistory.add(buffer);
				System.out.println("File saved");
			}
			Storage.saveFile(filePath, buffer);
		}
	} 

	public static String executeCommand(String inputCommand) {

		CommandType command = getCommandType(inputCommand);

		if (command != CommandType.UNDO && command != CommandType.REDO ) {
			while (!actionFuture.empty()){
				actionFuture.pop();
			}
		}

		switch (command) {
		case ADD:
			return Add.addAssignment(refinedUserInput[1], refinedUserInput[2], Integer.parseInt(refinedUserInput[7]),
					refinedUserInput[3], refinedUserInput[4], refinedUserInput[5], refinedUserInput[6], 
					false, refinedUserInput[9], null);

		case EDIT:
			Edit.editAssignment(refinedUserInput);
			break;

		case DELETE:
			return Delete.delete(refinedUserInput[1]);

		case TENTATIVE:
			Tentative.addTentative(refinedUserInput[8], refinedUserInput[2]);
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
			return RedoUndo.undo(filePath, buffer, actionHistory, actionFuture);

		case REDO:
			return RedoUndo.redo(filePath, buffer, actionHistory, actionFuture);

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

	protected static int getBufferPosition(String id) {

		counter = 0;
		size = buffer.size();

		while(counter < size && !buffer.get(counter).getId().contentEquals(id)){
			counter++;
		}	
		return counter;
	}

	static int getLineCount() {
		return buffer.size();
	}

	public static String getfilePath(){
		return filePath;
	}
	
	public static LinkedList<Assignment> getBuffer() {
		return buffer;
	}
}
