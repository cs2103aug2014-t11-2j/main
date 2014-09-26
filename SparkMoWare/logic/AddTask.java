package logic;

import java.util.*;

public class AddTask {

	// Add tasks and appointment into the system.
	public static String addTask(String id, String title, int type,
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
		System.out.println(newAssignment.toString());

		// adding task to buffer according to date
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