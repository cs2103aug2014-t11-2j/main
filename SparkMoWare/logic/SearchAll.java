package logic;

import java.util.*;

import parser.EnumGroup.AssignmentType;

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
	private static final String TYPE_TENTATIVE = "tntv";

	protected static LinkedList<Assignment> searchAll(String userInput){

		LinkedList<Assignment> stringsFound = new LinkedList<Assignment>();

		if(userInput.length() == ID_FORMAT_LENGTH) {
			stringsFound = searchById(userInput);

		} else if(userInput.length() == TYPE_FORMAT_LENGTH && !userInput.matches("\\d+")) {

			if(userInput.equalsIgnoreCase(TYPE_TASK)) {
				stringsFound = searchByTask();
			} else if (userInput.equalsIgnoreCase(TYPE_APPOINTMENT)) {
				stringsFound = searchByAppointment();
			} else if (userInput.equalsIgnoreCase(TYPE_TENTATIVE)) {
				stringsFound = searchByTentative();
			}
		} else if(userInput.length() == TIME_FORMAT_LENGTH && userInput.matches("\\d+")) {
			stringsFound = searchByTime(userInput);

		} else if(userInput.length() == DATE_FORMAT_LENGTH && userInput.matches("\\d+")) {
			stringsFound = searchByDate(userInput);

		} else if(userInput.length() == IS_COMPLETED && userInput.equalsIgnoreCase("completed")) {
			stringsFound = searchByCompletion();

		} else if(userInput.length() == IS_ON_TIME && userInput.equalsIgnoreCase("isontime")) {
			stringsFound = searchByOnTime();

		} else if(userInput.equalsIgnoreCase("important")) {
			stringsFound = searchByPriority();

		} else {
			stringsFound = searchByWords(userInput);
		}

		if(stringsFound.isEmpty()) {
			Print.printToUser(Message.INVALID_SEARCH_PARAMETER);

			return stringsFound;
		} else {
			return stringsFound;
		}
	}

	private static LinkedList<Assignment> searchByCompletion() {

		LinkedList<Assignment> completionFound = new LinkedList<Assignment>();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getIsDone() == true) {
				completionFound.add(InternalStorage.getBuffer().get(listCount));
			}
		}
		return completionFound;
	}

	private static LinkedList<Assignment> searchByOnTime() {

		LinkedList<Assignment> onTimeFound = new LinkedList<Assignment>();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getIsOnTime() == true) {
				onTimeFound.add(InternalStorage.getBuffer().get(listCount));
			}
		}
		return onTimeFound;
	}

	private static LinkedList<Assignment> searchByPriority() {

		LinkedList<Assignment> priorityFound = new LinkedList<Assignment>();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {
			try{
				if(InternalStorage.getBuffer().get(listCount).getPriority().equals("important")) {
					priorityFound.add(InternalStorage.getBuffer().get(listCount));
				}
			}catch(NullPointerException e){

			}
		}
		return priorityFound;
	}

	private static LinkedList<Assignment> searchByTask() {

		LinkedList<Assignment> taskFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getAssignType().equals(AssignmentType.TASK)) {
				taskFound.add(InternalStorage.getBuffer().get(listCount));
			}
		}
		return taskFound;
	}

	private static LinkedList<Assignment> searchByAppointment() {

		LinkedList<Assignment> appointmentFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentFound.add(InternalStorage.getBuffer().get(listCount));
			}
		}
		return appointmentFound;
	}

	private static LinkedList<Assignment> searchByTentative() {

		LinkedList<Assignment> tentativeFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getType() == searchTentative) {
				tentativeFound.add(InternalStorage.getBuffer().get(listCount));
			}
		}
		return tentativeFound;
	}

	private static LinkedList<Assignment> searchById(String searchId) {

		LinkedList<Assignment> idFound = new LinkedList<Assignment>();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getId().equals(searchId)) {
				idFound.add(InternalStorage.getBuffer().get(listCount));
			}
		}
		return idFound;
	}

	// accepts into the searchedList as long as startTime or endTime is the same as input
	private static LinkedList<Assignment> searchByTime(String searchTime) {

		LinkedList<Assignment> timeFound = new LinkedList<Assignment>();

		timeFound.addAll(searchByStartTime(searchTime));
		timeFound.addAll(searchByEndTime(searchTime));

		return timeFound;
	}

	private static LinkedList<Assignment> searchByStartTime(String searchStartTime) {

		LinkedList<Assignment> startTimeFound = new LinkedList<Assignment>();
		Appointment appointmentInBuffer = new Appointment();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {
			if(InternalStorage.getBuffer().get(listCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(listCount)); 
			}
			if(appointmentInBuffer.getStartTime().equals(searchStartTime)) {
				startTimeFound.add(appointmentInBuffer);
			}
		}
		return startTimeFound;
	}

	private static LinkedList<Assignment> searchByEndTime(String searchEndTime) {

		LinkedList<Assignment> endTimeFound = new LinkedList<Assignment>();
		Appointment appointmentInBuffer = new Appointment();
		Task taskInBuffer = new Task();
		
		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(listCount)); 

				if(appointmentInBuffer.getEndTime().equals(searchEndTime)) {
					endTimeFound.add(appointmentInBuffer);
				}
			}
			if(InternalStorage.getBuffer().get(listCount).getAssignType().equals(AssignmentType.TASK)) {
				taskInBuffer = ((Task) InternalStorage.getBuffer().get(listCount)); 

				if(taskInBuffer.getEndTime().equals(searchEndTime)) {
					endTimeFound.add(taskInBuffer);
				}
			}
		}
		return endTimeFound;
	}

	// accepts into the searchedList as long as startDate or endDate is the same as input
	private static LinkedList<Assignment> searchByDate(String searchDate) {

		LinkedList<Assignment> datesFound = new LinkedList<Assignment>();

		datesFound.addAll(searchByStartDate(searchDate));
		datesFound.addAll(searchByDeadline(searchDate));

		return datesFound;
	}

	private static LinkedList<Assignment> searchByStartDate(String searchStartDate) {

		LinkedList<Assignment> startDateFound = new LinkedList<Assignment>();
		Appointment appointmentInBuffer = new Appointment();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {
			if(InternalStorage.getBuffer().get(listCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(listCount)); 
			}
			if(appointmentInBuffer.getStartTime().equals(searchStartDate)) {
				startDateFound.add(appointmentInBuffer);
			}
		}
		return startDateFound;
	}

	protected static LinkedList<Assignment> searchByDeadline(String searchDeadline) {

		LinkedList<Assignment> deadlineFound = new LinkedList<Assignment>();
		Appointment appointmentInBuffer = new Appointment();
		Task taskInBuffer = new Task();
		
		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(listCount)); 

				if(appointmentInBuffer.getEndDate().equals(searchDeadline)) {
					deadlineFound.add(appointmentInBuffer);
				}
			}
			if(InternalStorage.getBuffer().get(listCount).getAssignType().equals(AssignmentType.TASK)) {
				taskInBuffer = ((Task) InternalStorage.getBuffer().get(listCount)); 

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
	private static LinkedList<Assignment> searchByWords(String searchKeyWord) {

		LinkedList<Assignment> keysFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {
			if(InternalStorage.getBuffer().get(listCount).getTitle().contains(searchKeyWord)) {

				if(InternalStorage.getBuffer().get(listCount).getTitle().equalsIgnoreCase(searchKeyWord)) {
					keysFound.add(InternalStorage.getBuffer().get(listCount));

				} else {
					Assignment assignmentFound = new Assignment();
					assignmentFound = searchByKeyWord(searchKeyWord, listCount);

					if(assignmentFound != null) {
						keysFound.add(assignmentFound);
					}
				}
			}
		}
		return keysFound;
	}

	private static Assignment searchByKeyWord(String searchKeyWord, int listCount) {

		String[] textArray = InternalStorage.getBuffer().get(listCount).getTitle().split(" ");

		for(int textCount = 0; textCount < textArray.length; textCount++) {
			String checkText = textArray[textCount];


			if(checkText.equalsIgnoreCase(searchKeyWord)) {
				return InternalStorage.getBuffer().get(listCount);
			} else {
				for(int textExtendCount = textCount + 1; textExtendCount < textArray.length; textExtendCount++) {
					checkText += " " + textArray[textExtendCount];

					if(checkText.equalsIgnoreCase(searchKeyWord)) {
						return InternalStorage.getBuffer().get(listCount);
					}
				}
			}
		}
		return null;
	}
}