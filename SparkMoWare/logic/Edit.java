package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/*
 * This edit method will edit any part of the assignment requested by the user
 * It will return to the user whether if the assignment has been edited
 */
public class Edit {
	
	//Enum for determining which assignment attribute is being edited
	enum EditType {
		TITLE, START_DATE, START_TIME, END_DATE, END_TIME, INVALID, PRIORITY, DONE
	}
	
	protected static String editAssignment(String[] refinedUserInput) {

		LinkedList<Assignment> idFound = new LinkedList<Assignment>();
		idFound = SearchAll.searchAll(refinedUserInput[1]);

		if(idFound.size() == 0) {
			
			String toUser = String.format(Message.DOES_NOT_EXISTS, "Serial Number " + refinedUserInput[1]);
			
			Print.printToUser(toUser);

			return toUser;
		} else {

			int bufferPosition = SparkMoVare.getBufferPosition(refinedUserInput[1]);

			switch(getEditType(refinedUserInput[8])) {

			case TITLE:
				SparkMoVare.buffer.get(bufferPosition).setTitle(refinedUserInput[2]);
				break;

			case START_DATE:
				SparkMoVare.buffer.get(bufferPosition).setStartDate(refinedUserInput[3]);
				break;

			case START_TIME:
				SparkMoVare.buffer.get(bufferPosition).setStartTime(refinedUserInput[4]);
				break;

			case END_DATE:
				SparkMoVare.buffer.get(bufferPosition).setEndDate(refinedUserInput[5]);
				break;

			case END_TIME:
				SparkMoVare.buffer.get(bufferPosition).setEndTime(refinedUserInput[6]);
				break;

			case PRIORITY:
				SparkMoVare.buffer.get(bufferPosition).setPriority(Integer.parseInt(refinedUserInput[8]));
				break;

			case DONE:
				editDone(bufferPosition, refinedUserInput[5], refinedUserInput[6]);
				break;

			case INVALID:
				Print.printToUser(Message.INVALID_SEARCH_PARAMETER);

			default:
				Print.printToUser(Message.INVALID_SEARCH_PARAMETER);
			}
			return Message.EDITED; 
		}
	}
	
	// ASSUMPTION: user input attribute to change as a single word eg startdate
	protected static EditType getEditType(String attributeName) { 
		
		if (attributeName.length() < 1) {
			return EditType.INVALID;
		}
		if (attributeName.equalsIgnoreCase("title")) {
			return EditType.TITLE;
		} else if (attributeName.equalsIgnoreCase("startdate")) {
			return EditType.START_DATE;
		} else if (attributeName.equalsIgnoreCase("starttime")) {
			return EditType.START_TIME;
		} else if (attributeName.equalsIgnoreCase("enddate")) {
			return EditType.END_DATE;
		} else if (attributeName.equalsIgnoreCase("endtime")) {
			return EditType.END_TIME;
		} else if (attributeName.equalsIgnoreCase("priority")) {
			return EditType.PRIORITY;
		} else if(attributeName.equalsIgnoreCase("done")) {
			return EditType.DONE;
		} else {
			return EditType.INVALID;
		}
	}
	
	private static void editDone(int bufferPosition,String date, String time) {
		
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmm");
		Date todayDate = new Date();
		
		String currentDate = dateFormat.format(todayDate).substring(0, 8);
		String currentTime = dateFormat.format(todayDate).substring(8);
		SparkMoVare.buffer.get(bufferPosition).setIsDone(true);

		if (date != null) {
			currentDate = date;
		}
		if (time != null) {
			currentTime = time;
		}
		
		setIsOnTime(currentDate,currentTime, bufferPosition);
	}
	
	private static void setIsOnTime(String currentDate, String currentTime, int bufferPosition) {
		
		if (Comparator.dateComparator(currentDate, SparkMoVare.buffer.get(bufferPosition).getEndDate()) == -1) {
			SparkMoVare.buffer.get(bufferPosition).setIsOnTime(true);
			
		} else if (Comparator.dateComparator(currentDate,SparkMoVare.buffer.get(bufferPosition).getEndDate()) == 0) {
			if (Comparator.timeComparator(currentTime,SparkMoVare.buffer.get(bufferPosition).getEndTime()) == -1) {
				SparkMoVare.buffer.get(bufferPosition).setIsOnTime(true);
			}
		}
	}
}