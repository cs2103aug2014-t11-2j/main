package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

	private static final String MESSAGE_DOES_NOT_EXISTS = "%1$s does not exists";
	private static final String MESSAGE_INVALID_FORMAT = "Invalid Format";
	private static final String MESSAGE_NO_TITLE = "Title is blank";
	private static final String MESSAGE_FORMAT_PROMPT = "Please type the %1$s again: ";

	private static final String MESSAGE_INVALID_SEARCH_PARAMETER = "Invalid search parameter entered";

	private static final String MESSAGE_STORAGE_FILE_ERROR = "Exception encountered while initalising the Storage file";
	private static final String MESSAGE_SAVE_FILE_ERROR = "Exception encountered while saving the textfile";
	private static final int SYSTEM_EXIT_NO_ERROR = 0;
	private static final int SYSTEM_EXIT_ERROR = 1;

	private static Stack< LinkedList<Assignment>> actionHistory = new Stack< LinkedList<Assignment>>();
	private static Stack< LinkedList<Assignment>> actionFuture = new Stack< LinkedList<Assignment>>();
	private static LinkedList<Assignment> buffer = new LinkedList<Assignment>();
	private static Scanner scanner = new Scanner(System.in);
	private static String filePath = "Storage";
	private static String latestSerialNumber="";

	private static int counter = 0;
	private static int size = 0;

	private static String[] refinedUserInput = new String[9];
	/*each index of refinedUserinput represent something
	 *0:The command string
	 *1:Serial Number (S/N) of the Assignment ASSUMPTION: serial number length is at most 10 digits DD/MM/YY/0000
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

	enum CommandType {
		ADD_TASK, EDIT_TASK, DELETE_TASK, TENTATIVE, CONFIRM, SORT, SEARCH, 
		DISPLAY, DELETE_ALL, UNDO, REDO, STATISTIC, EXIT, INVALID 
	}

	//Fundamentally the same as CommandType, but without single word commands 
	enum RefinementType {
		ADD_TASK, EDIT_TASK, DELETE_TASK, TENTATIVE, CONFIRM, SORT, SEARCH, 
		DELETE_ALL, INVALID, OTHERS
	}

	//Enum for determining which assignment attribute is being edited
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
				//temp.setAlarm(Integer.parseInt(lineArray[7]));
				// tags to be done
				// updates the latest serial number
				if( latestSerialNumber.equals("")){
					latestSerialNumber = lineArray[0];
				}
				else{
					if( SerialNumbercomparator(lineArray[0],latestSerialNumber)){
						latestSerialNumber = lineArray[0];
					}
				}
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
			executeCommand(refinedUserInput[0]);
			actionHistory.add(buffer);
			saveFile(filePath);
		}
	}

	private static void saveFile(String filePath) {
		File file = new File(filePath);
		file.delete();
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0; i< buffer.size(); i++){
				bw.write(buffer.get(i).toString());
				if (i<buffer.size()-1){
					bw.newLine(); 
				}
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("Exception encountered while saving the textfile");
			System.exit(SYSTEM_EXIT_ERROR);
		}
	}

	//Otherwise known as the Passer. Determines from the user input: command and content, if any.
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
			userInputDelete(userInputArray);
			break;
		case TENTATIVE:
			userInputTentative(userInputArray);
			break;
		case CONFIRM:
			userInputConfirm(userInputArray);
			break;
		case DELETE_ALL:
			userInputDeleteAll(userInputArray);
			break;
		case SORT:
			userInputSort(userInputArray);
			break;
		case SEARCH:
			userInputSearch(userInputArray);
			break;
		case INVALID:
			refinedUserInput[0] = "invalid";
			break;
		case OTHERS://for single commands that require no refinement
		default: //does nothing
		}
	}

	//Checks validity of the user input command
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
		else{
			return RefinementType.OTHERS;
		}
	}

	/*Refines the user input into the String[] refinedUserInput for passing on to addCommand() later
	 * FATAL ERROR: Cannot cope if title if longer than a single word.
	 * FATAL ERROR: Even if format is <ddmmyy><hhmm><title> no way to determine is user has a title more than
	 * 2 words long with one of the words consisting of only numbers and is leaving the time and/or date blank.
	 * FATAL ERROR: method cannot cope if date or time is left blank.
	 * 
	 * Yet to add way to determine type. Most likely will involve checking if start date and start time exists.
	 * If exists then assign assignment type appropriately. 0 for task, 1 for appointment, 2 for tentative*/
	public static void userInputAdd(String[] userInputArray){
		refinedUserInput[1] = serialNumGen();
		refinedUserInput[2] = userInputArray[1];

		if(userInputArray.length==4){//ASSUMPTION: format is <add><title><ddmmyy><hhmm>
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

	// only work till Sn 9999 otherwise the year will be corrupted
	// Malfunctions if latestSerialNumber is in the future (in theory should not happen? unless system clock change)
	public static String serialNumGen(){
		String serialNum="";
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		//get current date time with Date()
		Date todayDate = new Date();
		serialNum += dateFormat.format(todayDate);
		serialNum += "0001";
		if(latestSerialNumber.equals("") || SerialNumbercomparator(serialNum,latestSerialNumber)){
			return serialNum;
		}
		else{
			Long Sn = Long.parseLong(latestSerialNumber);
			System.out.println("test" + Sn.toString());
			Sn++;
			return Long.toString(Sn);
		}
	}

	public static String determineDate(String inputDate){
		while(!dateFormatValid(inputDate)){//will continuously prompt user for correct date format currently no way to exit
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
		while(!timeFormatValid(inputTime)){//will continuously prompt user for correct time format currently no way to exit
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

	//Refines the user input into the String[] refinedUserInput for passing on to editCommand() later 
	public static void userInputEdit(String[] userInputArray){ //User must use S/N
		refinedUserInput[1] = determineID(userInputArray[1]);
		/*if(refinedUserInput[1].equalsIgnoreCase("exit")){//Method for dealing with fatal error
			refinedUserInput[0] = "invalid";
		}*/
		refinedUserInput[8] = userInputArray[2];
		switch(getEditType(userInputArray[2])){
		case TITLE:
			refinedUserInput[2] = determineTitle(userInputArray);
			break;
		case START_DATE:
			refinedUserInput[3] = userInputArray[3];
			break;
		case START_TIME:
			refinedUserInput[4] = userInputArray[3];
			break;
		case END_DATE:
			refinedUserInput[5] = userInputArray[3];
			break;
		case END_TIME:
			refinedUserInput[6] = userInputArray[3];
			break;
		case INVALID:
			refinedUserInput[0] = "invalid";
			break;
		default:
		}
	}

	public static String determineID(String id){
		while(!_IDFormatValid(id)){//will continuously prompt user for correct ID format currently no way to exit
			//FATAL ERROR: if user enters edit command while file/program is empty, this prompt will run forever. 
			printToUser(MESSAGE_INVALID_FORMAT);
			printToUser(String.format(MESSAGE_FORMAT_PROMPT, "ID"));		
			id = scanner.nextLine();			
		}
		return id;
	}

	public static boolean _IDFormatValid(String id){
		if(id.length() != 10){
			return false;
		}
		/*else if(id.equalsIgnoreCase("exit")){ //Method for dealing with fatal error
			return true;
		}*/
		else if(id.matches(".*\\D+.*")){
			return false;
		}
		else if(!_IDExists(Integer.parseInt(id))){
			return false;
		}
		else{
			return true;
		}
	}

	public static boolean _IDExists(int id){
		if(dateExists(id/10000)){
			return true;
		}
		else{
			return false;
		}
	}

	private static EditType getEditType(String attributeName){ //ASSUMPTION: user input attribute to change as a single word eg startdate
		if (attributeName.length()<1){
			return EditType.INVALID;
		}
		if (attributeName.equalsIgnoreCase("title")){
			return EditType.TITLE;
		}
		else if (attributeName.equalsIgnoreCase("startdate")){
			return EditType.START_DATE;
		}
		else if (attributeName.equalsIgnoreCase("starttime")){
			return EditType.START_TIME;
		}
		else if (attributeName.equalsIgnoreCase("enddate")){
			return EditType.END_DATE;
		}
		else if (attributeName.equalsIgnoreCase("endtime")){
			return EditType.END_TIME;
		}
		else{
			return EditType.INVALID;
		}
	}

	//For now, this method is simply to refine the content that is to replace the title.
	private static String determineTitle(String [] userInputArray){//What if title is empty?
		size = userInputArray.length;
		if(size==4){
			return userInputArray[3];
		}

		else if(size<4){//title is blank
			return promptForTitle();			
		}

		String temp = new String();
		for(counter=0; counter+3<size; counter++){
			temp.concat(userInputArray[counter]);
			temp.concat(" ");
		}
		return temp.trim();
	}

	private static String promptForTitle(){
		String title = new String();

		while(title.isEmpty()){//will continuously prompt user for any input
			printToUser(MESSAGE_NO_TITLE);
			printToUser(String.format(MESSAGE_FORMAT_PROMPT, "title"));
			title = scanner.nextLine();
		}

		return title;
	}

	//Refines the user input into the String[] refinedUserInput for passing on to deleteCommand() later
	private static void userInputDelete(String[] userInputArray){
		refinedUserInput[1] = userInputArray[1];
	}

	//Refines the user input into the String[] refinedUserInput for passing on to tentativeCommand() later
	private static void userInputTentative(String[] userInputArray){
		refinedUserInput[8] = validTentative(userInputArray[1]);
		refinedUserInput[7] = "2";
	}

	private static String validTentative(String num){
		while(num.matches(".*\\D+.*")){
			printToUser(MESSAGE_INVALID_FORMAT);
			printToUser(String.format(MESSAGE_FORMAT_PROMPT, "number of tentative days"));	
			num = scanner.nextLine();
		}
		return num;
	}

	//Refines the user input into the String[] refinedUserInput for passing on to confirmCommand() later
	private static void userInputConfirm(String[] userInputArray){//ASSUMPTION: array size is 4 in format <confirm><S/N><ddmmyy><hhmm>		
		if(userInputArray.length==4){
			refinedUserInput[1] = userInputArray[1];
			refinedUserInput[3] = determineDate(userInputArray[2]);
			refinedUserInput[4] = determineTime(userInputArray[3]);
		}
		else{
			refinedUserInput[0] = "invalid";
		}
	}

	//Refines the user input into the String[] refinedUserInput for passing on to deleteAllCommand() later
	private static void userInputDeleteAll(String[] userInputArray){
		if(userInputArray.length==4){//deleteall between command
			refinedUserInput[8] = userInputArray[1];
			refinedUserInput[3] = determineDate(userInputArray[2]);
			refinedUserInput[5] = determineDate(userInputArray[3]);
		}
		else if(userInputArray.length==4){//deleteall on or before command
			refinedUserInput[8] = userInputArray[1];
			refinedUserInput[5] = determineDate(userInputArray[2]);
		}
		else{
			refinedUserInput[0] = "invalid";
		}
	}

	//Refines the user input into the String[] refinedUserInput for passing on to sortCommand() later
	private static void userInputSort(String[] userInputArray){
		refinedUserInput[8] = userInputArray[1];
	}

	//Refines the user input into the String[] refinedUserInput for passing on to searchCommand() later
	private static void userInputSearch(String[] userInputArray){
		refinedUserInput[8] = userInputArray[1];
	}

	public static String executeCommand(String inputCommand) {
		CommandType command = getCommandType(inputCommand);
		if (command != CommandType.UNDO && command != CommandType.REDO ){
			while (!actionFuture.empty()){
				actionFuture.pop();
			}
		}
		switch (command) {
		case ADD_TASK:
			return addTask(refinedUserInput[1], refinedUserInput[2], Integer.parseInt(refinedUserInput[7]),
					refinedUserInput[3], refinedUserInput[4], refinedUserInput[5], refinedUserInput[6], 
					false, null);
		case EDIT_TASK:
			editTask();
			//		case DELETE_TASK:
			//			return deleteTask();
			//		case TENTATIVE:
			//			return tentative();
			//		case CONFIRM:
			//			return confirm()
			//		case DISPLAY:
			//			return display();
			//		case DELETE_ALL:
			//			return DELETE_ALL();
			//		case SORT:
			//			return sort();
			//		case SEARCH:
			//			return search();
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
			// to implement insert by deadline
			buffer.add(newAssignment);
		}

		// to implement format
		return newAssignment.toString();
	}

	private static void editTask(){
		int id = Integer.parseInt(refinedUserInput[1]);

		LinkedList<Assignment> idFound = new LinkedList<Assignment>(); //this will be equal to the result of the search
		//call the SearchAll function using id
		if(idFound.size()==0){
			printToUser(String.format(MESSAGE_DOES_NOT_EXISTS, "Serial Number" + refinedUserInput[1]));
			return;//don't do anything since not ID does not exists
		}

		int bufferPosition = idSearcher(id);

		switch(getEditType(refinedUserInput[8])){
		case TITLE:
			buffer.get(bufferPosition).setTitle(refinedUserInput[2]);
			break;
		case START_DATE:
			buffer.get(bufferPosition).setStartDate(refinedUserInput[3]);
			break;
		case START_TIME:
			buffer.get(bufferPosition).setStartTime(refinedUserInput[4]);
			break;
		case END_DATE:
			buffer.get(bufferPosition).setEndDate(refinedUserInput[5]);
			break;
		case END_TIME:
			buffer.get(bufferPosition).setEndTime(refinedUserInput[6]);
			break;
		case INVALID:
			printToUser(MESSAGE_INVALID_SEARCH_PARAMETER);
		default:
			printToUser(MESSAGE_INVALID_SEARCH_PARAMETER);
		}
	}

	//returns the position in the buffer of the id
	private static int idSearcher(int id){ //there should be easier way to search for it such as search for the date first then the id
		size = buffer.size();
		counter = 0;

		while(Integer.parseInt(buffer.get(counter).getId())!=id && counter<size){
			counter++;
		}
		return counter;
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

	// compares id with nextId, return true if id is bigger else return false the format is 250920140001
	static boolean SerialNumbercomparator(String idA, String idB){
		// check year
		if (Integer.parseInt(idA.substring(4, 8))>Integer.parseInt(idB.substring(4, 8))){
			return true;
		}else if (Integer.parseInt(idA.substring(4, 8))<Integer.parseInt(idB.substring(4, 8))){
			return false;
		}
		// check month
		else if (Integer.parseInt(idA.substring(2, 4))>Integer.parseInt(idB.substring(2, 4))){
			return true;
		}else if (Integer.parseInt(idA.substring(2, 4))<Integer.parseInt(idB.substring(2, 4))){
			return false;
		}
		// check day
		else if (Integer.parseInt(idA.substring(0, 2))>Integer.parseInt(idB.substring(0, 2))){
			return true;
		}else if (Integer.parseInt(idA.substring(0, 2))<Integer.parseInt(idB.substring(0, 2))){
			return false;
		}
		//check Sn
		else if (Integer.parseInt(idA.substring(8))>Integer.parseInt(idB.substring(8))){
			return true;
		}
		return false;	
	}

	// for testing purpose

	static int getLineCount(){
		return buffer.size();
	}

	public static void setLatestSerialNumber(String newSn){
		latestSerialNumber=newSn;
	}
}
