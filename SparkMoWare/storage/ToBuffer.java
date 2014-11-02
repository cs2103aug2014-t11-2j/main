package storage;

import java.util.LinkedList;

import logic.Appointment;
import logic.Assignment;
import logic.Id;
import logic.Task;
import logic.Tentative;

public class ToBuffer {

	private static final int  ASSIGNMENT_LENGTH = 7;
	private static final int TASK_LENGTH = 9;
	private static final int APPOINTMENT_LENGTH = 11;

	protected static LinkedList<Assignment> addToBuffer(String[] lineArray) {

		boolean check = false;
		String serial = "";
		LinkedList<Assignment> buffer = new LinkedList<Assignment>();

		if(lineArray.length == ASSIGNMENT_LENGTH) {

			check = AssignValidCheck.checkAssignment(lineArray);

			if(check) {
				if(lineArray[1].length() == 1) {
					serial = lineArray[0] + "000" + lineArray[1];
				} else if(lineArray[1].length() == 2) {
					serial = lineArray[0] + "00" + lineArray[1];
				}

				setLatestSerialNumber(serial);
				buffer.add(toBufferAssignment(lineArray));
			}
		} else if (lineArray.length == TASK_LENGTH) {

			check = AssignValidCheck.checkTask(lineArray);

			if(check) {
				if(lineArray[1].length() == 1) {
					serial = lineArray[0] + "000" + lineArray[1];
				} else if(lineArray[1].length() == 2) {
					serial = lineArray[0] + "00" + lineArray[1];
				}

				setLatestSerialNumber(serial);
				buffer.add(toBufferTask(lineArray));
			}
		} else if(lineArray.length == APPOINTMENT_LENGTH && lineArray[4].contains("[")) {
			check = AssignValidCheck.checkTentative(lineArray);

			if(check) {
				if(lineArray[1].length() == 1) {
					serial = lineArray[0] + "000" + lineArray[1];
				} else if(lineArray[1].length() == 2) {
					serial = lineArray[0] + "00" + lineArray[1];
				}

				setLatestSerialNumber(serial);
				buffer.add(toBufferTentative(lineArray));
			}
		} else if(lineArray.length == APPOINTMENT_LENGTH) {

			check = AssignValidCheck.checkAppointment(lineArray);

			if(check) {
				if(lineArray[1].length() == 1) {
					serial = lineArray[0] + "000" + lineArray[1];
				} else if(lineArray[1].length() == 2) {
					serial = lineArray[0] + "00" + lineArray[1];
				}

				setLatestSerialNumber(serial);
				buffer.add(toBufferAppointment(lineArray));
			}
		}
		return buffer;
	}

	protected static Assignment toBufferAssignment(String[] lineArray) {

		// adding as Assignment
		Assignment temp = new Assignment();
		String serial = "";

		if(lineArray[1].length() == 1) {
			serial = lineArray[0] + "000" + lineArray[1];
		} else if(lineArray[1].length() == 2) {
			serial = lineArray[0] + "00" + lineArray[1];
		}
		temp.setId(serial);
		temp.setDateCreation(lineArray[0]);
		temp.setIndex(Integer.parseInt(lineArray[1]));
		temp.setTitle(lineArray[3]);
		temp.setIsDone(Boolean.parseBoolean(lineArray[4]));
		temp.setIsOnTime(Boolean.parseBoolean(lineArray[5]));
		temp.setPriority(lineArray[6]);

		return temp;
	}

	protected static Task toBufferTask(String[] lineArray) {

		// adding as Task
		Task temp = new Task();
		String serial = "";

		if(lineArray[1].length() == 1) {
			serial = lineArray[0] + "000" + lineArray[1];
		} else if(lineArray[1].length() == 2) {
			serial = lineArray[0] + "00" + lineArray[1];
		}
		temp.setId(serial);
		temp.setDateCreation(lineArray[0]);
		temp.setIndex(Integer.parseInt(lineArray[1]));
		temp.setTitle(lineArray[3]);
		temp.setIsDone(Boolean.parseBoolean(lineArray[6]));
		temp.setIsOnTime(Boolean.parseBoolean(lineArray[7]));
		temp.setPriority(lineArray[8]);
		temp.setEndDate(lineArray[4]);
		temp.setEndTime(lineArray[5]);

		return temp;	
	}

