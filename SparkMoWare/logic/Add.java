package logic;

import logic.Assignment.AssignmentType;
import parser.RefinedUserInput;

//@author A0111572R

public class Add {
	
	/**
	 * add methods for logic
	 * @param userinput
	 * @return new assignment
	 */
	protected static int addSomething(RefinedUserInput userInput) {

		int id = Id.serialNumGen();

		if (userInput.getAssignmentType().equals(AssignmentType.APPT)) {

			addAppointment(id, userInput.getTitle(),
					userInput.getStartDate(), userInput.getStartTime(),
					userInput.getEndDate(), userInput.getEndTime(), false,
					userInput.getPriority());

		} else if (userInput.getAssignmentType().equals(AssignmentType.TASK)) {

			addTask(id, userInput.getTitle(),
					userInput.getEndDate(), userInput.getEndTime(), false,
					userInput.getPriority());
		} else {
			addAssignment(id, userInput.getTitle(),
					false, userInput.getPriority());
		}
		return id;
	}

	private static void addAssignment(int id, String title,
			boolean isDone, String priority) {

		Assignment newAssignment = new Assignment();

		newAssignment.setIndex(id);
		newAssignment.setTitle(title);
		newAssignment.setIsDone(isDone);
		newAssignment.setPriority(priority);

		addAssignmentToBuffer(newAssignment);

	}

	protected static void addAssignmentToBuffer(Assignment newAssignment) {

		if(InternalStorage.getLineCount() == 0) {
			InternalStorage.addBuffer(newAssignment);
		} else {
			addAssignmentToBuffer2(newAssignment);
		}
	}
	
	private static void addAssignmentToBuffer2(Assignment newAssignment) {
		
		int count = 0;
		int size = InternalStorage.getLineCount();
		
		for(int i = 0; i < size; i++) {
			if(!InternalStorage.getBuffer().get(i).getIsDone()) {
				InternalStorage.addBuffer(i, newAssignment);
				break;
			} else if(i == size - 1) {
				count = i;
				InternalStorage.addBuffer(count + 1, newAssignment);
				break;
			}
		}
	}
	
	private static String addAppointment(int id, String title,
			String startDate, String startTime, String endDate, String endTime,
			boolean isDone, String priority) {

		Appointment newAppointment = new Appointment();

		newAppointment.setIndex(id);
		newAppointment.setTitle(title);
		newAppointment.setStartDate(startDate);
		newAppointment.setStartTime(startTime);
		newAppointment.setEndDate(endDate);
		newAppointment.setEndTime(endTime);
		newAppointment.setIsDone(isDone);
		newAppointment.setPriority(priority);

		if(Comparator.isClashing(newAppointment)) {
			SetTentative.setToTentative(newAppointment);
		} else {
			addAppointmentToBuffer(newAppointment);
		}
		return newAppointment.toString();
	}

	protected static void addAppointmentToBuffer(Appointment newAppointment) {

		if (InternalStorage.getLineCount() == 0) {
			InternalStorage.addBuffer(newAppointment);
		} else {
			int position = Comparator.addToBigBuffer(newAppointment);

			InternalStorage.addBuffer(position, newAppointment);
		}
	}

	private static String addTask(int id, String title, String endDate,
			String endTime, boolean isDone, String priority) {

		Task newTask = new Task();

		newTask.setIndex(id);
		newTask.setTitle(title);
		newTask.setEndDate(endDate);
		newTask.setEndTime(endTime);
		newTask.setIsDone(isDone);
		newTask.setPriority(priority);

		addTaskToBuffer(newTask);

		return newTask.toString();
	}

	protected static void addTaskToBuffer(Task newTask) {

		if (InternalStorage.getLineCount() == 0) {
			InternalStorage.addBuffer(newTask);
		} else {

			int position = Comparator.addTaskToBigBuffer(newTask);
			InternalStorage.addBuffer(position, newTask);
		}
	}
}