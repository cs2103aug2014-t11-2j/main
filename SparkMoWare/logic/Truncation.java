package logic;

import logic.Assignment;
import static org.junit.Assert.*;
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
		//assertTrue(truncatedList.getFirst().getEndDate().equals(date));
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
		assertFalse(truncatedList.contains(DateLocal.updateDate(date)));
		return truncatedList;
	}
}

/*
 * 
	private static int searchLimit(LinkedList<Assignment> trancatedList,
			String date, String limitType) {

		int limitIndex = 0;
		LinkedList<Assignment> possibleIndexes = new LinkedList<Assignment>(); 
		possibleIndexes = SearchAll.searchAll(date);

		if(limitType.equals("lower")){
			for(int i = 0; i < possibleIndexes.size(); i++){
				if(possibleIndexes.get(i).getStartDate().equals(date)){
					if(i > limitIndex){
						return i;
					}
				}
			}
		} else {
			for(int i = 0; i < possibleIndexes.size(); i++){
				if(possibleIndexes.get(i).getEndDate().equals(date)){
					if(i > limitIndex){
						limitIndex = i;
					}
				}
			}
		}
		return limitIndex;
	}

 */