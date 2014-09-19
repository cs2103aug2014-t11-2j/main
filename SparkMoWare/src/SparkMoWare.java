import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

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
		SEARCH, SHOW_LIST, DELETE_ALL, UNDO, STATISTIC 
	};//why show list, why not display? User will input display base on the project manual
	
	// This vector will be use to store the text lines
	private static Vector<String> buffer = new Vector<String>();

	private static Scanner scanner = new Scanner(System.in);

	// To keep track of the text file used
	private static String filePath;

	public static void main(String[] args) {
		filePath = "storage.txt";
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
			case ADD_TASK:
				createNew(userCommand);
				break;
			case EDIT_TASK:
				edit(userCommand);
				break;
			case DELETE_TASK:
				delete(userCommand);
				break;
			case TENTATIVE:
				addTentative(userCommand);
				break;
			case CONFIRM:
				confirmTentative();
				break;
			case SEARCH:
				search(userCommand);
				break;
			case SHOW_LIST:
				showList();
				break;
			case DELETE_ALL:
				deleteAll(userCommand);
				break;
			case STATISTIC:
				statistic();
				break;
			case UNDO:
				undo();
				break;	
			/*case INVALID:
				System.out.println(String.format(MESSAGE_INVALID_FORMAT, userCommand));
				break;
			case EXIT:
				System.exit(0);
				break;*/
			default:
				//throw an error if the command is not recognised
				throw new Error("Unrecognized command type");
			}
		}

	}

	private static String getFirstWord(String userCommand){
		return userCommand.substring(0, userCommand.indexOf(" "));
	}
	
	private static CommandType determineCommandType(String commandType){
		if(commandType.equalsIgnoreCase("add")){
			return CommandType.ADD_TASK;
		}
		else if(commandType.equalsIgnoreCase("edit")){
			return CommandType.EDIT_TASK;
		}
		else if(commandType.equalsIgnoreCase("delete")){
			return CommandType.DELETE_TASK;
		}
		else if(commandType.equalsIgnoreCase("tentative")){
			return CommandType.TENTATIVE;
		}
		else if(commandType.equalsIgnoreCase("confirm")){
			return CommandType.CONFIRM;
		}
		else if(commandType.equalsIgnoreCase("search")){
			return CommandType.SEARCH;
		}
		else if(commandType.equalsIgnoreCase("display")){
			return CommandType.SHOW_LIST;
		}
		else if(commandType.equalsIgnoreCase("deleteAll")){
			return CommandType.DELETE_ALL;
		}
		else if(commandType.equalsIgnoreCase("undo")){
			return CommandType.UNDO;
		}
		else if(commandType.equalsIgnoreCase("statistic")){
			return CommandType.STATISTIC;
		}
		/*what about invalid?
		 *  else{
		 *  return CommandType.Invalid; //the enum does not contain Invalid
		 *  }
		 */
	}
	
	private static void createNew(String userCommand){
		userCommand// if no duration then create task
		//otherwise create appointment
	}

	private static void createTask(String userCommand){

		createReminder();
	}

	private static void createAppoinment(String userCommand){

		createReminder();

	}

	private static void createReminder(){

	}
	
	private static void edit(String userCommand){
		search(userCommand);
		//while still a next term, switch case
		editWhole();
		editTitle();
		editDescription();
		editTime();
		editDate();
	}
	
	private static void delete(String userCommand){
		search(userCommand);
		// then remove
	}
	
		//private static void convert(userCommand){

	private static void addTentative(String userComamnd){

		String[] tentativeArray = userCommand.split(" ", 2);
		
		for(int tentativeCount = 1; tentativeCount <= tentativeArray[1]; tentativeCount++) {
			
		}
	}
	
	private static void confirmTentative(){
		
	}
	
	private static String search(String userCommand){
		
		String[] stringArray = userCommand.split(" ", 2);
		String stringToSearch = stringArray[1];
		String stringFound;
		
		for(int listCount = 0; listCount < buffer.size(); listCount++) {
			if(buffer.get(listCount).getTitle().contains(stringToSearch)) {
				
				// When the title is only a word long
				if(buffer.get(listCount).getTitle() == stringToSearch) {
					stringFound = stringToSearch;
				}
				else {
					String[] textArray = buffer.get(listCount).getTitle().split(" ");
				
					for(int textCount = 0; textCount < textArray.length; textCount++) {
						if(textArray[textCount] == stringToSearch){
							stringFound = buffer.get(listCount).getTitle();
						}
					}
				}
			}		
		}
		return stringFound;
	}
	
	private static void showList(){
		// display all the list
	}
	
	private static void deleteAll(String userCommand){
		//search() and delete ( if == date < date or startEnd
		
		if(userCommand.length()==10){	//user only inputs delete all
			//delete entire file
		}
		else if(userCommand.contains("on")){
			
		}
		else if(userCommand.contains("before")){
			
		}
		else if(userCommand.contains("from")){
			
		}
		/* to deal with exceptions/errors, Below is rough version. Necessary?
		else{
			System.out.println(String.format(MESSAGE_INVALID_FORMAT, userCommand));
		}*/
		
		//if user inputs a date/period with nothing and thus deletes nothing
		//are we printing error "nothing to delete" or just report "all deleted"
	}

	//changeview

	private static void undo(){
	}
	
	private static void statistic(){

	}
	
	private static void checkValidity(){
		// check validity of date and time => date exist or is clashing?
	}	
	/**
	 * This operation is used to save the changes made
	 */
	private static void saveFile(String filePath) {
		File file = new File(filePath);
		file.delete();
		try {
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0; i< buffer.size(); i++){
				bw.write(buffer.get(i));
				if (i<buffer.size()-1){
					bw.newLine(); 
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
