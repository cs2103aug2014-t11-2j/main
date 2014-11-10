package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import logic.Assignment.AssignmentType;
import parser.EnumGroup.EditType;
import parser.RefinedUserInput;

//@author A0117057J

public class Edit {
	
	private static final String DEFAULT_TIME = "2359";
	private static final String DEFAULT_START_TIME = "0000";
	
	protected static String editAssignment(RefinedUserInput userInput) {

		LinkedList<Assignment> idFound = new LinkedList<Assignment>();
		idFound = SearchAll.searchAll(InternalStorage.getBuffer(), Integer.toString(userInput.getIndex()));

		if(idFound.size() == 0) {
			
			String toUser = "";

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

	private static EditType getEditType(String attributeName) { 

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
			setNewAppointment(date, DEFAULT_START_TIME, appointmentTemp.getEndDate(), appointmentTemp.getEndTime(), 
					appointmentTemp);
			
		} else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.ASGN)) {
			Assignment appointmentTemp = InternalStorage.getBuffer().remove(bufferPosition);
			setNewAppointment(date, DEFAULT_START_TIME, date, DEFAULT_TIME, appointmentTemp);
		}
	}

	private static void editStartTime(int bufferPosition, String time) {
		
		Appointment appointmentInBuffer = new Appointment();

		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferPosition));
			appointmentInBuffer.setStartTime(time);
			
		} else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.TASK)) {
			Task appointmentTemp = ((Task) InternalStorage.getBuffer().remove(bufferPosition));
			setNewAppointment(appointmentTemp.getEndDate(), time, appointmentTemp.getEndDate(),
					appointmentTemp.getEndTime(), appointmentTemp);
		}
	}
	
	private static void setNewAppointment(String startDate, String startTime, String endDate, String endTime,
			Assignment appointmentTemp) {
		
		Appointment appointmentInBuffer = new Appointment();
		
		appointmentInBuffer.setEndDate(endDate);
		appointmentInBuffer.setEndTime(endTime);
		appointmentInBuffer.setStartDate(startDate);
		appointmentInBuffer.setStartTime(startTime);
		appointmentInBuffer.setIndex(appointmentTemp.getIndex());
		appointmentInBuffer.setIsDone(appointmentTemp.getIsDone());
		appointmentInBuffer.setIsOnTime(appointmentTemp.getIsOnTime());
		appointmentInBuffer.setPriority(appointmentTemp.getPriority());
		appointmentInBuffer.setTitle(appointmentTemp.getTitle());
		
		Add.addAppointmentToBuffer(appointmentInBuffer);	
	}
	
	private static void editEndDate(int bufferPosition, String date) {

		Appointment appointmentInBuffer = new Appointment();
		Task taskInBuffer = new Task();

		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferPosition));
			appointmentInBuffer.setEndDate(date);
			InternalStorage.getBuffer().remove(bufferPosition);
			Add.addAppointmentToBuffer(appointmentInBuffer);
			
		} else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.TASK)) {
			taskInBuffer = ((Task) InternalStorage.getBuffer().get(bufferPosition));
			taskInBuffer.setEndDate(date);
			InternalStorage.getBuffer().remove(bufferPosition);
			Add.addTaskToBuffer(taskInBuffer);
			
		}else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.ASGN)){
			Assignment assignmentTemp = (Assignment) InternalStorage.getBuffer().remove(bufferPosition);
			setNewTask(DateLocal.dateString(), DEFAULT_TIME, assignmentTemp);
		}
	}

	private static void editEndTime(int bufferPosition, String time) {

		Appointment appointmentInBuffer = new Appointment();
		Task taskInBuffer = new Task();

		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferPosition));
			appointmentInBuffer.setEndTime(time);
			InternalStorage.getBuffer().remove(bufferPosition);
			Add.addAppointmentToBuffer(appointmentInBuffer);
			
		} else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.TASK)) {
			taskInBuffer = ((Task) InternalStorage.getBuffer().get(bufferPosition));
			taskInBuffer.setEndTime(time);
			InternalStorage.getBuffer().remove(bufferPosition);
			Add.addTaskToBuffer(taskInBuffer);
			
		}else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.ASGN)){
			Assignment assignmentTemp = (Assignment) InternalStorage.getBuffer().remove(bufferPosition);
			setNewTask(DateLocal.dateString(), time, assignmentTemp);
		}
	}
	
	private static void setNewTask(String endDate, String endTime,
			Assignment assignmentTemp) {
		
		Task taskInBuffer = new Task();
		
		taskInBuffer.setEndDate(endDate);
		taskInBuffer.setEndTime(endTime);
		taskInBuffer.setIndex(assignmentTemp.getIndex());
		taskInBuffer.setIsDone(assignmentTemp.getIsDone());
		taskInBuffer.setIsOnTime(assignmentTemp.getIsOnTime());
		taskInBuffer.setPriority(assignmentTemp.getPriority());
		taskInBuffer.setTitle(assignmentTemp.getTitle());
		
		Add.addTaskToBuffer(taskInBuffer);
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