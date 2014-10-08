package logic;

import java.util.Scanner;

/*
 * This is to refine the input by the user so that everything
 * will be passed on to the methods without much worry
 */
public class UserRefinementInput {

	private static int size = 0;

	private static String defaultEndTime = "2359";
	private static String defaultStartTime = "0000";

	private static Scanner scanner = new Scanner(System.in);

	/*
	 * FATAL ERROR: method cannot cope if date or time is left blank.
	 */
	protected static void userInputAdd(String[] userInputArray) {

		SparkMoVare.refinedUserInput[1] = Id.serialNumGen();
		SparkMoVare.refinedUserInput[2] = userInputArray[1];
		SparkMoVare.refinedUserInput[7] = "0";
		SparkMoVare.refinedUserInput[9] = null;
		boolean containsPriority = false;

		for (String priority : userInputArray) {
			if (priority.equals("important")) {
				SparkMoVare.refinedUserInput[9] = "important";
				containsPriority = true;
			}
		}

		if (userInputArray.length == 2) {
			setEnd(DateLocal.dateString(), defaultEndTime);

		} else if (userInputArray.length == 3) {
			if (TimeLocal.timeFormatValid(userInputArray[2])) {
				setEnd(DateLocal.dateString(), userInputArray[2]);

			} else if (DateLocal.dateFormatValid(userInputArray[2])) {
				setEnd(userInputArray[2], defaultEndTime);

			} else if (containsPriority) {
				setEnd(DateLocal.dateString(), defaultEndTime);

			} else {
				SparkMoVare.refinedUserInput[0] = "invalid";
			}

		} else if (userInputArray.length == 4) {

			if (containsPriority) {
				setEnd(userInputArray[2], defaultEndTime);
			} else {
				if (TimeLocal.timeFormatValid(userInputArray[3])) {
					setEnd(userInputArray[2], userInputArray[3]);

				} else if (DateLocal.dateFormatValid(userInputArray[3])) {
					setStart(userInputArray[2], defaultStartTime);
					setEnd(userInputArray[4], defaultEndTime);

					SparkMoVare.refinedUserInput[7] = "1";

				} else {
					SparkMoVare.refinedUserInput[0] = "invalid";
				}
			}

		} else if (userInputArray.length == 5) {
			SparkMoVare.refinedUserInput[7] = "1";
			if (containsPriority) {
				setStart(userInputArray[2], defaultStartTime);
				setEnd(userInputArray[3], defaultEndTime);
				
			} else {
				if (TimeLocal.timeFormatValid(userInputArray[4])) {
					setStart(userInputArray[2], defaultStartTime);
					setEnd(userInputArray[4], userInputArray[5]);

				} else if (DateLocal.dateFormatValid(userInputArray[4])) {
					setStart(userInputArray[2], userInputArray[3]);
					setEnd(userInputArray[4], defaultEndTime);

				} else {
					SparkMoVare.refinedUserInput[0] = "invalid";
				}
			}
		} else if (userInputArray.length == 6) {
			SparkMoVare.refinedUserInput[7] = "1";
			if(containsPriority){
				if (TimeLocal.timeFormatValid(userInputArray[4])) {
					setStart(userInputArray[2], defaultStartTime);
					setEnd(userInputArray[3], userInputArray[4]);

				} else if (DateLocal.dateFormatValid(userInputArray[4])) {
					setStart(userInputArray[2], userInputArray[3]);
					setEnd(userInputArray[4], defaultEndTime);
				
			} else {
			setStart(userInputArray[2], userInputArray[3]);
			setEnd(userInputArray[4], userInputArray[5]);
			}	
				
		} else if (userInputArray.length == 7);
			setStart(userInputArray[2], userInputArray[3]);
			setEnd(userInputArray[4], userInputArray[5]);
			
		} else {
			SparkMoVare.refinedUserInput[0] = "invalid";
		}
		
	}

	protected static void userInputEdit(String[] userInputArray) {

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

		case PRIORITY:
			SparkMoVare.refinedUserInput[9] = userInputArray[3];

		case DONE:
			break;

		default:
			SparkMoVare.refinedUserInput[0] = "invalid";
			break;
		}
	}

	protected static String determineTitle(String[] userInputArray) {

		size = userInputArray.length;
		String title = "";

		if (size >= 4) {
			title = userInputArray[3];
		} else if (size < 4) { // title is blank
			title = promptForTitle();
		}
		return title;
	}

	// This is to ensure that the user has a title for each respective
	// assignment
	private static String promptForTitle() {

		String title = "";

		while (title.isEmpty()) {// will continuously prompt user for any input

			Print.printToUser(Message.NO_TITLE);
			Print.printToUser(String.format(Message.FORMAT_PROMPT, "title"));
			title = scanner.nextLine();
		}
		return title;
	}

	protected static void userInputDelete(String[] userInputArray) {
		SparkMoVare.refinedUserInput[1] = userInputArray[1];
	}

	protected static void userInputTentative(String[] userInputArray) {

		SparkMoVare.refinedUserInput[8] = validTentative(userInputArray[1]);
		SparkMoVare.refinedUserInput[2] = userInputArray[2];
		SparkMoVare.refinedUserInput[7] = "2";
	}

	// Check if the number of tentative dates are given in integer format
	private static String validTentative(String numOfTentative) {

		while (numOfTentative.matches(".*\\D+.*")) {

			Print.printToUser(Message.INVALID_FORMAT);
			Print.printToUser(String.format(Message.FORMAT_PROMPT,
					"number of tentative days"));
			numOfTentative = scanner.nextLine();
		}
		return numOfTentative;
	}

	// ASSUMPTION: array size is 6 in the format <confirm> <S/N> <ddmmyyyy>
	// <hhmm> <ddmmyyyy> <hhmm>
	protected static void userInputConfirm(String[] userInputArray) {

		if (userInputArray.length == 6) {

			SparkMoVare.refinedUserInput[1] = userInputArray[1];
			setStart(userInputArray[2], userInputArray[3]);
			setEnd(userInputArray[4], userInputArray[5]);

		} else {
			SparkMoVare.refinedUserInput[0] = "invalid";
		}
	}

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
		} else if (userInputArray.length == 1) {
			SparkMoVare.refinedUserInput[8] = userInputArray[0];
		} else {
			SparkMoVare.refinedUserInput[0] = "invalid";
		}
	}

	protected static void userInputSort(String[] userInputArray) {

		SparkMoVare.refinedUserInput[8] = userInputArray[1];

		if (userInputArray.length > 2) {
			SparkMoVare.refinedUserInput[3] = userInputArray[2];
			SparkMoVare.refinedUserInput[5] = userInputArray[3];
		}
	}

	protected static void userInputSearch(String[] userInputArray) {
		SparkMoVare.refinedUserInput[8] = userInputArray[1];
	}

	protected static void userInputFilter(String[] userInputArray) {

		SparkMoVare.refinedUserInput[8] = userInputArray[1];

		if (userInputArray.length > 2) {
			SparkMoVare.refinedUserInput[3] = userInputArray[2];
			SparkMoVare.refinedUserInput[5] = userInputArray[3];
		}
	}

	private static void setStart(String startDate, String startTime) {

		SparkMoVare.refinedUserInput[3] = DateLocal.determineDate(startDate);
		SparkMoVare.refinedUserInput[4] = TimeLocal.determineTime(startTime);
	}

	private static void setEnd(String endDate, String endTime) {

		SparkMoVare.refinedUserInput[5] = DateLocal.determineDate(endDate);
		SparkMoVare.refinedUserInput[6] = TimeLocal.determineTime(endTime);
	}
}