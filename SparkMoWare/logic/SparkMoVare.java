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

	private static final String MESSAGE_INVALID_SEARCH_PARAMETER = "Invalid search parameter entered";
	
	// These are the possible command types
	enum CommandType {
		ADD_TASK, EDIT_TASK, DELETE_TASK, TENTATIVE, CONFIRM, SORT, SEARCH, 
		DISPLAY, DELETE_ALL, UNDO, REDO, STATISTIC, EXIT, INVALID 
	};

	enum EditType{
		TITLE, START_DATE, START_TIME, END_DATE, END_TIME, INVALID
	}
	
	private static Stack< LinkedList<Assignment>> actionHistory = new Stack< LinkedList<Assignment>>();
	private static Stack< LinkedList<Assignment>> actionFuture = new Stack< LinkedList<Assignment>>();
	private static LinkedList<Assignment> buffer = new LinkedList<Assignment>();
	private static Scanner scanner = new Scanner(System.in);
	private static String filePath = "Storage";

	public static void main(String[] args) {
		printToUser(MESSAGE_WELCOME);
		openFile(filePath);
		ToDoManager();
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
				temp.setId(Integer.parseInt(lineArray[0]));
				temp.setTitle(lineArray[1]);
				temp.setType(Integer.parseInt(lineArray[2]));
				temp.setStartDate(Integer.parseInt(lineArray[2]));
				temp.setStartTime(Integer.parseInt(lineArray[3]));
				temp.setEndDate(Integer.parseInt(lineArray[4]));
				temp.setEndTime(Integer.parseInt(lineArray[5]));
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
					store.concat(String.valueOf(buffer.get(i).getId()));
					store.concat(";");
					store.concat(buffer.get(i).getTitle());
					store.concat(";");
					store.concat(String.valueOf(buffer.get(i).getType()));
					store.concat(";");
					store.concat(String.valueOf(buffer.get(i).getStartDate()));
					store.concat(";");
					store.concat(String.valueOf(buffer.get(i).getStartTime()));
					store.concat(";");
					store.concat(String.valueOf(buffer.get(i).getEndDate()));
					store.concat(";");
					store.concat(String.valueOf(buffer.get(i).getEndTime()));
					store.concat(";");
					store.concat(Boolean.toString(buffer.get(i).getIsDone()));
					//store.concat(String.valueOf(buffer.get(i).getAlarm()));
					//tags to be done
					System.out.println(store);
					bw.write(store);
					if (i<buffer.size()-1){
						bw.newLine(); 
					}
				}	
			}
			bw.close();
		} catch (IOException e) {
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
			return addTask(01, getCommandContent(userInput), 1, 1, 1, 1, false, null);
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

	// in the format of: (int type,String title,int startDate,int endDate,String description,int alarm,
	public static String addTask(int type,String title,int startDate,int startTime,
			int endDate,int endTime, boolean isDone, Vector<String> tag) {
		Assignment newAssignment = new Assignment();
		newAssignment.setType(type);
		newAssignment.setTitle(title);
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
			for (int i = 0; i<buffer.size(); i++){
				if ( buffer.get(i).getEndDate() < newAssignment.getEndDate() ){
					buffer.add(i,newAssignment);
				}
			}
		}

		String confirmation = "added to " + filePath + ": \"" + newAssignment.getTitle() + "\"";
		return confirmation;

	}

	private static String editTask(String commandContent){ //assuming user input is as follows edit <id>
														   //<title/startdate> <new Title/startdate>
		String[] commandContentArray = commandContent.split(" ", 4); //not sure if 4 is accurate
		int bufferPosition = idSearcher(Integer.parseInt(commandContentArray[1]));

		if(bufferPosition==-1){
			return "search is false statement";
		}
		switch(getEditType(commandContentArray[2])){
		case TITLE:
			buffer.get(bufferPosition).setTitle(commandContentArray[3]);
			break;
		case START_DATE:
			buffer.get(bufferPosition).setStartDate(Integer.parseInt(commandContentArray[3]));
			break;
		case START_TIME:
			buffer.get(bufferPosition).setStartTime(Integer.parseInt(commandContentArray[3]));
			break;
		case END_DATE:
			buffer.get(bufferPosition).setEndDate(Integer.parseInt(commandContentArray[3]));
			break;
		case END_TIME:
			buffer.get(bufferPosition).setEndTime(Integer.parseInt(commandContentArray[3]));
			break;
		case INVALID:
			return MESSAGE_INVALID_SEARCH_PARAMETER;
		default:
			return MESSAGE_INVALID_SEARCH_PARAMETER;
		}

		return "statement";
	}
	//returns the position in the buffer of the id
	//returning -1 means does not exist
	private static int idSearcher(int id){ //there should be easier way to search for it
		//such as search for the date first then the id
		for(int i=0; i<buffer.size(); i++){
			if(buffer.get(i).getId()==id){
				return i;
			}
		}
		return -1;
	}

	private static EditType getEditType(String attributeName){
		if (attributeName.length()<1){
			return EditType.INVALID;
		}
		if (attributeName.equalsIgnoreCase("title")){
			return EditType.TITLE;
		}
		else if (attributeName.equalsIgnoreCase("start date")){
			return EditType.START_DATE;
		}
		else if (attributeName.equalsIgnoreCase("start time")){
			return EditType.START_TIME;
		}
		else if (attributeName.equalsIgnoreCase("end date")){
			return EditType.END_DATE;
		}
		else if (attributeName.equalsIgnoreCase("end time")){
			return EditType.END_TIME;
		}
		else{
			return EditType.INVALID;
		}
	}


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


	static int getLineCount(){
		return buffer.size();
	}


}
