package logic;

import java.util.LinkedList;

import logic.Assignment.assignmentType;

public class BufferValidity {

	static boolean typeChecked = false;
	static boolean idChecked = false;
	static boolean priorityChecked = false;
	static boolean startDateChecked = false;
	static boolean startTimeChecked = false;
	static boolean endDateChecked = false;
	static boolean endTimeChecked = false;

	/*
	 * checks validity of each attribute of each assignment in the buffer list
	 * if valid, assignment is added into new list and new list is returned
	 * else, it is stored in a separate list which is to be shown to user to
	 * prompt him to correct mistakes
	 */
	protected static void checkValidity(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> newBuffer = new LinkedList<Assignment>();

		for (int counter = 0; counter < buffer.size(); counter++) {
			// check validity of SN, Title(maybe), start date, start time, end
			// date,
			// end time, type, priority
			// ID
			// firstly check valid type
			typeChecked = validType(buffer, buffer.get(counter));
			dateAndTimeChecker(buffer.get(counter), buffer.get(counter)
					.getAssignType());
			idChecked = Id._IDFormatValid(buffer.get(counter).getId());
			priorityChecked = priorityChecker(buffer.get(counter).getPriority());

			if (typeChecked || startDateChecked || endDateChecked
					|| startTimeChecked || endTimeChecked || idChecked
					|| priorityChecked) {
				newBuffer.add(buffer.get(counter));
			}

		}

	}

	private static boolean priorityChecker(String priority) {
		if (priority.equals("NIMPT") || priority.equals("IMPT")) {
			return true;
		}
		return false;
	}

	// Temporary assumption: start date and time appears before end date and
	// time
	private static void dateAndTimeChecker(Assignment assignment,
			assignmentType assignType) {

		// may need to change to determineDateValidity
		endDateChecked = parser.ParserDateLocal
				.dateFormatValid(((Task) assignment).getEndDate());
		endTimeChecked = parser.ParserTimeLocal
				.timeFormatValid(((Task) assignment).getEndTime());

		// since task & tentative task won't have start details
		try {
			startDateChecked = parser.ParserDateLocal
					.dateFormatValid(((Appointment) assignment).getStartDate());
			startTimeChecked = parser.ParserTimeLocal
					.timeFormatValid(((Appointment) assignment).getStartTime());
		} catch (NullPointerException e) {
			startDateChecked = true;
			startTimeChecked = true;
		}
	}

	// Temporary assumption: assignment type has changed to either
	// TASK/APPT/APPT and nothing else
	private static boolean validType(LinkedList<Assignment> bufferList,
			Assignment element) {

		// if (!element.equals("TASK") && !element.equals("APPT") &&
		// !element.equals("TNTV")){

		LinkedList<Assignment> tentativeList = new LinkedList<Assignment>();
		tentativeList = SearchAll.searchAll(bufferList, element.getTitle());

		if (tentativeList.size() > 1) {
			element.setAssignType(Assignment.assignmentType.TENTATIVE);
		} else {
			String elementString = element.toString();
			String[] elementAttributes = elementString.split("~");
			if (elementAttributes.length == 8) {
				return true;
			} else if (elementAttributes.length == 10) {
				return true;
			}
		}
		// consider whether to return false when exception is handled
		return false;
	}

}
