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
	
	private static final String DEFAULT_TIME = "2359";
	
	public static String editAssignment(RefinedUserInput userInput) {

		LinkedList<Assignment> idFound = new LinkedList<Assignment>();
		idFound = SearchAll.searchAll(InternalStorage.getBuffer(), Integer.toString(userInput.getIndex()));

		if(idFound.size() == 0) {

			String toUser = String.format(Message.DOES_NOT_EXISTS, "Serial Number " + userInput.getIndex());

			return toUser;
		} else {

			int bufferPosition = InternalStorage.getBufferPosition(userInput.getIndex());
			
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
		} else {
			return EditType.INVALID;
		}
	}

	private static void editStartDate(int bufferPosition, String date) {
		
		Appointment appointmentInBuffer = new Appointment();
		
		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferPosition));
			appointmentInBuffer.setStartDate(date);
			
		} else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.TASK)) {
			
			Task appointmentTemp = ((Task) InternalStorage.getBuffer().remove(bufferPosition));
			
			appointmentInBuffer.setEndDate(appointmentTemp.getEndDate());
			appointmentInBuffer.setEndTime(appointmentTemp.getEndTime());
			appointmentInBuffer.setStartDate(date);
			appointmentInBuffer.setStartTime(DEFAULT_TIME);
			appointmentInBuffer.setIndex(appointmentTemp.getIndex());
			appointmentInBuffer.setIsDone(appointmentTemp.getIsDone());
			appointmentInBuffer.setIsOnTime(appointmentTemp.getIsOnTime());
			appointmentInBuffer.setPriority(appointmentTemp.getPriority());
			appointmentInBuffer.setTitle(appointmentTemp.getTitle());
			
			Add.addAppointmentToBuffer(appointmentInBuffer);
			
		} else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.ASGN)) {
			
			Assignment appointmentTemp = InternalStorage.getBuffer().remove(bufferPosition);

			appointmentInBuffer.setEndDate(date);
			appointmentInBuffer.setEndTime(DEFAULT_TIME);
			appointmentInBuffer.setStartDate(date);
			appointmentInBuffer.setStartTime(DEFAULT_TIME);
			appointmentInBuffer.setIndex(appointmentTemp.getIndex());
			appointmentInBuffer.setIsDone(appointmentTemp.getIsDone());
			appointmentInBuffer.setIsOnTime(appointmentTemp.getIsOnTime());
			appointmentInBuffer.setPriority(appointmentTemp.getPriority());
			appointmentInBuffer.setTitle(appointmentTemp.getTitle());
			
			Add.addAppointmentToBuffer(appointmentInBuffer);
		}
	}

	private static void editStartTime(int bufferPosition, String time) {
		
		Appointment appointmentInBuffer = new Appointment();

		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferPosition));
			appointmentInBuffer.setStartTime(time);
			
		} else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.TASK)) {
			Task appointmentTemp = ((Task) InternalStorage.getBuffer().remove(bufferPosition));

			appointmentInBuffer.setEndDate(appointmentTemp.getEndDate());
			appointmentInBuffer.setEndTime(appointmentTemp.getEndTime());
			appointmentInBuffer.setStartDate(DateLocal.dateString());
			appointmentInBuffer.setStartTime(time);
			appointmentInBuffer.setIndex(appointmentTemp.getIndex());
			appointmentInBuffer.setIsDone(appointmentTemp.getIsDone());
			appointmentInBuffer.setIsOnTime(appointmentTemp.getIsOnTime());
			appointmentInBuffer.setPriority(appointmentTemp.getPriority());
			appointmentInBuffer.setTitle(appointmentTemp.getTitle());
			
			Add.addAppointmentToBuffer(appointmentInBuffer);	
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
			
			Assignment appointmentTemp = (Assignment) InternalStorage.getBuffer().remove(bufferPosition);

			taskInBuffer.setEndDate(date);
			taskInBuffer.setEndTime(DEFAULT_TIME);
			taskInBuffer.setIndex(appointmentTemp.getIndex());
			taskInBuffer.setIsDone(appointmentTemp.getIsDone());
			taskInBuffer.setIsOnTime(appointmentTemp.getIsOnTime());
			taskInBuffer.setPriority(appointmentTemp.getPriority());
			taskInBuffer.setTitle(appointmentTemp.getTitle());
			
			Add.addTaskToBuffer(taskInBuffer);
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
			
			Assignment appointmentTemp = (Assignment) InternalStorage.getBuffer().remove(bufferPosition);

			taskInBuffer.setEndDate(DateLocal.dateString());
			taskInBuffer.setEndTime(time);
			taskInBuffer.setIndex(appointmentTemp.getIndex());
			taskInBuffer.setIsDone(appointmentTemp.getIsDone());
			taskInBuffer.setIsOnTime(appointmentTemp.getIsOnTime());
			taskInBuffer.setPriority(appointmentTemp.getPriority());
			taskInBuffer.setTitle(appointmentTemp.getTitle());
			
			Add.addTaskToBuffer(taskInBuffer);
		}
	}
	
	protected static int completeAssignment(int index) {
		
		int bufferPosition;
		
		bufferPosition = InternalStorage.getBufferPosition(index);
		
		InternalStorage.getBuffer().get(bufferPosition).setIsDone(true);
		setIsOnTime(bufferPosition);
		
		InternalStorage.addBufferFirst(InternalStorage.getBuffer().get(bufferPosition));
		InternalStorage.getBuffer().remove(bufferPosition + 1);
		
		
		return bufferPosition;
	}
	
	private static void setIsOnTime(int bufferPosition) {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HHmm");
		Date todayDate = new Date();
		System.out.println(dateFormat.format(todayDate));
		String currentDate = dateFormat.format(todayDate);
		
		String[] dateTime = currentDate.split(" ");
		
		
		Comparator.checkOnTime(dateTime[0], dateTime[1], bufferPosition);
	}
}