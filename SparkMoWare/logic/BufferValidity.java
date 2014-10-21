package logic;

import java.util.LinkedList;

import logic.Assignment.AssignmentType;

public class BufferValidity {

	private static boolean typeChecked = false;
	private static boolean idChecked = false;
	private static boolean priorityChecked = false;
	private static boolean startDateChecked = false;
	private static boolean startTimeChecked = false;
	private static boolean endDateChecked = false;
	private static boolean endTimeChecked = false;

	/*
	 * checks validity of each attribute of each assignment in the buffer list
	 * if valid, assignment is added into new list and new list is returned
	 * else, it is stored in a separate list which is to be shown to user to
	 * prompt him to correct mistakes
	 */
	protected static LinkedList<Assignment> checkValidity(LinkedList<Assignment> buffer) {

		for (int counter = buffer.size(); counter < buffer.size(); counter--) {
			
			dateAndTimeChecker(buffer.get(counter));
			idChecker(buffer.get(counter).getId());
			priorityChecker(buffer.get(counter).getPriority());

			if (typeChecked && startDateChecked && endDateChecked
					&& startTimeChecked && endTimeChecked && idChecked
					&& priorityChecked) {
				buffer.remove(buffer.get(counter));
			}
		}
		return buffer;
	}

	// Temporary assumption: start date and time appears before end date and
	// time
	private static void dateAndTimeChecker(Assignment assignment) {

		validType(assignment);
		
		if(assignment.getAssignType() == Assignment.AssignmentType.TASK){
			// may need to change to determineDateValidity
			Assignment tempTask = assignment;
			endDateChecked = DateLocal.dateFormatValid(((Task) assignment).getEndDate());
			endTimeChecked = TimeLocal.timeFormatValid(((Task) assignment).getEndTime());	
		} else {
			Assignment tempAppt = assignment;
			endDateChecked = DateLocal.dateFormatValid(((Appointment) assignment).getEndDate());
			endTimeChecked = TimeLocal.timeFormatValid(((Appointment) assignment).getEndTime());
			startDateChecked = DateLocal.dateFormatValid(((Appointment) assignment).getStartDate());
			startTimeChecked = TimeLocal.timeFormatValid(((Appointment) assignment).getStartTime());	
		}		
	}

	// Temporary assumption: assignment type has changed to either
	// TASK/APPT/APPT and nothing else
	private static void validType(Assignment element) {

		Assignment temp = element;
		Appointment tempAppt = (Appointment)temp;
		
		if(tempAppt.getAssignType().equals(Assignment.AssignmentType.TASK)){
			if(tempAppt.getStartDate() != null || tempAppt.getStartTime() != null){
				typeChecked = false;
			}
		}
		typeChecked = true;
	}

	private static void idChecker(String id) {
		 idChecked = Id._IDFormatValid(id);
	}

	private static void priorityChecker(String priority) {
		if (priority.equals("NIMPT") || priority.equals("IMPT")) {
			priorityChecked = true;
		}
		priorityChecked = false;
	}
	
}
