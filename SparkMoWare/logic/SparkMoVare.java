package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class SparkMoVare {

	protected static final int SYSTEM_EXIT_NO_ERROR = 0;
	protected static final int SYSTEM_EXIT_ERROR = 1;

	protected static Stack< LinkedList<Assignment>> actionHistory = new Stack< LinkedList<Assignment>>();
	protected static Stack< LinkedList<Assignment>> actionFuture = new Stack< LinkedList<Assignment>>();
	protected static LinkedList<Assignment> buffer = new LinkedList<Assignment>();
	protected static Scanner scanner = new Scanner(System.in);
	protected static String filePath = "Storage.txt";
	protected static String latestSerialNumber = "";

	protected static int counter = 0;
	protected static int size = 0;

	protected static String[] refinedUserInput = new String[9];
	/*each index of refinedUserinput represent something
	 *0:The command string
	 *1:Serial Number (S/N) of the Assignment ASSUMPTION: serial number length is at most 12 digits DD/MM/YYYY/0000
	 *2:Title of the Assignment
	 *3:Start Date
	 *4:Start Time
	 *5:End Date
	 *6:End Time
	 *7:Type: Task(0), Appointment(1) and Tentative(2)
	 *8:For command types: delete all (on, before, during)
	 *					   edit which is to be edited, such as title or start date
	 *					   tentative (number of days)
	 *					   sort and search by date, serial number, etc. 
	 */
	public static Object SparkMoVare;

	enum CommandType {
		ADD, EDIT, DELETE, TENTATIVE, CONFIRM, SORT, SEARCH, 
		CLEAR, UNDO, REDO, STATISTIC, EXIT, INVALID, DISPLAY 
	}

	//Fundamentally the same as CommandType, but without single word commands 
	enum RefinementType {
		ADD, EDIT, DELETE, TENTATIVE, CONFIRM, SORT, SEARCH, 
		CLEAR, INVALID, OTHERS
	}

	public static void main(String[] args) {

		Message.printToUser(Message.WELCOME);
		openFile(filePath);
		toDoManager();
	}

	public static void openFile(String filePath) {

		try { // check if file exist if not create a new file with that name
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;


			while ((line = bufferedReader.readLine())  != null ) {

				String lineArray[] = line.split("~");
				Assignment temp = new Assignment();

				temp.setId(lineArray[0]);
				temp.setTitle(lineArray[1]);
				temp.setType(Integer.parseInt(lineArray[2]));
				temp.setStartDate(lineArray[3]);
				temp.setStartTime(lineArray[4]);
				temp.setEndDate(lineArray[5]);
				temp.setEndTime(lineArray[6]);
				temp.setIsDone(Boolean.parseBoolean(lineArray[7]));
				temp.setIsOnTime(Boolean.parseBoolean(lineArray[8]));
				temp.setPriority(Integer.parseInt(lineArray[9]));
				//temp.setAlarm(Integer.parseInt(lineArray[7]));
				// tags to be done
				// updates the latest serial number

				if( latestSerialNumber.equals("")) {
					latestSerialNumber = lineArray[0];
				} else {
					if(Comparator.serialNumberComparator(lineArray[0], latestSerialNumber)) {

						latestSerialNumber = lineArray[0];
					}
				}
				buffer.add(temp);
			}
			fileReader.close();
		} catch (IOException e) {

			Message.printToUser(Message.STORAGE_FILE_ERROR);
			System.exit(SYSTEM_EXIT_ERROR);
		}
	}

	public static void toDoManager() {

		while (true) {
			Message.printToUser(Message.PROMPT);
			RefineInput.determineUserInput(scanner.nextLine());
			Message.printToUser(executeCommand(refinedUserInput[0]));
			if (getCommandType(refinedUserInput[0])!=CommandType.UNDO &&
					getCommandType(refinedUserInput[0]) != CommandType.REDO &&
					getCommandType(refinedUserInput[0]) != CommandType.INVALID &&
					getCommandType(refinedUserInput[0]) != CommandType.DISPLAY) {
				actionHistory.add(buffer);
				System.out.println("File saved");
			}
			saveFile(filePath);
		}
	} 

	protected static void saveFile(String filePath) {

		File file = new File(filePath);
		if(file.delete()){
			//System.out.println("yay");
		}else{
			System.out.println("failed");
		}

		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for(int i = 0; i< buffer.size(); i++){
				bw.write(buffer.get(i).toString());
				if (i<buffer.size() - 1){
					bw.newLine(); 
				}
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("Exception encountered while saving the textfile");
			System.exit(SYSTEM_EXIT_ERROR);
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
					false, null);

		case EDIT:
			Edit.editAssignment(refinedUserInput);
			break;

		case DELETE:
			return Delete.delete(refinedUserInput[1]);

		case TENTATIVE:
			Tentative.addTentative(refinedUserInput[8]);
			break;

		case CONFIRM:
			ConfirmTentative.confirmTentative(refinedUserInput[1], refinedUserInput[3], refinedUserInput[4]);
			break;

		case CLEAR:
			return Delete.deleteAll(refinedUserInput[8], refinedUserInput[5], refinedUserInput[3]);

		case SORT:
			//		return Sort.sort();
			break;

		case SEARCH:
			//		return search();
			break;

		case STATISTIC:
			//		Statisics.statistic();
			break;

		case UNDO:
			return RedoUndo.undo();

		case REDO:
			return RedoUndo.redo();

		case DISPLAY:
			display();
			break;

		case EXIT:
			System.exit(SYSTEM_EXIT_NO_ERROR);
			break;

		case INVALID:
			return "Invalid Command issued!";

		default:
			return "Invalid Command issued!";
		}
		return "";
	}

	// Returns commandType back to the system before executing the logics
	public static CommandType getCommandType(String command) {

		if (command.equalsIgnoreCase("add")) {
			return CommandType.ADD;
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
		} else {
			return CommandType.INVALID;
		}		
	}

	public static String display() {
		for(int i=0; i< buffer.size(); i++){
			String lineToAdd="";
			lineToAdd+=String.valueOf(i+1);
			lineToAdd+=". ";
			lineToAdd+=buffer.get(i);
			System.out.println(lineToAdd);
		}

		if (getLineCount()==0){
			return (filePath + " is empty");
		}
		else{
			return "";
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

	public static void setLatestSerialNumber(String newSn) {
		latestSerialNumber = newSn;
	}

	public static String getLatestSerialNumber(){
		return latestSerialNumber;
	}

	public static String getfilePath(){
		return filePath;
	}
}
