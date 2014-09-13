import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import textbuddy.TextBuddyPlus.CommandType;


public class SparkMoWare2 {

	// Output Messages
	private static final String MESSAGE_ADDED = "added to %1$s: \"%2$s\"";
	private static final String MESSAGE_DELETED = "deleted from %1$s: \"%2$s\"";
	private static final String MESSAGE_CLEARED = "all content deleted from %1$s";
	private static final String MESSAGE_EMPTY = "%1$s is empty";
	private static final String MESSAGE_WELCOME = "Welcome to SparkMoWare!";
	private static final String MESSAGE_INVALID_FORMAT = "Invalid Format";
	private static final String MESSAGE_STORAGE_FILE_ERROR = "Exception encountered while initalising the Storage file";

	// These are the possible command types
	enum CommandType {
		ADD_TASK, EDIT_TASK, DELETE_TASK, TENTATIVE, CONFIRM, SORT, SEARCH, 
		DISPLAY, DELETE_ALL, UNDO, STATISTIC, EXIT, INVALID 
	};

	private static Vector<String> buffer = new Vector<String>();
	private static Scanner scanner = new Scanner(System.in);
	private static String filePath="Storage";

	public static void main(String[] args) {
		printToUser(MESSAGE_WELCOME);
		openFile(filePath);
		ToDoManager();
	}

	private static void ToDoManager() {
		while (true) {
			System.out.print("command: ");
			printToUser(executeCommand(scanner.nextLine()));
			saveFile(filePath);
		}
	}

	private static void printToUser(String output){
		if (!output.equals("")){
			System.out.println(output);
		}
	}

	private static void openFile(String filePath) {
		try { // check if file exist if not create a new file with that name
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				buffer.add(line);
			}
			fileReader.close();
		} catch (IOException e) {
			showToUser(MESSAGE_STORAGE_FILE_ERROR);
			System.exit(0);
		}
	}

	private static void saveFile(String filePath) {
		File file = new File(filePath);
		file.delete();
		try {
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
			System.out.println("Exception encountered while saving the textfile");
			System.exit(0);
		}
	}

	private static String getCommandContent(String userInput) {
		return userInput.substring(userInput.indexOf(' ')+1);
	}

	private static CommandType getCommandType(String userInput) {
		if (userInput.split(" ").length<1){
			return CommandType.INVALID;
		}
		else {
			String userInputSubstring;
			if (userInput.split(" ").length>1){
				userInputSubstring = userInput.substring(0,userInput.indexOf(' '));
			}
			else{
				userInputSubstring = userInput;
			}

			if (userInputSubstring.equalsIgnoreCase("add")) {
				if (userInput.split(" ").length<2){
					return CommandType.INVALID;
				}
				return CommandType.ADD_TASK;
			} else if (userInputSubstring.equalsIgnoreCase("delete")) {
				if (userInput.split(" ").length<2){
					return CommandType.INVALID;
				}
				return CommandType.DELETE_TASK;
			} else if (userInputSubstring.equalsIgnoreCase("display")) {
				return CommandType.DISPLAY;
			} else if (userInputSubstring.equalsIgnoreCase("clear")) {
				return CommandType.DELETE_ALL;
			} else if (userInputSubstring.equalsIgnoreCase("confirm")) {
				if (userInput.split(" ").length<2){
					return CommandType.INVALID;
				}
				return CommandType.CONFIRM;
			} else if (userInputSubstring.equalsIgnoreCase("sort")) {
				return CommandType.SORT;
			} else if (userInputSubstring.equalsIgnoreCase("search")) {
				if (userInput.split(" ").length<2){
					return CommandType.INVALID;
				}
				return CommandType.SEARCH;
			}  else if (userInputSubstring.equalsIgnoreCase("statistic")) {
				return CommandType.STATISTIC;
			}  else if (userInputSubstring.equalsIgnoreCase("undo")) {
				return CommandType.UNDO;
			} else if (userInputSubstring.equalsIgnoreCase("exit")) {
				return CommandType.EXIT;
			} else {
				return CommandType.INVALID;
			}		
		}
	}

	static String executeCommand(String userInput) {
		switch (getCommandType(userInput)) {
		case ADD_TASK:
			return addTask(getCommandContent(userInput));
		case EDIT_TASK:
			return editTask(getCommandContent(userInput));
		case DELETE_TASK:
			return deleteTask(getCommandContent(userInput));
		case TENTATIVE:
			return tentative(getCommandContent(userInput));
		case CONFIRM:
			return confirm(getCommandContent(userInput))
		case DISPLAY:
			return display();
		case DELETE_ALL:
			return DELETE_ALL();
		case SORT:
			return sort();
		case SEARCH:
			return search(getCommandContent(userInput));
		case STATISTIC:
			statistic();
			break;
		case UNDO:
			undo();
			break;	
		case EXIT:
			System.exit(0);
			break;
		case INVALID:
			return "Invalid Command issued!";
		default:
			return "Invalid Command issued!";
		}
		return "";
	}

	private static String addTask(String commandContent) {
		buffer.add(commandContent.trim());
		String confirmation = "added to " + filePath + ": \"" + commandContent + "\"";
		return confirmation;
	}

	private static String editTask(String commandContent){
		//TODO
	}

	private static String deleteTask(String commandContent) {

		int lineNumber=Integer.parseInt(commandContent);
		if (lineNumber<1||lineNumber>buffer.size()){
			return "Trying to delete invalid line";
		}
		else{
			String stringDeleted="";
			stringDeleted=buffer.get(lineNumber-1);
			buffer.remove(lineNumber-1);
			return ("deleted from " + filePath + ": " + "\"" + stringDeleted + "\"");
		}
	}

	private static String display() {
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

	private static String DELETE_ALL() {
		buffer.clear();
		return ("all content deleted from "+ filePath);
	}


	private static String sort() {
		Collections.sort(buffer);
		return "To do list has been sorted";
	}

	static String search(String commandContent) {
		boolean isFound = false;
		for(int i=0; i< buffer.size(); i++){
			if (buffer.get(i).toLowerCase().indexOf(commandContent.toLowerCase())>-1){
				printToUser(commandContent + " has been found in: " +String.valueOf(i+1)+ ". "+ buffer.get(i));
				isFound = true;
			}
		}
		if (isFound){
			return "";
		}
		return (commandContent + " is not found within " + filePath);
	}

	static int getLineCount(){
		return buffer.size();
	}


}
