package logic;

import java.util.*;

/*
 * This will delete all the tentative with the same SIN
 * and save the confirmed one with new SIN
 */
public class ConfirmTentative {

	public static Assignment confirmTentative(String serialId, String confirmStartDate, String confirmStartTime, 
			String confirmEndDate, String confirmEndTime) {
		
		Assignment confirmAssignment = new Assignment();
		
		LinkedList<Assignment> tentativeNeeded = SearchAll.searchAll(serialId);
		
		for(int listCheck = 0; listCheck < tentativeNeeded.size(); listCheck++) {
			if(tentativeNeeded.get(listCheck).getStartDate().equals(confirmStartDate) && 
					tentativeNeeded.get(listCheck).getEndDate().equals(confirmEndDate)) {
				
				if(tentativeNeeded.get(listCheck).getStartTime().equals(confirmStartTime) && 
						tentativeNeeded.get(listCheck).getEndTime().equals(confirmEndTime) ) {
					confirmAssignment = tentativeNeeded.get(listCheck);
					confirmAssignment.setId(Id.serialNumGen());
				}
			}
		}
		Delete.delete(tentativeNeeded.get(1).getId());
		
		String newTitle = confirmAssignment.getTitle().substring(0, confirmAssignment.getTitle().lastIndexOf(' '));
		
		confirmAssignment.setTitle(newTitle);
		
		return confirmAssignment;
	}
}