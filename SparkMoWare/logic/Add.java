package logic;

import parser.RefinedUserInput;
import logic.Assignment.AssignmentType;

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

	/**
	 * 
	 * @param refinedUserInput
	 * @return new assignment 
	 */
	protected static String addSomething(RefinedUserInput userInput) {

		if(userInput.getAssignmentType().equals(AssignmentType.APPOINTMENT)) {

			return addAppointment(userInput.getId(), userInput.getTitle(), userInput.getStartDate(),
					userInput.getStartTime(), userInput.getEndDate(), userInput.getEndTime(), 
					false, userInput.getSpecialContent());
		} else if(userInput.getAssignmentType().equals(AssignmentType.TASK)) {

			return addTask(userInput.getId(), userInput.getTitle(), userInput.getEndDate(), 
					userInput.getEndTime(), false, userInput.getSpecialContent());
		} else {
			return addAssignment(userInput.getId(), userInput.getTitle(), false, userInput.getSpecialContent());
		}
	}

	private static String addAssignment(String id, String title,
			boolean isDone, String priority) {

		Assignment newAssignment = new Assignment();

		newAssignment.setId(id);
		newAssignment.setDateCreation(DateLocal.dateString());
		newAssignment.setIndex(Integer.parseInt(Id.removeFrontZero(id.substring(8))));
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
		newAppointment.setDateCreation(DateLocal.dateString());
		newAppointment.setIndex(Integer.parseInt(Id.removeFrontZero(id.substring(8))));
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
		newTask.setDateCreation(DateLocal.dateString());
		newTask.setIndex(Integer.parseInt(Id.removeFrontZero(id.substring(8))));
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