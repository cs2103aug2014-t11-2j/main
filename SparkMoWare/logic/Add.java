package logic;

import logic.Assignment.AssignmentType;
import parser.RefinedUserInput;

public class Add {

	/**
	 * 
	 * @param refinedUserInput
	 * @return new assignment
	 */
	protected static String addSomething(RefinedUserInput userInput) {

		String id = Id.serialNumGen();

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

	private static void addAssignment(String id, String title,
			boolean isDone, String priority) {

		Assignment newAssignment = new Assignment();

		newAssignment.setId(id);
		newAssignment.setDateCreation(DateLocal.dateString());
		newAssignment.setIndex(Integer.parseInt(Id.removeFrontZero(id
				.substring(8))));
		newAssignment.setTitle(title);
		newAssignment.setIsDone(isDone);
		newAssignment.setPriority(priority);

		addAssignmentToBuffer(newAssignment);

	}

	protected static void addAssignmentToBuffer(Assignment newAssignment) {

		int count = 0;
//		Assignment assignment;
		
		if(InternalStorage.getLineCount() == 0) {
			InternalStorage.addBuffer(newAssignment);
		} else {
			int size = InternalStorage.getLineCount();
		//	ListIterator<Assignment> listIterate = InternalStorage.getBuffer().listIterator();
			
			for(int i = 0; i < size; i++) {
				if(!InternalStorage.getBuffer().get(i).getIsDone()) {
					InternalStorage.addBuffer(i, newAssignment);
				} else if(i == size - 1) {
					count = i;
					InternalStorage.addBuffer(count + 1, newAssignment);
					break;
				}
			}
		}
	}

	private static String addAppointment(String id, String title,
			String startDate, String startTime, String endDate, String endTime,
			boolean isDone, String priority) {

		Appointment newAppointment = new Appointment();

		newAppointment.setId(id);
		newAppointment.setDateCreation(DateLocal.dateString());
		newAppointment.setIndex(Integer.parseInt(Id.removeFrontZero(id
				.substring(8))));
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

	private static String addTask(String id, String title, String endDate,
			String endTime, boolean isDone, String priority) {

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

	protected static void addTaskToBuffer(Task newTask) {

		if (InternalStorage.getLineCount() == 0) {
			InternalStorage.addBuffer(newTask);
		} else {

			int position = Comparator.addTaskToBigBuffer(newTask);
			InternalStorage.addBuffer(position, newTask);
		}
	}
}