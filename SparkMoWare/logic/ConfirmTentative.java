package logic;

import java.util.*;

/*
 * This will delete all the tentative with the same SIN
 * and save the confirmed one with new SIN
 */
public class ConfirmTentative {

	public static Assignment confirmTentative(String serialId, String confirmDate, String confirmTime) {
		
		Assignment confirmAssignment = new Assignment();
		
		LinkedList<Assignment> tentativeNeeded = SearchAll.searchAll(serialId);
		
		for(int listCheck = 0; listCheck < tentativeNeeded.size(); listCheck++) {
			if(tentativeNeeded.get(listCheck).getStartDate().equals(confirmDate)) {
				
				if(tentativeNeeded.get(listCheck).getStartTime().equals(confirmTime)) {
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