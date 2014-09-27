package logic;

import java.util.*;

/* 
 * Statistic is to show the user the efficiency rate of the user
 * From the statistic, we will encourage user with some quotes (shown in the GUI)
 */
public class Statistic {
	
	private static LinkedList<Assignment> required = new LinkedList<Assignment>();
	
	protected static String getStats() {
		
		int completed = getCompleted(); 
		int isOnTime = getIsOnTime();
		
		if(completed == 0) {
			return Message.NOTHING_COMPLETED;
		} else if(isOnTime == 0) {
			return Message.NOTHING_COMPLETED + " on time";
		} else {
			String precentageOnTime = Integer.toString((isOnTime/completed) * 100);
			
			return precentageOnTime;
		}
	}
	
	private static int getCompleted() {
		
		required = SearchAll.searchAll("completed");
		
		return required.size();
	}

	private static int getIsOnTime() {

		required = SearchAll.searchAll("isontime");
		
		return required.size();
	}
}