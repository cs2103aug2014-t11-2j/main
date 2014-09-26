package logic;

import java.util.*;

public class Statistic {
	
	private static LinkedList<Assignment> temp = new LinkedList<Assignment>();
	
	protected static String getStats(){
		int completed = getCompleted(); //stub
		int isOnTime = getIsOnTime(); //stub
		
		if(completed == 0){
			return SparkMoVare.MESSAGE_NOTHING_COMPLETED;
		}else if(isOnTime == 0){
			return SparkMoVare.MESSAGE_NOTHING_COMPLETED + " on time";
		}else{
			return Integer.toString((completed/isOnTime)*100);
		}
	}
	
	private static int getCompleted() {
		
		temp = SearchAll.searchAll("completed");
		
		return temp.size();
	}

	private static int getIsOnTime(){

		temp = SearchAll.searchAll("isontime");
		
		return temp.size();
	}
}