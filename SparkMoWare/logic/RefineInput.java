package logic;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class RefineInput {

	protected static Scanner scanner = new Scanner(System.in);
	protected static int size = 0;
	protected static int counter = 0;

	protected static String defaultEndTime = "2359";
	protected static String defaultStartTime = "0000";

	// Fundamentally the same as CommandType in SparkMoVare, but without single
	// word commands
	enum RefinementType {
		ADD, EDIT, DELETE, TENTATIVE, CONFIRM, SORT, SEARCH, CLEAR, INVALID, OTHERS
	}

	// Otherwise known as the Passer. Determines from the user input: command
	// and content, if any.
	protected static void determineUserInput(String userInput) {

		Arrays.fill(SparkMoVare.refinedUserInput, null); // initialises the
															// array for new
															// user input
		String[] userInputArray = userInput.split(";");
		SparkMoVare.refinedUserInput[0] = userInputArray[0];

		switch (getRefinementType(userInput)) {
		case ADD:
			userInputAdd(userInputArray);
			break;

		case EDIT:
			userInputEdit(userInputArray);
			break;

		case DELETE:
			userInputDelete(userInputArray);
			break;

		case TENTATIVE:
			userInputTentative(userInputArray);
			break;

		case CONFIRM:
			userInputConfirm(userInputArray);
			break;

		case CLEAR:
			userInputclear(userInputArray);
			break;

		case SORT:
			userInputSort(userInputArray);
			break;

		case SEARCH:
			userInputSearch(userInputArray);
			break;

		case INVALID:
			SparkMoVare.refinedUserInput[0] = "invalid";
			break;

		case OTHERS:
			// for single commands that require no refinement
		default: // does nothing
		}
	}

	// Checks validity of the user input command
	protected static RefinementType getRefinementType(String userInput) {

		String refinement;
		String[] userInputArray = userInput.split(";");

		if (userInputArray.length > 1) {
			refinement = userInput.substring(0, userInput.indexOf(';'));
		} else {
			refinement = userInput;
		}
		if (refinement.equalsIgnoreCase("add")) {
			if (userInputArray.length < 2) {
				return RefinementType.INVALID;
			}
			return RefinementType.ADD;

		} else if (refinement.equalsIgnoreCase("confirm")) {
			if (userInputArray.length < 6) {
				return RefinementType.INVALID;
			}
			return RefinementType.CONFIRM;

		} else if (refinement.equalsIgnoreCase("delete")) {
			if (userInputArray.length < 2) {
				return RefinementType.INVALID;
			}
			return RefinementType.DELETE;

		} else if (refinement.equalsIgnoreCase("search")) {
			if (userInputArray.length < 2) {
				return RefinementType.INVALID;
			}
			return RefinementType.SEARCH;

		} else if (refinement.equalsIgnoreCase("edit")) {
			if (userInputArray.length < 4) {
				return RefinementType.INVALID;
			}
			return RefinementType.EDIT;

		} else if (refinement.equalsIgnoreCase("clear")) {
			return RefinementType.CLEAR;
		} else if (refinement.equalsIgnoreCase("sort")) {
			return RefinementType.SORT;
		} else {
			return RefinementType.OTHERS;
		}
	}

	/*
	 * Refines the user input into the String[] SparkMoVare.refinedUserInput for
	 * passing on to addCommand() later FATAL ERROR: Cannot cope if title is
	 * longer than a single word. Even if format is <ddmmyyyy><hhmm><title> no
	 * way to determine is user has a title more than 2 words long with one of
	 * the words consisting of only numbers and is leaving the time and/or date
	 * blank. FATAL ERROR: method cannot cope if date or time is left blank.
	 */
	protected static void userInputAdd(String[] userInputArray) {

		SparkMoVare.refinedUserInput[1] = Id.serialNumGen();
		SparkMoVare.refinedUserInput[2] = userInputArray[1];
		SparkMoVare.refinedUserInput[7] = "0";

		if (userInputArray.length == 2) {
			SparkMoVare.refinedUserInput[5] = DateLocal.dateString();
			SparkMoVare.refinedUserInput[6] = defaultEndTime;
		} else if (userInputArray.length == 3) {
			if (TimeLocal.timeFormatValid(userInputArray[2])) {
				SparkMoVare.refinedUserInput[5] = DateLocal.dateString();
				SparkMoVare.refinedUserInput[6] = userInputArray[2];

			} else if (DateLocal.dateFormatValid(userInputArray[2])) {
				SparkMoVare.refinedUserInput[5] = userInputArray[2];
				SparkMoVare.refinedUserInput[6] = defaultEndTime;

			} else {
				SparkMoVare.refinedUserInput[0] = "invalid";
			}
		} else if (userInputArray.length == 4) {
			if (TimeLocal.timeFormatValid(userInputArray[3])) {
				SparkMoVare.refinedUserInput[5] = userInputArray[2];
				SparkMoVare.refinedUserInput[6] = userInputArray[3];

			} else if (DateLocal.dateFormatValid(userInputArray[3])) {
				SparkMoVare.refinedUserInput[3] = userInputArray[2];
				SparkMoVare.refinedUserInput[4] = defaultStartTime;

				SparkMoVare.refinedUserInput[5] = userInputArray[3];
				SparkMoVare.refinedUserInput[6] = defaultEndTime;
				
				SparkMoVare.refinedUserInput[7] = "1";

			} else {
				SparkMoVare.refinedUserInput[0] = "invalid";
			}
		} else if (userInputArray.length == 5) {
			SparkMoVare.refinedUserInput[7] = "1";
			
			if (TimeLocal.timeFormatValid(userInputArray[4])) {
				SparkMoVare.refinedUserInput[3] = userInputArray[2];
				SparkMoVare.refinedUserInput[4] = defaultStartTime;

				SparkMoVare.refinedUserInput[5] = userInputArray[3];
				SparkMoVare.refinedUserInput[6] = userInputArray[4];
				
			} else if (DateLocal.dateFormatValid(userInputArray[4])) {
				SparkMoVare.refinedUserInput[3] = userInputArray[2];
				SparkMoVare.refinedUserInput[4] = userInputArray[3];

				SparkMoVare.refinedUserInput[5] = userInputArray[4];
				SparkMoVare.refinedUserInput[6] = defaultEndTime;
				
			}else{
				SparkMoVare.refinedUserInput[0] = "invalid";
			}
		}else if (userInputArray.length == 6){
			SparkMoVare.refinedUserInput[3] = userInputArray[2];
			SparkMoVare.refinedUserInput[4] = userInputArray[3];

			SparkMoVare.refinedUserInput[5] = userInputArray[4];
			SparkMoVare.refinedUserInput[6] = userInputArray[5];
			
			SparkMoVare.refinedUserInput[7] = "1";
			
		}else{
			SparkMoVare.refinedUserInput[0] = "invalid";
		}
	}

	// Refines the user input into the String[] refinedUserInput for passing on
	// to editCommand() later
	protected static void userInputEdit(String[] userInputArray) { // User must
																	// use S/N

		SparkMoVare.refinedUserInput[1] = Id.determineID(userInputArray[1]);
		/*
		 * if(SparkMoVare.refinedUserInput[1].equalsIgnoreCase("exit")){//Method
		 * for dealing with fatal error SparkMoVare.refinedUserInput[0] =
		 * "invalid"; }
		 */
		SparkMoVare.refinedUserInput[8] = userInputArray[2];

		switch (Edit.getEditType(userInputArray[2])) {
		case TITLE:
			SparkMoVare.refinedUserInput[2] = determineTitle(userInputArray);
			break;

		case START_DATE:
			SparkMoVare.refinedUserInput[3] = userInputArray[3];
			break;

		case START_TIME:
			SparkMoVare.refinedUserInput[4] = userInputArray[3];
			break;

		case END_DATE:
			SparkMoVare.refinedUserInput[5] = userInputArray[3];
			break;

		case END_TIME:
			SparkMoVare.refinedUserInput[6] = userInputArray[3];
			break;

		case INVALID:
			SparkMoVare.refinedUserInput[0] = "invalid";
			break;

		default:
		}
	}

	// For now, this method is simply to refine the content that is to replace
	// the title.
	protected static String determineTitle(String[] userInputArray) {

		size = userInputArray.length;
		String temp = "";

		if (size == 4) {
			return userInputArray[3];
		} else if (size < 4) { // title is blank
			return promptForTitle();
		}

		for (counter = 3; counter < size; counter++) {
			temp.concat(userInputArray[counter]);
			temp.concat(" ");
		}
		return temp.trim();
	}

	// This is to ensure that the user has a title for each respective
	// assignment
	protected static String promptForTitle() {

		String title = "";

		while (title.isEmpty()) {// will continuously prompt user for any input

			Message.printToUser(Message.NO_TITLE);
			Message.printToUser(String.format(Message.FORMAT_PROMPT, "title"));
			title = scanner.nextLine();
		}
		return title;
	}

	// Refines the user input into the String[] refinedUserInput for passing on
	// to deleteCommand() later
	protected static void userInputDelete(String[] userInputArray) {
		SparkMoVare.refinedUserInput[1] = userInputArray[1];
	}

	// Refines the user input into the String[] refinedUserInput for passing on
	// to tentativeCommand() later
	protected static void userInputTentative(String[] userInputArray) {

		SparkMoVare.refinedUserInput[8] = validTentative(userInputArray[1]);
		SparkMoVare.refinedUserInput[2] = userInputArray[2];
		SparkMoVare.refinedUserInput[7] = "2";
	}

	// Check if the number of tentative dates are given in integer format
	protected static String validTentative(String numOfTentative) {

		while (numOfTentative.matches(".*\\D+.*")) {

			Message.printToUser(Message.INVALID_FORMAT);
			Message.printToUser(String.format(Message.FORMAT_PROMPT,
					"number of tentative days"));
			numOfTentative = scanner.nextLine();
		}
		return numOfTentative;
	}

	// Refines the user input into the String[] refinedUserInput for passing on
	// to confirmCommand() later
	protected static void userInputConfirm(String[] userInputArray) {// ASSUMPTION:
																		// array
																		// size
																		// is 6
																		// in
																		// format
																		// <confirm><S/N><ddmmyy><hhmm><ddmmyy><hhmm>

		if (userInputArray.length == 6) {

			SparkMoVare.refinedUserInput[1] = userInputArray[1];
			SparkMoVare.refinedUserInput[3] = DateLocal
					.determineDate(userInputArray[2]);
			SparkMoVare.refinedUserInput[4] = TimeLocal
					.determineTime(userInputArray[3]);			
			SparkMoVare.refinedUserInput[5] = DateLocal
					.determineDate(userInputArray[4]);
			SparkMoVare.refinedUserInput[6] = TimeLocal
					.determineTime(userInputArray[5]);

		} else {
			SparkMoVare.refinedUserInput[0] = "invalid";
		}
	}

	// Refines the user input into the String[] refinedUserInput for passing on
	// to clearCommand() later
	protected static void userInputclear(String[] userInputArray) {

		if (userInputArray.length == 4) {// clear between command

			SparkMoVare.refinedUserInput[8] = userInputArray[1];
			SparkMoVare.refinedUserInput[3] = DateLocal
					.determineDate(userInputArray[2]);
			SparkMoVare.refinedUserInput[5] = DateLocal
					.determineDate(userInputArray[3]);

		} else if (userInputArray.length == 3) {// clear on or before command
			SparkMoVare.refinedUserInput[8] = userInputArray[1];
			SparkMoVare.refinedUserInput[5] = DateLocal
					.determineDate(userInputArray[2]);
		} else {
			SparkMoVare.refinedUserInput[0] = "invalid";
		}
	}

	// Refines the user input into the String[] refinedUserInput for passing on
	// to sortCommand() later
	protected static void userInputSort(String[] userInputArray) {
		SparkMoVare.refinedUserInput[8] = userInputArray[1];
	}

	// Refines the user input into the String[] refinedUserInput for passing on
	// to searchCommand() later
	protected static void userInputSearch(String[] userInputArray) {
		SparkMoVare.refinedUserInput[8] = userInputArray[1];
	}

}
