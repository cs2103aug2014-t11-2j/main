package logic;

import java.util.LinkedList;

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