	protected static Appointment toBufferAppointment(String[] lineArray) {

		// adding as Appointment
		Appointment temp = new Appointment();

		String serial = "";

		if(lineArray[1].length() == 1) {
			serial = lineArray[0] + "000" + lineArray[1];
		} else if(lineArray[1].length() == 2) {
			serial = lineArray[0] + "00" + lineArray[1];
		}
		temp.setId(serial);
		temp.setDateCreation(lineArray[0]);
		temp.setIndex(Integer.parseInt(lineArray[1]));
		temp.setTitle(lineArray[3]);
		temp.setStartDate(lineArray[4]);
		temp.setStartTime(lineArray[5]);
		temp.setEndDate(lineArray[6]);
		temp.setEndTime(lineArray[7]);
		temp.setIsDone(Boolean.parseBoolean(lineArray[8]));
		temp.setIsOnTime(Boolean.parseBoolean(lineArray[9]));
		temp.setPriority(lineArray[10]);
		
		return temp;	
	}

	protected static Tentative toBufferTentative(String[] lineArray) {

		Tentative temp = new Tentative();
		String serial = "";

		if(lineArray[1].length() == 1) {
			serial = lineArray[0] + "000" + lineArray[1];
		} else if(lineArray[1].length() == 2) {
			serial = lineArray[0] + "00" + lineArray[1];
		}
		temp.setId(serial);
		temp.setDateCreation(lineArray[0]);
		temp.setIndex(Integer.parseInt(lineArray[1]));
		temp.setTitle(lineArray[3]);
		setTimeSlot(temp, lineArray[4], lineArray[5], lineArray[6], lineArray[7]);
		temp.setIsDone(Boolean.parseBoolean(lineArray[8]));
		temp.setIsOnTime(Boolean.parseBoolean(lineArray[9]));
		temp.setPriority(lineArray[10]);
		
		return temp;
	}
	
	private static void setLatestSerialNumber(String id) {

		if(Id.getLatestSerialNumber().equals("")) {
			Id.setLatestSerialNumber(id);
		} else if(ValidityCheck.serialNumberComparator(id, Id.getLatestSerialNumber())) {
			Id.setLatestSerialNumber(id);
		}
	}
	
	private static void setTimeSlot(Tentative temp, String startDate, String startTime, String endDate, String endTime) {
		
		String[] startDates = startDate.split(",");
		String[] startTimes = startTime.split(",");
		String[] endDates = endDate.split(",");
		String[] endTimes = endTime.split(",");
		
		for(int i = 0; i < startDates.length; i++) {
			startDates[i].trim();
			startTimes[i].trim();
			endDates[i].trim();
			endTimes[i].trim();
		}
		
		startDates[0] = startDates[0].substring(1, 9);
		startDates[startDates.length - 1] = startDates[startDates.length - 1].substring(1, 9);
		startTimes[0] = startTimes[0].substring(1, 5);
		startTimes[startTimes.length - 1] = startTimes[startTimes.length - 1].substring(1, 5);
		endDates[0] = endDates[0].substring(1, 9);
		endDates[endDates.length - 1] = endDates[endDates.length - 1].substring(1, 9);
		endTimes[0] = endTimes[0].substring(1, 5);
		endTimes[endTimes.length - 1] = endTimes[startTimes.length - 1].substring(1, 5);
		
		for(int slotsCount = 0; slotsCount < startDates.length; slotsCount++) {
			temp.addStartDate(startDates[slotsCount]);
			temp.addStartTime(startTimes[slotsCount]);
			temp.addEndDate(endDates[slotsCount]);
			temp.addEndTime(endTimes[slotsCount]);
		}
	}
}
