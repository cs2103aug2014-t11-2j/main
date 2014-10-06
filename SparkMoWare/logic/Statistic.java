package logic;

import java.util.*;

/* 
 * Statistic is to show the user the efficiency rate of the user
 * From the statistic, we will encourage user with some quotes (shown in the GUI)
 */
public class Statistic {
	
	private static LinkedList<Assignment> requiredCompleted = new LinkedList<Assignment>();
	private static LinkedList<Assignment> requiredOnTime = new LinkedList<Assignment>();
	
	protected static String getStats() {
		
		double completed = getCompleted(); 
		double isOnTime = getIsOnTime();
		
		if(completed == 0) {
			return Message.NOTHING_COMPLETED;
		} else if(isOnTime == 0) {
			return Message.NOTHING_COMPLETED + " on time";
		} else {
			String precentageOnTime = Double.toString((isOnTime/completed) * 100);
			
			return precentageOnTime;
		}
	}
	
	private static int getCompleted() {
		
		requiredCompleted = SearchAll.searchAll("completed");
		
		return requiredCompleted.size();
	}

	private static int getIsOnTime() {

		requiredOnTime = SearchAll.searchAll("isontime");
		
		return requiredOnTime.size();
	}
}