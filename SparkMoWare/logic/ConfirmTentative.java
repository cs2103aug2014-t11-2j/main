package logic;

import java.util.*;

/*
 * Left with deleteAll to pass the result to
 */
public class ConfirmTentative {

	public static Assignment confirmTentative(String serialId, String confirmDate, String confirmTime) {
		
		Assignment confirmAssignment = new Assignment();
		
		LinkedList<Assignment> tentativeNeeded = SearchAll.searchAll(serialId);
		
		for(int listCheck = 0; listCheck < tentativeNeeded.size(); listCheck++) {
			if(tentativeNeeded.get(listCheck).getStartDate().equals(confirmDate)) {
				
				if(tentativeNeeded.get(listCheck).getStartTime().equals(confirmTime)) {
					confirmAssignment = tentativeNeeded.get(listCheck);
				}
			}
		}
		DeleteAll(tentativeNeeded);
		
		return confirmAssignment;
	}
}