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
			boolean isDone, Vector<String> tag) {

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

		for (int bufferCount = 0; bufferCount < SparkMoVare.buffer.size(); bufferCount++) {
			if (Comparator.dateComparator(newAssignment.getEndDate(),
					SparkMoVare.buffer.get(bufferCount).getEndDate()) == 1) {
				
				SparkMoVare.buffer.add(bufferCount, newAssignment);

				return newAssignment.toString();
			}
		}
		SparkMoVare.buffer.addLast(newAssignment);

		return newAssignment.toString();
	}
}