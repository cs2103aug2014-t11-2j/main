package logic;

import java.util.*;
import static org.junit.Assert.*;
import logic.Assignment.AssignmentType;

/*
 * Able to search for:
 * ID, Date, Time, Completion, OnTime or Title
 * 
 * It will return a linkedlist back to the system
 */
public class SearchAll {

	private static final int ID_FORMAT_LENGTH = 12;
	private static final int TIME_FORMAT_LENGTH = 4;
	private static final int DATE_FORMAT_LENGTH = 8;
	private static final int TYPE_FORMAT_LENGTH = 4;

	private static int listCount;

	private static final int IS_COMPLETED = 9;
	private static final int IS_ON_TIME = 8;

	private static final String TYPE_TASK = "task";
	private static final String TYPE_APPOINTMENT = "appt";
	//private static final String TYPE_TENTATIVE = "tntv";

	protected static LinkedList<Assignment> searchAll(LinkedList<Assignment> buffer, 
			String userInput){

		LinkedList<Assignment> stringsFound = new LinkedList<Assignment>();

		if(userInput.length() == ID_FORMAT_LENGTH) {
			stringsFound = searchById(buffer, userInput);

		} else if(userInput.length() == TYPE_FORMAT_LENGTH && !userInput.matches("\\d+")) {

			if(userInput.equalsIgnoreCase(TYPE_TASK)) {
				stringsFound = searchByTask(buffer);
			} else if (userInput.equalsIgnoreCase(TYPE_APPOINTMENT)) {
				stringsFound = searchByAppointment(buffer);
			}/* else if (userInput.equalsIgnoreCase(TYPE_TENTATIVE)) {
				stringsFound = searchByTentative(buffer);
			}*/
			assertTrue(userInput.equalsIgnoreCase(TYPE_TASK) || userInput.equalsIgnoreCase(TYPE_APPOINTMENT));

		} else if(userInput.length() == TIME_FORMAT_LENGTH && userInput.matches("\\d+")) {
			stringsFound = searchByTime(buffer, userInput);

		} else if(userInput.length() == DATE_FORMAT_LENGTH && userInput.matches("\\d+")) {
			stringsFound = searchByDate(buffer, userInput);

		} else if(userInput.length() == IS_COMPLETED && userInput.equalsIgnoreCase("completed")) {
			stringsFound = searchByCompletion(buffer);

		} else if(userInput.length() == IS_ON_TIME && userInput.equalsIgnoreCase("isontime")) {
			stringsFound = searchByOnTime(buffer);

		} else if(userInput.equalsIgnoreCase("important")) {
			stringsFound = searchByPriority(buffer);

		} else {
			stringsFound = searchByWords(buffer, userInput);
		}

		if(stringsFound.isEmpty()) {
			Print.printToUser(Message.INVALID_SEARCH_PARAMETER);

			return stringsFound;
		} else {
			return stringsFound;
		}
	}

