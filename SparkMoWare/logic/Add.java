package logic;

import parser.EnumGroup.AssignmentType;

/*
 * If the startDate and startTime is empty, it will be created as task
 * else
 * it will be created as appointment
 * Assignment will be added to the buffer according to the date of creation
 */
public class Add {

	private static Appointment appointmentInBuffer = new Appointment();
	private static Task taskInBuffer = new Task();
	private static int bufferCount;

	protected static String addSomething(String[] refinedUserInput) {

		if(refinedUserInput[7].equals(AssignmentType.APPOINTMENT)) {

			return addAppointment(refinedUserInput[1], refinedUserInput[2],	refinedUserInput[3],
					refinedUserInput[4], refinedUserInput[5], refinedUserInput[6], 
					false, refinedUserInput[9]);
		} else if(refinedUserInput[7].equals(AssignmentType.TASK)) {

			return addTask(refinedUserInput[1], refinedUserInput[2], refinedUserInput[5], 
					refinedUserInput[6], false, refinedUserInput[9]);
		} else {
			return addAssignment(refinedUserInput[1], refinedUserInput[2], false, refinedUserInput[9]);
		}
	}

	private static String addAssignment(String id, String title,
			boolean isDone, String priority) {

		Assignment newAssignment = new Assignment();

		newAssignment.setId(id);
		newAssignment.setTitle(title);
		newAssignment.setIsDone(isDone);
		newAssignment.setPriority(priority);

		InternalStorage.addBuffer(newAssignment);

		return newAssignment.toString();
	}

	private static String addAppointment(String id, String title, String startDate,
			String startTime, String endDate, String endTime,
			boolean isDone, String priority) {

		Appointment newAppointment = new Appointment();

		newAppointment.setId(id);
		newAppointment.setTitle(title);
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
		} else {
			addToBigBuffer(newAppointment);
		}
	}
	
	private static void addToBigBuffer(Appointment newAppointment) {
		
		for(bufferCount = 0; bufferCount < InternalStorage.getLineCount(); bufferCount++) {

			if(InternalStorage.getBuffer().get(bufferCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferCount));

				if(Comparator.dateComparator(newAppointment.getEndDate(), appointmentInBuffer.getEndDate()) == -1) {
					InternalStorage.addBuffer(bufferCount, newAppointment);
				}
			} else if(InternalStorage.getBuffer().get(bufferCount).getAssignType().equals(AssignmentType.TASK)) {
				taskInBuffer = ((Task) InternalStorage.getBuffer().get(bufferCount));

				if (Comparator.dateComparator(newAppointment.getEndDate(), taskInBuffer.getEndDate()) == -1) {
					InternalStorage.addBuffer(bufferCount, newAppointment);
				}
			}
		}
	}
	/*
		else if(InternalStorage.getBuffer().get(0).getAssignType().equals(assignmentType.TASK)) {

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
		} */

	private static String addTask(String id, String title, 
			String endDate, String endTime,	boolean isDone, String priority) {

		Task newTask = new Task();

		newTask.setId(id);
		newTask.setTitle(title);
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
		} else {
			addToBigBuffer(newTask);
		}
	}
	
	private static void addToBigBuffer(Task newTask) {
		
		for(bufferCount = 0; bufferCount < InternalStorage.getLineCount(); bufferCount++) {

			if(InternalStorage.getBuffer().get(bufferCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferCount));

				if(Comparator.dateComparator(newTask.getEndDate(), appointmentInBuffer.getEndDate()) == -1) {
					InternalStorage.addBuffer(bufferCount, newTask);
				}
			} else if(InternalStorage.getBuffer().get(bufferCount).getAssignType().equals(AssignmentType.TASK)) {
				taskInBuffer = ((Task) InternalStorage.getBuffer().get(bufferCount));

				if (Comparator.dateComparator(newTask.getEndDate(), taskInBuffer.getEndDate()) == -1) {
					InternalStorage.addBuffer(bufferCount, newTask);
				}
			}
		}
	}
}