package logic;

import java.util.LinkedList;

/**
 * Logic: Statistic component to make the show the number of job completed
 * 		  and whether they are completed on time.
 * @author Teck Zhi
 */

public class Statistic {
	
	private static LinkedList<Assignment> requiredCompleted = new LinkedList<Assignment>();
	private static LinkedList<Assignment> requiredOnTime = new LinkedList<Assignment>();
	
	protected static int getCompleted() {
		
		requiredCompleted = SearchAll.searchAll(InternalStorage.getBuffer(), "completed");
		
		return requiredCompleted.size();
	}

	protected static int getIsOnTime() {

		requiredOnTime = SearchAll.searchAll(InternalStorage.getBuffer(), "isontime");
		
		return requiredOnTime.size();
	}
}