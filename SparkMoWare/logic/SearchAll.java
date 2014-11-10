package logic;

import java.util.LinkedList;
import logic.Assignment.AssignmentType;

//@author A0111572R

public class SearchAll {

	private static final int TIME_FORMAT_LENGTH = 4;
	private static final int DATE_FORMAT_LENGTH = 8;

	private static int listCount;

	private static final int IS_COMPLETED = 9;
	private static final int IS_ON_TIME = 8;

	private static final String TYPE_TASK = "task";
	private static final String TYPE_APPOINTMENT = "appointment";
	private static final String TYPE_TENTATIVE = "tentative";
	private static final String TYPE_ASSIGNMENT = "assignment";
	
	protected static LinkedList<Assignment> searchAll(LinkedList<Assignment> buffer, 
			String userInput){

		LinkedList<Assignment> stringsFound = new LinkedList<Assignment>();

		if(userInput.length() == TIME_FORMAT_LENGTH && userInput.matches("\\d+")) {
			stringsFound = searchByTime(buffer, userInput);
			
		} else if(userInput.equalsIgnoreCase("important")) {
			stringsFound = searchByPriority(buffer);

		} else if(userInput.equalsIgnoreCase(TYPE_TASK)) {
			stringsFound = searchByTask(buffer);
			
		} else if (userInput.equalsIgnoreCase(TYPE_APPOINTMENT)) {
			stringsFound = searchByAppointment(buffer);
			
		} else if (userInput.equalsIgnoreCase(TYPE_TENTATIVE)) {
			stringsFound = searchByTentative(buffer);
			
		} else if (userInput.equalsIgnoreCase(TYPE_ASSIGNMENT)) {
			stringsFound = searchByAssignment(buffer);
			
		} else if(userInput.matches("\\d+")) {
			stringsFound = searchById(buffer, Integer.parseInt(userInput));

		} else if(userInput.length() == DATE_FORMAT_LENGTH && userInput.contains("/")) {
			stringsFound = searchByDate(buffer, userInput);

		} else if(userInput.length() == IS_COMPLETED && userInput.equalsIgnoreCase("completed")) {
			stringsFound = searchByCompletion(buffer);

		} else if(userInput.length() == IS_ON_TIME && userInput.equalsIgnoreCase("isontime")) {
			stringsFound = searchByOnTime(buffer);

		} else if(userInput.equalsIgnoreCase("NIMPT")) {
			stringsFound = searchByNonPriority(buffer);
			
		} else {
			stringsFound = searchByWords(buffer, userInput);
		}
		return stringsFound;
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

			if(buffer.get(listCount).getPriority().equals("IMPT")) {
				priorityFound.add(buffer.get(listCount));
			}
		}
		return priorityFound;
	}

	private static LinkedList<Assignment> searchByNonPriority(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> priorityFound = new LinkedList<Assignment>();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getPriority().equals("NIMPT")) {
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

	private static LinkedList<Assignment> searchByAssignment(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> assignmentFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getAssignType().equals(AssignmentType.ASGN)) {
				assignmentFound.add(buffer.get(listCount));
			}
		}
		return assignmentFound;
	}

	private static LinkedList<Assignment> searchByAppointment(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> appointmentFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getAssignType().equals(AssignmentType.APPT)) {
				appointmentFound.add(buffer.get(listCount));
			}
		}
		return appointmentFound;
	}

	private static LinkedList<Assignment> searchByTentative(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> tentativeFound = new LinkedList<Assignment> ();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getAssignType().equals(AssignmentType.TNTV)) {
				tentativeFound.add(buffer.get(listCount));
			}
		}
		return tentativeFound;
	}

	private static LinkedList<Assignment> searchById(LinkedList<Assignment> buffer, int searchId) {

		LinkedList<Assignment> idFound = new LinkedList<Assignment>();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(buffer.get(listCount).getIndex() == searchId) {
				idFound.add(buffer.get(listCount));
			}
		}
		return idFound;
	}

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
			if(buffer.get(listCount).getAssignType().equals(AssignmentType.APPT)) {
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

			if(buffer.get(listCount).getAssignType().equals(AssignmentType.APPT)) {
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
			if(buffer.get(listCount).getAssignType().equals(AssignmentType.APPT)) {
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

			if(buffer.get(listCount).getAssignType().equals(AssignmentType.APPT)) {
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
				found = searchByKeyWord2(textCount, textArray, searchKeyWord, checkText);
			}
		}
		return found;
	}
	
	private static boolean searchByKeyWord2(int textCount, String[] textArray, String searchKeyWord, String checkText) {
		
		boolean found = false;
		
		for(int textExtendCount = textCount + 1; textExtendCount < textArray.length; textExtendCount++) {

			checkText += " " + textArray[textExtendCount];

			if(checkText.equalsIgnoreCase(searchKeyWord)) {
				found = true;
			}
		}
		return found;
	}
}