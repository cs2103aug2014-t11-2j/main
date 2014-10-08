package logic;

import java.util.*;

/*
 * If the startDate and startTime is empty, it will be created as task
 * else
 * it will be created as appointment
 * Assignment will be added to the buffer according to the date of creation
 */
public class Add {

	public static String addAssignment(String id, String title, int type,
			String startDate, String startTime, String endDate, String endTime,
			boolean isDone, String priority, Vector<String> tag) {

		Assignment newAssignment = new Assignment();

		newAssignment.setId(id);
		newAssignment.setTitle(title);
		newAssignment.setType(type);
		newAssignment.setStartDate(startDate);
		newAssignment.setStartTime(startTime);
		newAssignment.setEndDate(endDate);
		newAssignment.setEndTime(endTime);
		newAssignment.setIsDone(isDone);
		// newAssignment.setDescription(description);
		// newAssignment.setAlarm(alarm);
		newAssignment.setTag(tag);
		newAssignment.setPriority(priority);
		
		addToBuffer(newAssignment);
		
		return newAssignment.toString();
	}
	
	private static void addToBuffer(Assignment newAssignment) {
		
		if (SparkMoVare.buffer.size() == 0) {
			SparkMoVare.buffer.add(newAssignment);
			
		} else if (Comparator.dateComparator(newAssignment.getEndDate(),
					SparkMoVare.buffer.get(SparkMoVare.buffer.size() - 1).getEndDate()) == 1) {
			SparkMoVare.buffer.add(newAssignment);
			
		} else{
			int bufferCount;
			
			for (bufferCount = SparkMoVare.buffer.size() - 1; bufferCount > 0 && 
					(Comparator.dateComparator(newAssignment.getEndDate(), 
					SparkMoVare.buffer.get(bufferCount - 1).getEndDate()) == -1); bufferCount--);
			SparkMoVare.buffer.add(bufferCount, newAssignment);
		} 
	}
}