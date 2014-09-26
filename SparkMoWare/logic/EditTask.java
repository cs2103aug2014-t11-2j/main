package logic;

import java.util.LinkedList;

public class EditTask {

	//Enum for determining which assignment attribute is being edited
	enum EditType {
		TITLE, START_DATE, START_TIME, END_DATE, END_TIME, INVALID
	}
	
	protected static String editTask(String[] refinedUserInput) {
		

		LinkedList<Assignment> idFound = new LinkedList<Assignment>(); //this will be equal to the result of the search
		idFound = SearchAll.searchAll(refinedUserInput[1]);
		
		if(idFound.size() == 0) {
			printToUser(String.format(SparkMoVare.MESSAGE_DOES_NOT_EXISTS, "Serial Number" + refinedUserInput[1]));
			
			return null;//don't do anything since not ID does not exists
		}
		
		int idIntForm = Integer.parseInt(refinedUserInput[1]);
		int bufferPosition = SparkMoVare.idSearcher(idIntForm);

		switch(SparkMoVare.getEditType(refinedUserInput[8])) {
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
	return null; //This is a stub
}
