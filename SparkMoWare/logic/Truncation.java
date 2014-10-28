package logic;

import logic.Assignment;
import logic.Assignment.AssignmentType;
import java.util.LinkedList;

public class Truncation {

	private static Appointment appointmentInList = new Appointment();
	private static Task taskInList = new Task();

	public static LinkedList<Assignment> truncateList(LinkedList<Assignment> truncatedList, String startDate,
			String endDate) {

		LinkedList<Assignment> limitRemoved = new LinkedList<Assignment>();
		if(startDate != null) {
			limitRemoved = removeLowerLimit(truncatedList, startDate);
		} 
		if(endDate != null) {
			limitRemoved = removeUpperLimit(truncatedList, endDate);
		}
		return limitRemoved;
	}

	private static LinkedList<Assignment> removeLowerLimit(LinkedList<Assignment> truncatedList, String date) {

		for(int truncatedCount = truncatedList.size() - 1; truncatedCount >= 0; truncatedCount--) {
			
			if(truncatedList.get(truncatedCount).getAssignType().equals(AssignmentType.TASK)) {
				taskInList = ((Task) truncatedList.get(truncatedCount));
				
				if(Comparator.dateComparator(taskInList.getEndDate(), date) == -1) {
					truncatedList.remove(truncatedCount);
				}
			} else if(truncatedList.get(truncatedCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInList = ((Appointment) truncatedList.get(truncatedCount));
				
				if(Comparator.dateComparator(appointmentInList.getEndDate(), date) == -1) {
					truncatedList.remove(truncatedCount);
				}
			}
		}
		return truncatedList;
	}

	private static LinkedList<Assignment> removeUpperLimit(LinkedList<Assignment> truncatedList, String date) {

		for(int truncatedCount = truncatedList.size() - 1; truncatedCount >= 0; truncatedCount--) {
			
			if(truncatedList.get(truncatedCount).getAssignType().equals(AssignmentType.TASK)) {
				taskInList = ((Task) truncatedList.get(truncatedCount));
				
				if(Comparator.dateComparator(taskInList.getEndDate(), date) == 1) {
					truncatedList.remove(truncatedCount);
				}
			} else if(truncatedList.get(truncatedCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInList = ((Appointment) truncatedList.get(truncatedCount));
				
				if(Comparator.dateComparator(appointmentInList.getEndDate(), date) == 1) {
					truncatedList.remove(truncatedCount);
				}
			}
		}
		return truncatedList;
	}
}