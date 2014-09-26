package logic;

import java.util.LinkedList;

public class EditTask {
	
	private static void editTask(String id) {
		int idIntForm = Integer.parseInt(id);

		LinkedList<Assignment> idFound = new LinkedList<Assignment>(); //this will be equal to the result of the search
		
		//call the SearchAll function using id
		idFound = SearchAll.searchAll(id);

		if(idFound.size() == 0) {
			printToUser(String.format(MESSAGE_DOES_NOT_EXISTS, "Serial Number" + refinedUserInput[1]));
			
			return;//don't do anything since not ID does not exists
		}

		int bufferPosition = idSearcher(idIntForm);

		switch(getEditType(refinedUserInput[8])) {
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

}
