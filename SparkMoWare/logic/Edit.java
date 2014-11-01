package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import logic.Assignment.AssignmentType;
import parser.EnumGroup.EditType;
import parser.RefinedUserInput;

/*
 * This edit method will edit any part of the assignment requested by the user
 * It will return to the user whether if the assignment has been edited
 */
public class Edit {

	public static String editAssignment(RefinedUserInput userInput) {

		LinkedList<Assignment> idFound = new LinkedList<Assignment>();
		idFound = SearchAll.searchAll(InternalStorage.getBuffer(), userInput.getId());

		if(idFound.size() == 0) {

			String toUser = String.format(Message.DOES_NOT_EXISTS, "Serial Number " + userInput.getId());

			Print.printToUser(toUser);

			return toUser;
		} else {

			int bufferPosition = InternalStorage.getBufferPosition(userInput.getId());

			switch(getEditType(userInput.getSpecialContent())) {

			case TITLE:
				InternalStorage.getBuffer().get(bufferPosition).setTitle(userInput.getTitle());
				break;

			case START_DATE:
				editStartDate(bufferPosition, userInput.getStartDate());
				break;

			case START_TIME:
				editStartTime(bufferPosition, userInput.getStartTime());
				break;

			case END_DATE:
				editEndDate(bufferPosition, userInput.getEndDate());
				break;

			case END_TIME:
				editEndTime(bufferPosition, userInput.getEndTime());
				break;

			case PRIORITY:
				InternalStorage.getBuffer().get(bufferPosition).setPriority(userInput.getPriority());
				break;

			case DONE:
				editDone(bufferPosition, userInput.getEndDate(), userInput.getEndTime());
				break;

			case INVALID:
				Print.printToUser(Message.INVALID_SEARCH_PARAMETER);
				break;
				
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
		InternalStorage.getBuffer().get(bufferPosition).setIsDone(true);

		if (date != null) {
			currentDate = date;
		}
		if (time != null) {
			currentTime = time;
		}

		setIsOnTime(currentDate,currentTime, bufferPosition);
	}

	private static void setIsOnTime(String currentDate, String currentTime, int bufferPosition) {

		Appointment appointmentInBuffer = new Appointment();
		Task taskInBuffer = new Task();

		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.TASK)) {
			taskInBuffer = ((Task) InternalStorage.getBuffer().get(bufferPosition)); 

			if (Comparator.dateComparator(currentDate, taskInBuffer.getEndDate()) == -1) {
				InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(true);

			} else if (Comparator.dateComparator(currentDate, taskInBuffer.getEndDate()) == 0) {
				if (Comparator.timeComparator(currentTime, taskInBuffer.getEndTime()) == -1) {
					InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(true);
				}
			}
		} else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferPosition)); 

			if (Comparator.dateComparator(currentDate, appointmentInBuffer.getEndDate()) == -1) {
				InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(true);

			} else if (Comparator.dateComparator(currentDate, appointmentInBuffer.getEndDate()) == 0) {
				if (Comparator.timeComparator(currentTime, appointmentInBuffer.getEndTime()) == -1) {
					InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(true);
				}
			}
		} else {
			InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(false);
		}
	}

	private static void editStartDate(int bufferPosition, String date) {
		Appointment appointmentInBuffer = new Appointment();

		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferPosition));
			appointmentInBuffer.setStartDate(date);
		}
	}

	private static void editStartTime(int bufferPosition, String time) {
		Appointment appointmentInBuffer = new Appointment();

		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferPosition));
			appointmentInBuffer.setStartTime(time);
		}
	}

	private static void editEndDate(int bufferPosition, String date) {

		Appointment appointmentInBuffer = new Appointment();
		Task taskInBuffer = new Task();

		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferPosition));
			appointmentInBuffer.setEndDate(date);
		} else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.TASK)) {
			taskInBuffer = ((Task) InternalStorage.getBuffer().get(bufferPosition));
			taskInBuffer.setEndDate(date);
		}
	}

	private static void editEndTime(int bufferPosition, String time) {

		Appointment appointmentInBuffer = new Appointment();
		Task taskInBuffer = new Task();

		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferPosition));
			appointmentInBuffer.setEndTime(time);
		} else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.TASK)) {
			taskInBuffer = ((Task) InternalStorage.getBuffer().get(bufferPosition));
			taskInBuffer.setEndTime(time);
		}
	}
	
	protected static void completeAssignment(String id) {
		
		int bufferPosition;
		
		bufferPosition = InternalStorage.getBufferPosition(id);
		
		InternalStorage.getBuffer().get(bufferPosition).setIsDone(true);
		
		InternalStorage.addBufferFirst(InternalStorage.getBuffer().get(bufferPosition));
		InternalStorage.getBuffer().remove(bufferPosition + 1);
	}
}