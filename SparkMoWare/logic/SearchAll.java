package logic;

import java.util.*;

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
	private static final int TYPE_FORMAT_LENGTH = 1;

	private static int listCount;

	private static final int IS_COMPLETED = 9;
	private static final int IS_ON_TIME = 8;

	private static final int TYPE_TASK = 0;
	private static final int TYPE_APPOINTMENT = 1;
	private static final int TYPE_TENTATIVE = 2;

	protected static LinkedList<Assignment> searchAll(String userInput){

		LinkedList<Assignment> stringsFound = new LinkedList<Assignment>();

		if(userInput.length() == ID_FORMAT_LENGTH) {
			stringsFound = searchById(userInput);

		} else if(userInput.length() == TYPE_FORMAT_LENGTH && userInput.matches("\\d+")) {

			int assignmentType = Integer.parseInt(userInput);

			if(assignmentType == TYPE_TASK) {
				stringsFound = searchByTask(assignmentType);
			} else if (assignmentType == TYPE_APPOINTMENT) {
				stringsFound = searchByAppointment(assignmentType);
			} else if (assignmentType == TYPE_TENTATIVE) {
				stringsFound = searchByTentative(assignmentType);
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
	
	private static LinkedList<Assignment> searchByTask(int searchTask) {

		LinkedList<Assignment> taskFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getType() == searchTask) {
				taskFound.add(InternalStorage.getBuffer().get(listCount));
			}
		}
		return taskFound;
	}

	private static LinkedList<Assignment> searchByAppointment(int searchAppointment) {

		LinkedList<Assignment> appointmentFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getType() == searchAppointment) {
				appointmentFound.add(InternalStorage.getBuffer().get(listCount));
			}
		}
		return appointmentFound;
	}

	private static LinkedList<Assignment> searchByTentative(int searchTentative) {

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

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getStartTime().equals(searchStartTime)) {
				startTimeFound.add(InternalStorage.getBuffer().get(listCount));
			}
		}
		return startTimeFound;
	}

	private static LinkedList<Assignment> searchByEndTime(String searchEndTime) {
		
		LinkedList<Assignment> endTimeFound = new LinkedList<Assignment>();

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getStartTime().equals(searchEndTime)) {
				endTimeFound.add(InternalStorage.getBuffer().get(listCount));
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

		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getStartDate().equals(searchStartDate)) {
				startDateFound.add(InternalStorage.getBuffer().get(listCount));
			}
		}
		return startDateFound;
	}
	
	protected static LinkedList<Assignment> searchByDeadline(String searchDeadline) {

		LinkedList<Assignment> deadlineFound = new LinkedList<Assignment>();
		
		for(listCount = 0; listCount < InternalStorage.getBuffer().size(); listCount++) {

			if(InternalStorage.getBuffer().get(listCount).getEndDate().equals(searchDeadline)) {
				deadlineFound.add(InternalStorage.getBuffer().get(listCount));
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