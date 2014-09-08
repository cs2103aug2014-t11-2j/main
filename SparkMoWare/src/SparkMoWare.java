import java.util.Scanner;

public class SparkMoWare {

	// Output Messages
	private static final String MESSAGE_ADDED = "added to %1$s: \"%2$s\"";
	private static final String MESSAGE_DELETED = "deleted from %1$s: \"%2$s\"";
	private static final String MESSAGE_CLEARED = "all content deleted from %1$s";
	private static final String MESSAGE_EMPTY = "%1$s is empty";
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %1$s is ready for use ";
	private static final String MESSAGE_INVALID_FORMAT = "Invalid Format";

	
	// These are the possible command types
	enum CommandType {
		ADD_TASK, EDIT_TASK, DELETE_TASK, TENTATIVE, CONFIRM,
		SEARCH, SHOW_LIST, DELETE_ALL, STATISTIC, UNDO
	};

	private static Scanner scanner = new Scanner(System.in);

	// To keep track of the text file used
	private static String filePath;

	public static void main(String[] args) {
		filePath = args[0];
		showToUser(MESSAGE_WELCOME,filePath,"");
		openFile(filePath);
		while (true) {
			System.out.print("command: ");
			String command = scanner.nextLine();
			executeCommand(command);
			saveFile(filePath);
		}
	}

	private static void executeCommand(String userCommand) {
		if (userCommand.trim().equals("")){
			System.out.println(String.format(MESSAGE_INVALID_FORMAT, userCommand));
		}
		else{
			String commandTypeString = getFirstWord(userCommand);
			CommandType commandType = determineCommandType(commandTypeString);
			switch (commandType) {
			case ADD_LINE:
				addLine(userCommand);
				break;
			case DELETE_LINE:
				deleteLine(userCommand);
				break;
			case DISPLAY:
				display();
				break;
			case CLEAR:
				clearAll();
				break;
			case INVALID:
				System.out.println(String.format(MESSAGE_INVALID_FORMAT, userCommand));
				break;
			case EXIT:
				System.exit(0);
				break;
			default:
				//throw an error if the command is not recognized
				throw new Error("Unrecognized command type");
			}
		}

	}
	
	// 
	private static void  createNew(userCommand){
		userCommand// if no duration the create task
		//otherwise create appointment
	}
	
	private static void	  createTask(userCommand){
	
		createReminder();
	}
	
	private static void  createAppoinment(userCommand){
		
		createReminder();

	}
	
	private static void createReminder(){
	
	}
	
	private static void checkValidity(){
		// check validity of date and time
	}
	
	private static void edit(userCommand){
		search(usercommand)
		//while still a next term,switch case
		editWhole();
		editTitle();
		editDescription();
		editTime();
		editDate();
	}
	
	private static void  search(userCommand){
	}
	
	private static void  delete(userCommand){
		search();
		//remove
	}
	
	
}
