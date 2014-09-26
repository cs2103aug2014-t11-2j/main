package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class EditTask {


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

		case PRIORITY:
			SparkMoVare.buffer.get(bufferPosition).setPriority(Integer.parseInt(refinedUserInput[8]));
			break;

		case DONE:
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmm");
			Date todayDate = new Date();
			String currentDate = dateFormat.format(todayDate).substring(0, 8);
			String currentTime = dateFormat.format(todayDate).substring(8);
			SparkMoVare.buffer.get(bufferPosition).setIsDone(true);
			
			if (refinedUserInput[5]!=null) {
				currentDate=refinedUserInput[5];
			}
			if (refinedUserInput[6]!=null) {
				currentTime=refinedUserInput[6];
			}
			if (Comparator.dateComparator(currentDate,SparkMoVare.buffer.get(bufferPosition).getEndDate())==-1) {
				SparkMoVare.buffer.get(bufferPosition).setIsOnTime(true);
			} else if (Comparator.dateComparator(currentDate,SparkMoVare.buffer.get(bufferPosition).getEndDate())==0) {
				if (Comparator.timeComparator(currentTime,SparkMoVare.buffer.get(bufferPosition).getEndTime())==-1) {
					SparkMoVare.buffer.get(bufferPosition).setIsOnTime(true);
				}
			}	
			break;
			
		case INVALID:
			SparkMoVare.printToUser(SparkMoVare.MESSAGE_INVALID_SEARCH_PARAMETER);

		default:
			SparkMoVare.printToUser(SparkMoVare.MESSAGE_INVALID_SEARCH_PARAMETER);
		}
		return null; //This is a stub
	}
}
