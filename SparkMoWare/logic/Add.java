package logic;

import java.util.*;

import logic.Assignment.assignmentType;

/*
 * If the startDate and startTime is empty, it will be created as task
 * else
 * it will be created as appointment
 * Assignment will be added to the buffer according to the date of creation
 */
public class Add {

	public static String addAppointment(String id, String title, String type,
			String startDate, String startTime, String endDate, String endTime,
			boolean isDone, String priority) {

		Appointment newAppointment = new Appointment();

		newAppointment.setId(id);
		newAppointment.setTitle(title);
		newAppointment.setType(type);
		newAppointment.setStartDate(startDate);
		newAppointment.setStartTime(startTime);
		newAppointment.setEndDate(endDate);
		newAppointment.setEndTime(endTime);
		newAppointment.setIsDone(isDone);
		// newAppointment.setDescription(description);
		// newAppointment.setAlarm(alarm);
		//newAppointment.setTag(tag);
		newAppointment.setPriority(priority);

		addAppointmentToBuffer(newAppointment);

		return newAppointment.toString();
	}

	private static void addAppointmentToBuffer(Appointment newAppointment) {

		if (InternalStorage.getLineCount() == 0) {
			InternalStorage.addBuffer(newAppointment);

		} else if(InternalStorage.getBuffer().get(0).getAssignType().equals(assignmentType.TASK)) {

			if (Comparator.dateComparator(newAppointment.getEndDate(),
					InternalStorage.getBuffer().get(0).getEndDate()) == 1) {
				InternalStorage.addBuffer(newAppointment);
			}
		} else {
			int bufferCount;

			for (bufferCount = InternalStorage.getLineCount() - 1; bufferCount > 0 && 
					(Comparator.dateComparator(newAppointment.getEndDate(), 
							InternalStorage.getBuffer().get(bufferCount - 1).getEndDate()) == -1); bufferCount--);
			InternalStorage.addBuffer(bufferCount, newAppointment);
		} 
	}

	public static String addTask(String id, String title, String type, 
			String endDate, String endTime,	boolean isDone, String priority) {

		Task newTask = new Task();

		newTask.setId(id);
		newTask.setTitle(title);
		newTask.setType(type);
		newTask.setEndDate(endDate);
		newTask.setEndTime(endTime);
		newTask.setIsDone(isDone);
		newTask.setPriority(priority);

		addTaskToBuffer(newTask);

		return newTask.toString();
	}

	private static void addTaskToBuffer(Task newTask) {

		if (InternalStorage.getLineCount() == 0) {
			InternalStorage.addBuffer(newTask);

		} else if (Comparator.dateComparator(newTask.getEndDate(),
				InternalStorage.getBuffer().get(InternalStorage.getLineCount() - 1).getEndDate()) == 1) {
			InternalStorage.addBuffer(newTask);

		} else{
			int bufferCount;

			for (bufferCount = InternalStorage.getLineCount() - 1; bufferCount > 0 && 
					(Comparator.dateComparator(newTask.getEndDate(), 
							InternalStorage.getBuffer().get(bufferCount - 1).getEndDate()) == -1); bufferCount--);
			InternalStorage.addBuffer(bufferCount, newTask);
		} 
	}
}