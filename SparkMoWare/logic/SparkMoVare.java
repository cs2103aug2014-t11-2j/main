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
import java.util.Vector;

public class SparkMoVare {


	// Output Messages
	private static final String MESSAGE_WELCOME = "Welcome to SparkMoWare!";
	private static final String MESSAGE_ADDED = "added to %1$s: \"%2$s\"";
	private static final String MESSAGE_DELETED = "deleted from %1$s: \"%2$s\"";
	private static final String MESSAGE_CLEARED = "all content deleted from %1$s";
	private static final String MESSAGE_EMPTY = "%1$s is empty";
	private static final String MESSAGE_INVALID_FORMAT = "Invalid Format";
	private static final String MESSAGE_STORAGE_FILE_ERROR = "Exception encountered while initalising the Storage file";

	// These are the possible command types
	enum CommandType {
		ADD_TASK, EDIT_TASK, DELETE_TASK, TENTATIVE, CONFIRM, SORT, SEARCH, 
		DISPLAY, DELETE_ALL, UNDO, REDO, STATISTIC, EXIT, INVALID 
	};

	private static Stack< LinkedList<Assignment>> actionHistory = new Stack< LinkedList<Assignment>>();
	private static Stack< LinkedList<Assignment>> actionFuture = new Stack< LinkedList<Assignment>>();
	private static LinkedList<Assignment> buffer = new LinkedList<Assignment>();
	private static Scanner scanner = new Scanner(System.in);
	private static String filePath = "Storage";

	public static void main(String[] args) {
		printToUser(MESSAGE_WELCOME);
		openFile(filePath);
		//ToDoManager();
		System.out.println(addTask("555","testing this is 123", 0, "230914", "2359", "010101","0000",false, new Vector<String>()));
		saveFile(filePath);
	}

	public static void ToDoManager() {
		while (true) {
			System.out.print("command: ");
			printToUser(executeCommand(scanner.nextLine()));
			actionHistory.add(buffer);
			saveFile(filePath);

		}
	}

