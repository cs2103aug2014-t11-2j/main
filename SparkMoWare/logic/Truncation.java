package logic;

import logic.Assignment;
import logic.Assignment.AssignmentType;
import java.util.LinkedList;

public class Truncation {

	private static Appointment appointmentInList = new Appointment();
	private static Task taskInList = new Task();

	public static LinkedList<Assignment> trancateList(LinkedList<Assignment> trancatedList, String startDate,
			String endDate) {

		LinkedList<Assignment> limitRemoved = new LinkedList<Assignment>();
		if(startDate != null) {
			limitRemoved = removeLowerLimit(trancatedList, startDate);
		} 
		if(endDate != null) {
			limitRemoved = removeUpperLimit(trancatedList, endDate);
		}
		return limitRemoved;
	}

	private static LinkedList<Assignment> removeLowerLimit(LinkedList<Assignment> trancatedList, String date) {

		for(int trancatedCount = trancatedList.size() - 1; trancatedCount >= 0; trancatedCount--) {
			
			if(trancatedList.get(trancatedCount).getAssignType().equals(AssignmentType.TASK)) {
				taskInList = ((Task) trancatedList.get(trancatedCount));
				
				if(Comparator.dateComparator(taskInList.getEndDate(), date) == -1) {
					trancatedList.remove(trancatedCount);
				}
			} else if(trancatedList.get(trancatedCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInList = ((Appointment) trancatedList.get(trancatedCount));
				
				if(Comparator.dateComparator(appointmentInList.getEndDate(), date) == -1) {
					trancatedList.remove(trancatedCount);
				}
			}
		}
		return trancatedList;
	}

	private static LinkedList<Assignment> removeUpperLimit(LinkedList<Assignment> trancatedList, String date) {

		for(int trancatedCount = trancatedList.size() - 1; trancatedCount >= 0; trancatedCount--) {
			
			if(trancatedList.get(trancatedCount).getAssignType().equals(AssignmentType.TASK)) {
				taskInList = ((Task) trancatedList.get(trancatedCount));
				
				if(Comparator.dateComparator(taskInList.getEndDate(), date) == 1) {
					trancatedList.remove(trancatedCount);
				}
			} else if(trancatedList.get(trancatedCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInList = ((Appointment) trancatedList.get(trancatedCount));
				
				if(Comparator.dateComparator(appointmentInList.getEndDate(), date) == 1) {
					trancatedList.remove(trancatedCount);
				}
			}
		}
		return trancatedList;
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