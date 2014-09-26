package logic;

import java.util.LinkedList;

public class EditTask {

	//Enum for determining which assignment attribute is being edited
	enum EditType {
		TITLE, START_DATE, START_TIME, END_DATE, END_TIME, INVALID
	}
	
	protected static String editTask(String[] refinedUserInput) {

		LinkedList<Assignment> idFound = new LinkedList<Assignment>();
		idFound = SearchAll.searchAll(refinedUserInput[1]);
		
		if(idFound.size() == 0) {
			SparkMoVare.printToUser(String.format(SparkMoVare.MESSAGE_DOES_NOT_EXISTS, "Serial Number" + refinedUserInput[1]));
			
			return null;//don't do anything since not ID does not exists
		}
		
		int bufferPosition = SparkMoVare.getBufferPosition(refinedUserInput[1]);

		switch(SparkMoVare.getEditType(refinedUserInput[8])) {
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
			
		case INVALID:
			SparkMoVare.printToUser(SparkMoVare.MESSAGE_INVALID_SEARCH_PARAMETER);

		default:
			SparkMoVare.printToUser(SparkMoVare.MESSAGE_INVALID_SEARCH_PARAMETER);
		}
		return null; //This is a stub
	}
}
