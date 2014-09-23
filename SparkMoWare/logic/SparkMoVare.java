package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class SparkMoVare {


	// Output Messages
	private static final String MESSAGE_WELCOME = "Welcome to SparkMoWare!";
	private static final String MESSAGE_PROMPT = "command: ";
	private static final String MESSAGE_ADDED = "added to %1$s: \"%2$s\"";
	private static final String MESSAGE_DELETED = "deleted from %1$s: \"%2$s\"";
	private static final String MESSAGE_CLEARED = "all content deleted from %1$s";
	private static final String MESSAGE_EMPTY = "%1$s is empty";
	//private static final String
	
	private static final String MESSAGE_INVALID_FORMAT = "Invalid Format";
	private static final String MESSAGE_FORMAT_PROMPT = "Please type the %1$s again: ";
	
	private static final String MESSAGE_INVALID_SEARCH_PARAMETER = "Invalid search parameter entered";
	
	private static final String MESSAGE_STORAGE_FILE_ERROR = "Exception encountered while initalising the Storage file";
	private static final String MESSAGE_SAVE_FILE_ERROR = "Exception encountered while saving the textfile";
	private static int SYSTEM_EXIT_NO_ERROR = 0;
	private static int SYSTEM_EXIT_ERROR = 1;
	
	private static Stack< LinkedList<Assignment>> actionHistory = new Stack< LinkedList<Assignment>>();
	private static Stack< LinkedList<Assignment>> actionFuture = new Stack< LinkedList<Assignment>>();
	private static LinkedList<Assignment> buffer = new LinkedList<Assignment>();
	private static Scanner scanner = new Scanner(System.in);
	private static String filePath = "Storage";
	
	private static int counter = 0;
	private static int size = 0;
	
	private static String[] refinedUserInput = new String[9];
	/*each index of refinedUserinput represent something
	 *0:The command string
	 *1:Serial Number (S/N) of the Assignment ASSUMPTION: serial number length is at most 4 digits aka 1000
	 *2:Title of the Assignment
	 *3:Start Date
	 *4:Start Time
	 *5:End Date
	 *6:End Time
	 *7:Type: Task(0), Appointment(1) and Tentative(2)
	 *8:Either the command type for delete all (on, before, during) or new content for edit command 
	 */
	
	enum CommandType {
		ADD_TASK, EDIT_TASK, DELETE_TASK, TENTATIVE, CONFIRM, SORT, SEARCH, 
		DISPLAY, DELETE_ALL, UNDO, REDO, STATISTIC, EXIT, INVALID 
	};
	
	//Fundamentally the same as CommandType, but without single word commands 
	enum RefinementType {
		ADD_TASK, EDIT_TASK, DELETE_TASK, TENTATIVE, CONFIRM, SORT, SEARCH, 
		DELETE_ALL, INVALID
	}

	enum EditType {
		TITLE, START_DATE, START_TIME, END_DATE, END_TIME, INVALID
	}

	public static void main(String[] args) {
		printToUser(MESSAGE_WELCOME);
		openFile(filePath);
		toDoManager();
	}
	
	public static void printToUser(String output){
		if (!output.equals("")){ //is the if necessary?
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
			System.exit(SYSTEM_EXIT_ERROR);
		}
	}

	public static void toDoManager() {
		while (true) {
			printToUser(MESSAGE_PROMPT);
			determineUserInput(scanner.nextLine());
			actionHistory.add(buffer);
			saveFile(filePath);
		}
	}
	
	public static void saveFile(String filePath) {
		File file = new File(filePath);
		file.delete();
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String store;
			for(counter=0; counter< buffer.size(); counter++){
				if (counter<buffer.size()-1){
					store="";
					store.concat(String.valueOf(buffer.get(counter).getId()));
					store.concat(";");
					store.concat(buffer.get(counter).getTitle());
					store.concat(";");
					store.concat(String.valueOf(buffer.get(counter).getType()));
					store.concat(";");
					store.concat(String.valueOf(buffer.get(counter).getStartDate()));
					store.concat(";");
					store.concat(String.valueOf(buffer.get(counter).getStartTime()));
					store.concat(";");
					store.concat(String.valueOf(buffer.get(counter).getEndDate()));
					store.concat(";");
					store.concat(String.valueOf(buffer.get(counter).getEndTime()));
					store.concat(";");
					store.concat(Boolean.toString(buffer.get(counter).getIsDone()));
					//store.concat(String.valueOf(buffer.get(counter).getAlarm()));
					//tags to be done
					printToUser(store);
					bw.write(store);
					if (counter<buffer.size()-1){
						bw.newLine(); 
					}
				}	
			}
			bw.close();
		} catch (IOException e) {
			printToUser(MESSAGE_SAVE_FILE_ERROR);
			System.exit(SYSTEM_EXIT_ERROR);
		}
	}

	public static void determineUserInput(String userInput){
		Arrays.fill(refinedUserInput, null); //initialises the array for new user input
		String[] userInputArray = userInput.split(" ");
		refinedUserInput[0] = userInputArray[0];
		switch(getRefinementType(userInput)){
		case ADD_TASK:
			userInputAdd(userInputArray);
			break;
		case EDIT_TASK:
			userInputEdit(userInputArray);
			break;
		case DELETE_TASK:
			break;
		case TENTATIVE:
			break;
		case CONFIRM:
			break;
		case DELETE_ALL:
			break;
		case SORT:
			break;
		case SEARCH:
			break;
		case INVALID:
			refinedUserInput[0] = "invalid";
			break;
		default: //does nothing
		}
	}

	public static RefinementType getRefinementType(String userInput) {
		String refinement;
		
		if (userInput.split(" ").length>1){
			refinement = userInput.substring(0,userInput.indexOf(' '));
		}
		else{
			refinement = userInput;
		}		
		if (refinement.equalsIgnoreCase("add")) {
			if (userInput.split(" ").length<2){
				return RefinementType.INVALID;
			}
			return RefinementType.ADD_TASK;
		}
		else if (refinement.equalsIgnoreCase("confirm")) {
			if (userInput.split(" ").length<2){
				return RefinementType.INVALID;
			}
			return RefinementType.CONFIRM;
		}
		else if (refinement.equalsIgnoreCase("delete")) {
			if (userInput.split(" ").length<2){
				return RefinementType.INVALID;
			}
			return RefinementType.DELETE_TASK;
		}
		else if (refinement.equalsIgnoreCase("search")) {
			if (userInput.split(" ").length<2){
				return RefinementType.INVALID;
			}
			return RefinementType.SEARCH;
		}
		else if (refinement.equalsIgnoreCase("edit")) {
			if (userInput.split(" ").length<2){
				return RefinementType.INVALID;
			}
			return RefinementType.EDIT_TASK;
		}
		else if (refinement.equalsIgnoreCase("deleteall")) { //2 words or 1 word?
			return RefinementType.DELETE_ALL;
		}
		else if (refinement.equalsIgnoreCase("sort")) {
			return RefinementType.SORT;
		}
		else {
			return RefinementType.INVALID;
		}		
	}

	public static void userInputAdd(String[] userInputArray){
		if(userInputArray.length==4){
			refinedUserInput[5] = determineDate(userInputArray[2]);
			refinedUserInput[6] = determineTime(userInputArray[3]);
		}
		else if(userInputArray.length==3 && userInputArray[2].length()==6){ //last input is date
			refinedUserInput[5] = determineDate(userInputArray[2]);
		}
		else if(userInputArray.length==3 && userInputArray[2].length()==4){ //last input is time
			refinedUserInput[6] = determineDate(userInputArray[2]);
		}		
		else{
			refinedUserInput[0] = "invalid";
			
			//currently change command input to invalid
			
			//what to do here? It's not possible to prompt the user to change the input.
			//It will go back into the determineUserInput method.
			//Exit the system, exception/error handling or is there a way to fix this?

		}
	}

	public static String determineDate(String inputDate){
		while(!dateFormatValid(inputDate)){
			printToUser(MESSAGE_INVALID_FORMAT);
			printToUser(String.format(MESSAGE_FORMAT_PROMPT, "date"));
			inputDate = scanner.nextLine();
		}
		return inputDate;
	}

	public static boolean dateFormatValid(String date){
		if(date.length()!=6){
			return false;
		}
		else if(date.matches(".*\\D+.*")){ //not sure if this checks if there are any chars in the input
			return false;
		}
		else if(!dateExists(Integer.parseInt(date))){
			return false;
		}
		else{
			return true;
		}
	}

	public static boolean dateExists(int date){
		boolean leapYear = false;
		int day = date/10000;
		int month = (date/100)%100;

		if(date%4==0){
			leapYear = true;
		}
		if(month>12 || month<1){
			return false;
		}

		if(day<29){
			return true;
		}
		else if(day==29 && month==02 && leapYear){
			return true;
		}
		else if(day==30 && month!=2){
			return true;
		}
		else if(day == 31 && month!=2  && month!=3 && month!=6 && month!=9 && month!=11){ //definitely a way to shorten this
			return true;
		}
		return false;	
	}

	public static String determineTime(String inputTime){
		while(!timeFormatValid(inputTime)){
			printToUser(MESSAGE_INVALID_FORMAT);
			printToUser(String.format(MESSAGE_FORMAT_PROMPT, "time"));
			inputTime = scanner.nextLine();			
		}
		return inputTime;
	}

	public static boolean timeFormatValid(String time){

		if(time.length()!=4){
			return false;
		}
		else if(time.matches(".*\\D+.*")){
			return false;
		}
		else if(!timeExists(Integer.parseInt(time))){ //hex or decimal format should not matter
			return false;
		}
		return true;
	}

	public static boolean timeExists(int time){
		int min = time%100;
		int hr = time%100;

		if(min>59){
			return false;
		}
		else if(hr>23){
			return false;
		}
		return true;
	}

	public static void userInputEdit(String[] userInputArray){ //User must use S/N
		refinedUserInput[1] = determineID(userInputArray[1]);
		switch(getEditType(refinedUserInput[2])){
		case TITLE:
			break;
		case START_DATE:
			break;
		case START_TIME:
			break;
		case END_DATE:
			break;
		case END_TIME:
			break;
		case INVALID:
			refinedUserInput[0] = "invalid";
			break;
		default:
		}
	}
			
	private static EditType getEditType(String attributeName){
		if (attributeName.length()<1){
			return EditType.INVALID;
		}
		if (attributeName.equalsIgnoreCase("title")){
			return EditType.TITLE;
		}
		else if (attributeName.equalsIgnoreCase("start date")){ //2 words?
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

	public static String determineID(String ID){
		while(!_IDFormatValid(ID)){
			printToUser(MESSAGE_INVALID_FORMAT);
			printToUser(String.format(MESSAGE_FORMAT_PROMPT, "ID"));		
			ID = scanner.nextLine();			
		}
		return ID;
	}
	
	public static boolean _IDFormatValid(String ID){

		if(ID.length() != 10){
			return false;
		}
		else if(ID.matches(".*\\D+.*")){
			return false;
		}
		else if(!_IDExists(Integer.parseInt(ID))){
			return false;
		}
		else{
			return true;
		}
	}

	public static boolean _IDExists(int ID){
		if(dateExists(ID/10000)){
			return true;
		}
		else{
			return false;
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
			System.exit(SYSTEM_EXIT_NO_ERROR);
			break;
		case INVALID:
			return "Invalid Command issued!";
		default:
			return "Invalid Command issued!";
		}
		return "";
	}

	public static String determineTitle(String[] userInputArray){
		size = userInputArray.length;
		String title = userInputArray[3];
		
		while(!titleExists(title)){
			printToUser(MESSAGE_INVALID_FORMAT);
			printToUser(String.format(MESSAGE_FORMAT_PROMPT, "Title"));		
			title = scanner.nextLine();				
		}
		
		//issue, what if new title input just above is more than one word?
		//need to split again
		
		for(counter=1; counter+3<size; counter++){
			title.concat(userInputArray[counter+3]);
		}
		return title;
	}

	public  static boolean titleExists(String title){
		if(title.length()==0){
			return false;
		}
		return true;
	}
	
	public static String getCommandContent(String userInput) {
		return userInput.substring(userInput.indexOf(' ')+1);
	}

	public static CommandType getCommandType(String userInput) {
		if (userInput.split(" ").length<1){
			return CommandType.INVALID;
		}
		String command;
		if (userInput.split(" ").length>1){
			command = userInput.substring(0,userInput.indexOf(' '));
		}
		else{
			command = userInput;
		}		
		if (command.equalsIgnoreCase("add")) {
			if (userInput.split(" ").length<2){
				return CommandType.INVALID;
			}
			return CommandType.ADD_TASK;
		}
		else if (command.equalsIgnoreCase("confirm")) {
			if (userInput.split(" ").length<2){
				return CommandType.INVALID;
			}
			return CommandType.CONFIRM;
		}
		else if (command.equalsIgnoreCase("delete")) {
			if (userInput.split(" ").length<2){
				return CommandType.INVALID;
			}
			return CommandType.DELETE_TASK;
		}
		else if (command.equalsIgnoreCase("search")) {
			if (userInput.split(" ").length<2){
				return CommandType.INVALID;
			}
			return CommandType.SEARCH;
		}
		else if (command.equalsIgnoreCase("edit")) {
			if (userInput.split(" ").length<2){
				return CommandType.INVALID;
			}
			return CommandType.EDIT_TASK;
		}
		else if (command.equalsIgnoreCase("display")) {
			return CommandType.DISPLAY;
		}
		else if (command.equalsIgnoreCase("deleteall")) { //2 words or 1 word?
			return CommandType.DELETE_ALL;
		}
		else if (command.equalsIgnoreCase("sort")) {
			return CommandType.SORT;
		}
		else if (command.equalsIgnoreCase("statistic")) {
			return CommandType.STATISTIC;
		} 
		else if (command.equalsIgnoreCase("undo")) {
			return CommandType.UNDO;
		}
		else if (command.equalsIgnoreCase("redo")) {
			return CommandType.REDO;
		}
		else if (command.equalsIgnoreCase("exit")) {
			return CommandType.EXIT;
		}
		else {
			return CommandType.INVALID;
		}		
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
			for (counter = 0; counter<buffer.size(); counter++){
				if ( buffer.get(counter).getEndDate() < newAssignment.getEndDate() ){
					buffer.add(counter,newAssignment);
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
			buffer.get(bufferPosition).setStartDate(commandContentArray[3]);
			break;
		case START_TIME:
			buffer.get(bufferPosition).setStartTime(commandContentArray[3]);
			break;
		case END_DATE:
			buffer.get(bufferPosition).setEndDate(commandContentArray[3]);
			break;
		case END_TIME:
			buffer.get(bufferPosition).setEndTime(commandContentArray[3]);
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
		for(counter=0; counter<buffer.size(); counter++){
			if(buffer.get(counter).getId()==id){
				return counter;
			}
		}
		return -1;
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