	public static void printToUser(String output){
		if (!output.equals("")){
			System.out.println(output);
		}
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

			while ((line = bufferedReader.readLine()) != null ) {
				String lineArray[] = line.split(";");
				Assignment temp = new Assignment();
				temp.setId(lineArray[0]);
				temp.setTitle(lineArray[1]);
				temp.setType(Integer.parseInt(lineArray[2]));
				temp.setStartDate(lineArray[2]);
				temp.setStartTime(lineArray[3]);
				temp.setEndDate(lineArray[4]);
				temp.setEndTime(lineArray[5]);
				temp.setIsDone(Boolean.parseBoolean(lineArray[6]));
				//temp.setAlarm(Integer.parseInt(lineArray[7]));
				// tags to be done
				buffer.add(temp);
			}
			fileReader.close();
		} catch (IOException e) {
			printToUser(MESSAGE_STORAGE_FILE_ERROR);
			System.exit(0);
		}
	}

	public static void saveFile(String filePath) {
		File file = new File(filePath);
		file.delete();
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String store;
			for(int i=0; i< buffer.size(); i++){
				if (i<buffer.size()-1){
					store="";
					store+=String.valueOf(buffer.get(i).getId());
					store+=(";");
					store+=(buffer.get(i).getTitle());
					store+=(";");
					store+=(String.valueOf(buffer.get(i).getType()));
					store+=(";");
					store+=(String.valueOf(buffer.get(i).getStartDate()));
					store+=(";");
					store+=(String.valueOf(buffer.get(i).getStartTime()));
					store+=(";");
					store+=(String.valueOf(buffer.get(i).getEndDate()));
					store+=(";");
					store+=(String.valueOf(buffer.get(i).getEndTime()));
					store+=(";");
					store+=(Boolean.toString(buffer.get(i).getIsDone()));
					//store.concat(String.valueOf(buffer.get(i).getAlarm()));
					//tags to be done
					bw.write(store);
					if (i<buffer.size()-1){
						bw.newLine(); 
					}
				}
				bw.close();}
		}catch (IOException e) {
			System.out.println("Exception encountered while saving the textfile");
			System.exit(0);
		}
	}

	public static String getCommandContent(String userInput) {
		return userInput.substring(userInput.indexOf(' ')+1);
	}

	public static CommandType getCommandType(String userInput) {
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
			}else if (userInputSubstring.equalsIgnoreCase("redo")) {
				return CommandType.REDO;
			} else if (userInputSubstring.equalsIgnoreCase("exit")) {
				return CommandType.EXIT;
			} else {
				return CommandType.INVALID;
			}		
		}
	}

	public static String executeCommand(String userInput) {
		CommandType command = getCommandType(userInput);
		if (command != CommandType.UNDO && command != CommandType.REDO ){
			while (!actionFuture.empty()){
				actionFuture.pop();
			}
		}
		switch (command) {
		case ADD_TASK:
			String line[] = getCommandContent(userInput).split("*");
			return addTask(line[0], line[1], Integer.parseInt(line[2]),line[3], line[4]
					, line[5], line[6], Boolean.parseBoolean(line[7]), new Vector<String>());

			//		case EDIT_TASK:
			//			return editTask(getCommandContent(userInput));
			//		case DELETE_TASK:
			//			return deleteTask(getCommandContent(userInput));
			//		case TENTATIVE:
			//			return tentative(getCommandContent(userInput));
			//		case CONFIRM:
			//			return confirm(getCommandContent(userInput))
			//		case DISPLAY:
			//			return display();
			//		case DELETE_ALL:
			//			return DELETE_ALL();
			//		case SORT:
			//			return sort();
			//		case SEARCH:
			//			return search(getCommandContent(userInput));
			//		case STATISTIC:
			//			statistic();
			//			break;
		case UNDO:
			undo();
			break;	
		case REDO:
			redo();
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

	public static String addTask(String id,String title,int type, String startDate,String startTime,
			String endDate,String endTime, boolean isDone, Vector<String> tag) {
		Assignment newAssignment = new Assignment();
		newAssignment.setId(id);
		newAssignment.setTitle(title);
		newAssignment.setType(type);
		newAssignment.setStartDate(startDate);
		newAssignment.setStartTime(startTime);
		newAssignment.setEndDate(endDate);
		newAssignment.setEndTime(endTime);
		newAssignment.setIsDone(isDone);
		//newAssignment.setDescription(description);
		//newAssignment.setAlarm(alarm);
		newAssignment.setTag(tag);
		if ( buffer.isEmpty() ){
			buffer.addLast(newAssignment);
		}
		else{
			//				for (int i = 0; i<buffer.size(); i++){
			//					if (1>2){
			buffer.add(newAssignment);
			//					}
			//				}
		}
		//String confirmation = "added to " + filePath + ": \"" + newAssignment.getTitle() + "\"";
		//return confirmation;
		return newAssignment.toString();
	}

	//TODO	public static String editTask(String commandContent){
	//		
	//	}

	//TODO	public static String deleteTask(String commandContent) {	
	//		int lineNumber=Integer.parseInt(commandContent);
	//		if (lineNumber<1||lineNumber>buffer.size()){
	//			return "Trying to delete invalid line";
	//		}
	//		else{
	//			String stringDeleted="";
	//			stringDeleted=buffer.get(lineNumber-1);
	//			buffer.remove(lineNumber-1);
	//			return ("deleted from " + filePath + ": " + "\"" + stringDeleted + "\"");
	//		}
	//	}

	//TODO	public static String display() {
	//		for(int i=0; i< buffer.size(); i++){
	//			String lineToAdd="";
	//			lineToAdd+=String.valueOf(i+1);
	//			lineToAdd+=". ";
	//			lineToAdd+=buffer.get(i);
	//			System.out.println(lineToAdd);
	//		}
	//
	//		if (getLineCount()==0){
	//			return (filePath + " is empty");
	//		}
	//		else{
	//			return "";
	//		}	
	//	}

	//TODO	public static String DELETE_ALL() {
	//		buffer.clear();
	//		return ("all content deleted from "+ filePath);
	//	}


	//	public static String sort() {
	//TODO
	//	}

	//TODO static String search(String commandContent) {
	//	boolean isFound = false;
	//	for(int i=0; i< buffer.size(); i++){
	//		if (buffer.get(i).toLowerCase().indexOf(commandContent.toLowerCase())>-1){
	//			printToUser(commandContent + " has been found in: " +String.valueOf(i+1)+ ". "+ buffer.get(i));
	//			isFound = true;
	//		}
	//	}
	//	if (isFound){
	//		return "";
	//	}
	//	return (commandContent + " is not found within " + filePath);
	//}

	public static String undo(){

		if (actionHistory.empty()){
			return "Undo Error, no moves to undo";
		}
		else{
			actionFuture.push(actionHistory.pop());
			buffer=actionHistory.peek();
			return "Last action has been undo-ed";
		}
	}

	public static String redo(){

		if (actionFuture.empty()){
			return "Redo Error, no moves to redo";
		}
		else{
			actionHistory.push(actionFuture.pop());
			buffer=actionHistory.peek();
			return "Last action has been redo-ed";
		}
	}


	public static int getLineCount(){
		return buffer.size();
	}

	public static void display(){

		System.out.println( buffer.getFirst().toString());

	}


}