	private static LinkedList<Assignment> searchByCompletion(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> completionFound = new LinkedList<Assignment>();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getIsDone() == true) {
				completionFound.add(buffer.get(listCount));
			}
		}
		return completionFound;
	}

	private static LinkedList<Assignment> searchByOnTime(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> onTimeFound = new LinkedList<Assignment>();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getIsOnTime() == true) {
				onTimeFound.add(buffer.get(listCount));
			}
		}
		return onTimeFound;
	}

	private static LinkedList<Assignment> searchByPriority(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> priorityFound = new LinkedList<Assignment>();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getPriority().equals("important")) {
				priorityFound.add(buffer.get(listCount));
			}
		}
		return priorityFound;
	}

	private static LinkedList<Assignment> searchByTask(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> taskFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getAssignType().equals(AssignmentType.TASK)) {
				taskFound.add(buffer.get(listCount));
			}
		}
		return taskFound;
	}

	private static LinkedList<Assignment> searchByAppointment(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> appointmentFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentFound.add(buffer.get(listCount));
			}
		}
		return appointmentFound;
	}
	/*
	private static LinkedList<Assignment> searchByTentative(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> tentativeFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getType() == searchTentative) {
				tentativeFound.add(buffer.get(listCount));
			}
		}
		return tentativeFound;
	}
	 */
	private static LinkedList<Assignment> searchById(LinkedList<Assignment> buffer, String searchId) {

		LinkedList<Assignment> idFound = new LinkedList<Assignment>();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getId().equals(searchId)) {
				idFound.add(buffer.get(listCount));
			}
		}
		return idFound;
	}

	// accepts into the searchedList as long as startTime or endTime is the same as input
	private static LinkedList<Assignment> searchByTime(LinkedList<Assignment> buffer, String searchTime) {

		LinkedList<Assignment> timeFound = new LinkedList<Assignment>();

		timeFound.addAll(searchByStartTime(buffer, searchTime));
		timeFound.addAll(searchByEndTime(buffer, searchTime));

		return timeFound;
	}

	private static LinkedList<Assignment> searchByStartTime(LinkedList<Assignment> buffer, 
			String searchStartTime) {

		LinkedList<Assignment> startTimeFound = new LinkedList<Assignment>();
		Appointment appointmentInBuffer = new Appointment();

		for(listCount = 0; listCount < buffer.size(); listCount++) {
			if(buffer.get(listCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInBuffer = ((Appointment) buffer.get(listCount)); 
			}
			if(appointmentInBuffer.getStartTime().equals(searchStartTime)) {
				startTimeFound.add(appointmentInBuffer);
			}
		}
		return startTimeFound;
	}

	private static LinkedList<Assignment> searchByEndTime(LinkedList<Assignment> buffer, 
			String searchEndTime) {

		LinkedList<Assignment> endTimeFound = new LinkedList<Assignment>();
		Appointment appointmentInBuffer = new Appointment();
		Task taskInBuffer = new Task();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInBuffer = ((Appointment) buffer.get(listCount)); 

				if(appointmentInBuffer.getEndTime().equals(searchEndTime)) {
					endTimeFound.add(appointmentInBuffer);
				}
			}
			if(buffer.get(listCount).getAssignType().equals(AssignmentType.TASK)) {
				taskInBuffer = ((Task) buffer.get(listCount)); 

				if(taskInBuffer.getEndTime().equals(searchEndTime)) {
					endTimeFound.add(taskInBuffer);
				}
			}
		}
		return endTimeFound;
	}

	// accepts into the searchedList as long as startDate or endDate is the same as input
	private static LinkedList<Assignment> searchByDate(LinkedList<Assignment> buffer, 
			String searchDate) {

		LinkedList<Assignment> datesFound = new LinkedList<Assignment>();

		datesFound.addAll(searchByStartDate(buffer, searchDate));
		datesFound.addAll(searchByDeadline(buffer, searchDate));

		return datesFound;
	}

	private static LinkedList<Assignment> searchByStartDate(LinkedList<Assignment> buffer, 
			String searchStartDate) {

		LinkedList<Assignment> startDateFound = new LinkedList<Assignment>();
		Appointment appointmentInBuffer = new Appointment();

		for(listCount = 0; listCount < buffer.size(); listCount++) {
			if(buffer.get(listCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInBuffer = ((Appointment) buffer.get(listCount)); 
			}
			if(appointmentInBuffer.getStartTime().equals(searchStartDate)) {
				startDateFound.add(appointmentInBuffer);
			}
		}
		return startDateFound;
	}

	protected static LinkedList<Assignment> searchByDeadline(LinkedList<Assignment> buffer, 
			String searchDeadline) {

		LinkedList<Assignment> deadlineFound = new LinkedList<Assignment>();
		Appointment appointmentInBuffer = new Appointment();
		Task taskInBuffer = new Task();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInBuffer = ((Appointment) buffer.get(listCount)); 

				if(appointmentInBuffer.getEndDate().equals(searchDeadline)) {
					deadlineFound.add(appointmentInBuffer);
				}
			}
			if(buffer.get(listCount).getAssignType().equals(AssignmentType.TASK)) {
				taskInBuffer = ((Task) buffer.get(listCount)); 

				if(taskInBuffer.getEndDate().equals(searchDeadline)) {
					deadlineFound.add(taskInBuffer);
				}
			}
		}
		return deadlineFound;
	}
	/*
	 * Method will check for key phrases or keyword in the title
	 * or
	 * When the searchKey is only a word long or equals to the title
	 */
	private static LinkedList<Assignment> searchByWords(LinkedList<Assignment> buffer, 
			String searchKeyWord) {

		LinkedList<Assignment> keysFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < buffer.size(); listCount++) {
			if(buffer.get(listCount).getTitle().contains(searchKeyWord)) {

				if(buffer.get(listCount).getTitle().equalsIgnoreCase(searchKeyWord)) {
					keysFound.add(buffer.get(listCount));

				} else {
					boolean assignmentFound;
					
					assignmentFound = searchByKeyWord(buffer.get(listCount), searchKeyWord);

					if(assignmentFound) {
						keysFound.add(buffer.get(listCount));
					}
				}
			}
		}
		return keysFound;
	}

	private static boolean searchByKeyWord(Assignment assignment, String searchKeyWord) {
		
		boolean found = false;
		
		String[] textArray = assignment.getTitle().split(" ");

		for(int textCount = 0; textCount < textArray.length; textCount++) {
			String checkText = textArray[textCount];

			if(checkText.equalsIgnoreCase(searchKeyWord)) {
				found = true;
			} else {
				for(int textExtendCount = textCount + 1; textExtendCount < textArray.length; textExtendCount++) {
					
					checkText += " " + textArray[textExtendCount];

					if(checkText.equalsIgnoreCase(searchKeyWord)) {
						found = true;
					}
				}
			}
		}
		return found;
	}
}