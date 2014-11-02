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
				System.out.println("editing start date1");
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
		} else if (attributeName.equalsIgnoreCase("start date")) {
			return EditType.START_DATE;
		} else if (attributeName.equalsIgnoreCase("start time")) {
			return EditType.START_TIME;
		} else if (attributeName.equalsIgnoreCase("end date")) {
			return EditType.END_DATE;
		} else if (attributeName.equalsIgnoreCase("end time")) {
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
		}else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.TASK)){
			Task appointmentTemp = (Task) InternalStorage.getBuffer().get(bufferPosition);
			appointmentInBuffer.setAssignType(AssignmentType.APPT);
			appointmentInBuffer.setDateCreation(appointmentTemp.getDateCreation());
			appointmentInBuffer.setEndDate(appointmentTemp.getEndDate());
			appointmentInBuffer.setEndTime(appointmentTemp.getEndTime());
			appointmentInBuffer.setStartDate(date);
			appointmentInBuffer.setStartTime("2359");
			appointmentInBuffer.setId(appointmentTemp.getId());
			appointmentInBuffer.setIndex(appointmentTemp.getIndex());
			appointmentInBuffer.setIsDone(appointmentTemp.getIsDone());
			appointmentInBuffer.setIsOnTime(appointmentTemp.getIsOnTime());
			appointmentInBuffer.setNumAppointment(0);
			appointmentInBuffer.setPriority(appointmentTemp.getPriority());
			appointmentInBuffer.setTitle(appointmentTemp.getTitle());
			InternalStorage.getBuffer().set(bufferPosition, appointmentInBuffer);
		}else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.ASGN)){
			Assignment appointmentTemp = (Assignment) InternalStorage.getBuffer().get(bufferPosition);
			appointmentInBuffer.setAssignType(AssignmentType.APPT);
			appointmentInBuffer.setDateCreation(appointmentTemp.getDateCreation());
			appointmentInBuffer.setEndDate(date);
			appointmentInBuffer.setEndTime("2359");
			appointmentInBuffer.setStartDate(date);
			appointmentInBuffer.setStartTime("2359");
			appointmentInBuffer.setId(appointmentTemp.getId());
			appointmentInBuffer.setIndex(appointmentTemp.getIndex());
			appointmentInBuffer.setIsDone(appointmentTemp.getIsDone());
			appointmentInBuffer.setIsOnTime(appointmentTemp.getIsOnTime());
			appointmentInBuffer.setNumAppointment(0);
			appointmentInBuffer.setPriority(appointmentTemp.getPriority());
			appointmentInBuffer.setTitle(appointmentTemp.getTitle());
			InternalStorage.getBuffer().set(bufferPosition, appointmentInBuffer);
		}
	}

	private static void editStartTime(int bufferPosition, String time) {
		Appointment appointmentInBuffer = new Appointment();

		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferPosition));
			appointmentInBuffer.setStartTime(time);
		}else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.TASK)){
			Task appointmentTemp = (Task) InternalStorage.getBuffer().get(bufferPosition);
			appointmentInBuffer.setAssignType(AssignmentType.APPT);
			appointmentInBuffer.setDateCreation(appointmentTemp.getDateCreation());
			appointmentInBuffer.setEndDate(appointmentTemp.getEndDate());
			appointmentInBuffer.setEndTime(appointmentTemp.getEndTime());
			appointmentInBuffer.setStartDate(DateLocal.dateString());
			appointmentInBuffer.setStartTime(time);
			appointmentInBuffer.setId(appointmentTemp.getId());
			appointmentInBuffer.setIndex(appointmentTemp.getIndex());
			appointmentInBuffer.setIsDone(appointmentTemp.getIsDone());
			appointmentInBuffer.setIsOnTime(appointmentTemp.getIsOnTime());
			appointmentInBuffer.setNumAppointment(0);
			appointmentInBuffer.setPriority(appointmentTemp.getPriority());
			appointmentInBuffer.setTitle(appointmentTemp.getTitle());
			InternalStorage.getBuffer().set(bufferPosition, appointmentInBuffer);
		}else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.ASGN)){
			Assignment appointmentTemp = (Assignment) InternalStorage.getBuffer().get(bufferPosition);
			appointmentInBuffer.setAssignType(AssignmentType.APPT);
			appointmentInBuffer.setDateCreation(appointmentTemp.getDateCreation());
			appointmentInBuffer.setEndDate(DateLocal.dateString());
			appointmentInBuffer.setEndTime(time);
			appointmentInBuffer.setStartDate(DateLocal.dateString());
			appointmentInBuffer.setStartTime(time);
			appointmentInBuffer.setId(appointmentTemp.getId());
			appointmentInBuffer.setIndex(appointmentTemp.getIndex());
			appointmentInBuffer.setIsDone(appointmentTemp.getIsDone());
			appointmentInBuffer.setIsOnTime(appointmentTemp.getIsOnTime());
			appointmentInBuffer.setNumAppointment(0);
			appointmentInBuffer.setPriority(appointmentTemp.getPriority());
			appointmentInBuffer.setTitle(appointmentTemp.getTitle());
			InternalStorage.getBuffer().set(bufferPosition, appointmentInBuffer);
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
		}else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.ASGN)){
			Assignment appointmentTemp = (Assignment) InternalStorage.getBuffer().get(bufferPosition);
			taskInBuffer.setAssignType(AssignmentType.TASK);
			taskInBuffer.setDateCreation(appointmentTemp.getDateCreation());
			taskInBuffer.setEndDate(DateLocal.dateString());
			taskInBuffer.setEndTime("2359");
			taskInBuffer.setId(appointmentTemp.getId());
			taskInBuffer.setIndex(appointmentTemp.getIndex());
			taskInBuffer.setIsDone(appointmentTemp.getIsDone());
			taskInBuffer.setIsOnTime(appointmentTemp.getIsOnTime());
			taskInBuffer.setPriority(appointmentTemp.getPriority());
			taskInBuffer.setTitle(appointmentTemp.getTitle());
			InternalStorage.getBuffer().set(bufferPosition, taskInBuffer);
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
		}else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.ASGN)){
			Assignment appointmentTemp = (Assignment) InternalStorage.getBuffer().get(bufferPosition);
			taskInBuffer.setAssignType(AssignmentType.TASK);
			taskInBuffer.setDateCreation(appointmentTemp.getDateCreation());
			taskInBuffer.setEndDate(DateLocal.dateString());
			taskInBuffer.setEndTime("2359");
			taskInBuffer.setId(appointmentTemp.getId());
			taskInBuffer.setIndex(appointmentTemp.getIndex());
			taskInBuffer.setIsDone(appointmentTemp.getIsDone());
			taskInBuffer.setIsOnTime(appointmentTemp.getIsOnTime());
			taskInBuffer.setPriority(appointmentTemp.getPriority());
			taskInBuffer.setTitle(appointmentTemp.getTitle());
			InternalStorage.getBuffer().set(bufferPosition, taskInBuffer);
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