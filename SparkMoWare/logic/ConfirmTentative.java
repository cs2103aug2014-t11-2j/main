package logic;

import java.util.*;

/*
 * This will delete all the tentative with the same SIN
 * and save the confirmed one with new SIN
 */
public class ConfirmTentative {

	private static LinkedList<Assignment> tentativeNeeded = new LinkedList<Assignment>();
	
	public static String confirmTentative(String serialId, String confirmStartDate, String confirmStartTime, 
			String confirmEndDate, String confirmEndTime) {
		
		Assignment confirmAssignment = new Assignment();
		
		tentativeNeeded = SearchAll.searchAll(serialId);
		
		if(tentativeNeeded.size() == 0) {
			return String.format(Message.DOES_NOT_EXISTS, "Serial Number " + serialId);
		} else {

			confirmAssignment = findConfirmTentative(confirmStartDate, confirmStartTime, confirmEndDate, confirmEndTime);
			confirmAssignment.setId(Id.serialNumGen());

			Delete.delete(tentativeNeeded.get(1).getId());

			String newTitle = confirmAssignment.getTitle().substring(0, confirmAssignment.getTitle().lastIndexOf(' '));

			confirmAssignment.setTitle(newTitle);

			return confirmAssignment.toString();
		}
	}
	
	private static Assignment findConfirmTentative(String confirmStartDate, String confirmStartTime, 
			String confirmEndDate, String confirmEndTime){
		
		Assignment noAssignment = new Assignment();
		
		for(int listCheck = 0; listCheck < tentativeNeeded.size(); listCheck++) {
			if(tentativeNeeded.get(listCheck).getStartDate().equals(confirmStartDate) && 
					tentativeNeeded.get(listCheck).getEndDate().equals(confirmEndDate)) {
				
				if(tentativeNeeded.get(listCheck).getStartTime().equals(confirmStartTime) && 
						tentativeNeeded.get(listCheck).getEndTime().equals(confirmEndTime) ) {
					
					return tentativeNeeded.get(listCheck);
				}
			}
		}
		return noAssignment;
	}
}