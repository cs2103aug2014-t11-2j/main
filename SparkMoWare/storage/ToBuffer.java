package storage;

import java.util.LinkedList;
import logic.Appointment;
import logic.Assignment;
import logic.Comparator;
import logic.Id;
import logic.Task;

public class ToBuffer {
	
	private static final int  ASSIGNMENT_LENGTH = 6;
	private static final int TASK_LENGTH = 8;
	private static final int APPOINTMENT_LENGTH = 10;
	
	protected static LinkedList<Assignment> addToBuffer(String[] lineArray) {

		boolean check = false;

		LinkedList<Assignment> buffer = new LinkedList<Assignment>();

		if(lineArray.length == ASSIGNMENT_LENGTH) {

			check = AssignValidCheck.checkAssignment(lineArray);

			if(check) {
				setLatestSerialNumber(lineArray[0]);
				buffer.add(toBufferAssignment(lineArray));
			}
		} else if (lineArray.length == TASK_LENGTH) {

			check = AssignValidCheck.checkTask(lineArray);

			if(check) {
				setLatestSerialNumber(lineArray[0]);
				buffer.add(toBufferTask(lineArray));
			}
		} else if(lineArray.length == APPOINTMENT_LENGTH) {

			check = AssignValidCheck.checkAppointment(lineArray);

			if(check) {
				setLatestSerialNumber(lineArray[0]);
				buffer.add(toBufferAppointment(lineArray));
			}
		}
		return buffer;
	}

	protected static Assignment toBufferAssignment(String[] lineArray) {

		// adding as Assignment
		Assignment temp = new Assignment();

		setLatestSerialNumber(lineArray[0]);

		temp.setId(lineArray[0]);
		temp.setTitle(lineArray[1]);
		temp.setIsDone(Boolean.parseBoolean(lineArray[3]));
		temp.setIsOnTime(Boolean.parseBoolean(lineArray[4]));
		temp.setPriority(lineArray[5]);
		
		return temp;
	}

	protected static Task toBufferTask(String[] lineArray) {

		// adding as Task
		Task temp = new Task();

		setLatestSerialNumber(lineArray[0]);

		temp.setId(lineArray[0]);
		temp.setTitle(lineArray[1]);
		temp.setIsDone(Boolean.parseBoolean(lineArray[5]));
		temp.setIsOnTime(Boolean.parseBoolean(lineArray[6]));
		temp.setPriority(lineArray[7]);
		temp.setEndDate(lineArray[3]);
		temp.setEndTime(lineArray[4]);

		return temp;	
	}

	protected static Appointment toBufferAppointment(String[] lineArray) {

		// adding as Appointment
		Appointment temp = new Appointment();

		temp.setId(lineArray[0]);
		temp.setTitle(lineArray[1]);
		temp.setIsDone(Boolean.parseBoolean(lineArray[7]));
		temp.setIsOnTime(Boolean.parseBoolean(lineArray[8]));
		temp.setPriority(lineArray[9]);
		temp.setEndDate(lineArray[5]);
		temp.setEndTime(lineArray[6]);
		temp.setStartDate(lineArray[3]);
		temp.setStartTime(lineArray[4]);

		return temp;	
	}
	
	private static void setLatestSerialNumber(String id) {
		
		if(Id.getLatestSerialNumber().equals("")) {
			Id.setLatestSerialNumber(id);
		} else if(Comparator.serialNumberComparator(id, Id.getLatestSerialNumber())) {
			Id.setLatestSerialNumber(id);
		}
	}
}
