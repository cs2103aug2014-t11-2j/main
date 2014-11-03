package logic;

import java.util.ListIterator;

import logic.Assignment.AssignmentType;

/*
 * Comparison for Time, Date and ID.
 * returns boolean 
 * or
 * -1 (A < B), 0 (A = B), 1 (A > B)
 */
public class Comparator {

	private static final int SMALLER = -1;
	private static final int SAME = 0;
	private static final int LARGER = 1;

	protected static boolean serialNumberComparator(int idA, int idB) {

		boolean serialCheck = false;

		if(idA > idB) {
			serialCheck= true;
		}
		return serialCheck;
	}

	protected static int dateComparator(String dateA, String dateB) {

		String yearA = dateA.trim().substring(6, 8);
		String yearB = dateB.trim().substring(6, 8);

		String monthA = dateA.trim().substring(3, 5);
		String monthB = dateB.trim().substring(3, 5);

		String dayA = dateA.trim().substring(0, 2);
		String dayB = dateB.trim().substring(0, 2);

		yearA = removeFrontZero(yearA);
		yearB = removeFrontZero(yearB);

		monthA = removeFrontZero(monthA);
		monthB = removeFrontZero(monthB);

		dayA = removeFrontZero(dayA);
		dayB = removeFrontZero(dayB);

		if (dateA.equals(dateB)) {
			return SAME;
		} else if (Integer.parseInt(yearA) > Integer.parseInt(yearB)) {
			return LARGER;
		} else if (Integer.parseInt(yearA) < Integer.parseInt(yearB)) {
			return SMALLER;
		} else if (Integer.parseInt(monthA) > Integer.parseInt(monthB)) {
			return LARGER;
		} else if (Integer.parseInt(monthA) < Integer.parseInt(monthB)) {
			return SMALLER;
		} else if (Integer.parseInt(dayA) > Integer.parseInt(dayB)) {
			return LARGER;
		}
		return SMALLER;
	}

	protected static int timeComparator(String timeA, String timeB) {

		String hourA = timeA.trim().substring(0, 2);
		String hourB = timeB.trim().substring(0, 2);

		String minA = timeA.trim().substring(2, 4);
		String minB = timeB.trim().substring(2, 4);

		if(hourA.equals(hourB)) {
			if(!minA.equals(minB)) {

				if(minA.equals("00") && !minB.equals("00")) {
					return SMALLER;
				} else if(!minA.equals("00") && minB.equals("00")) {
					return LARGER;
				} else if(!minA.equals("00") && !minB.equals("00")) {
					minA = removeFrontZero(minA);
					minB = removeFrontZero(minB);

					if(Integer.parseInt(minA) > Integer.parseInt(minB)) {
						return LARGER;
					} else {
						return SMALLER;
					}
				}
			} else {
				return SAME;
			}
		} else if(hourA.equals("00") && !hourB.equals("00")) {
			return SMALLER;
		} else if(!hourA.equals("00") && hourB.equals("00")) {
			return LARGER;
		} else if(!hourA.equals("00") && !hourB.equals("00")) {
			hourA = removeFrontZero(hourA);
			hourB = removeFrontZero(hourB);

			if(Integer.parseInt(hourA) > Integer.parseInt(hourB)) {
				return LARGER;
			} else {
				return SMALLER;
			}
		}
		return SAME;
	}
	
	protected static String removeFrontZero(String input) {
		while (input.length() > 0 && input.charAt(0) == '0') {
			input = input.substring(1);
		}
		return input;
	}
	
	protected static int addToBigBuffer(Appointment newAppointment) {

		Appointment nextAppointmentInBuffer = new Appointment();
		Appointment oldAppointmentInBuffer = new Appointment();
		Task nextTaskInBuffer = new Task();
		Task oldTaskInBuffer = new Task();
		int count = 0;
		boolean previousIsTask = false;
		boolean previousIsAppointment = false;

		return count;
	}

	protected static int addTaskToBigBuffer(Task newTask) {

		Appointment nextAppointmentInBuffer = new Appointment();
		Appointment oldAppointmentInBuffer = new Appointment();
		Task nextTaskInBuffer = new Task();
		Task oldTaskInBuffer = new Task();
		int count = 0;
		boolean previousIsTask = false;
		boolean previousIsAppointment = false;

		return count;
	}

	protected static boolean isClashing(Appointment newAppointment) {

		boolean isClashing = false;
		ListIterator<Assignment> buffer = InternalStorage.getBuffer().listIterator();
		Appointment checkAppointment = new Appointment();

		while(buffer.hasNext()) {
			Assignment assignment = buffer.next();
			if(!assignment.getIsDone()) {
				if(assignment.getAssignType().equals(AssignmentType.APPT)) {
					checkAppointment = ((Appointment) assignment); 

					if(dateComparator(newAppointment.getEndDate(), 
							checkAppointment.getEndDate()) == SAME &&
							Comparator.dateComparator(newAppointment.getStartDate(), 
									checkAppointment.getStartDate()) == SAME) {
						
						isClashing = isClashingTime(newAppointment, checkAppointment);
					} else {
						isClashing = isClashingDate(newAppointment, checkAppointment);
					}
					if(isClashing) {
						break;
					}
				}
			}
		}
		return isClashing;
	}

	private static boolean isClashingDate(Appointment newAppointment, Appointment checkAppointment) {

		boolean isClashing = true;

		if(dateComparator(checkAppointment.getStartDate(), newAppointment.getStartDate()) == LARGER &&
				dateComparator(checkAppointment.getStartDate(), newAppointment.getEndDate()) == SAME) {
			isClashing = isClashingTime2(newAppointment, checkAppointment);
			
		} else if(dateComparator(checkAppointment.getStartDate(), newAppointment.getStartDate()) == LARGER &&
				dateComparator(checkAppointment.getStartDate(), newAppointment.getEndDate()) == LARGER) {
			isClashing = false;
			
		} else if(dateComparator(checkAppointment.getEndDate(), newAppointment.getEndDate()) == SMALLER &&
				dateComparator(checkAppointment.getEndDate(), newAppointment.getStartDate()) == SMALLER) {
			isClashing = false;
			
		} else if(dateComparator(checkAppointment.getEndDate(), newAppointment.getEndDate()) == SMALLER &&
				dateComparator(checkAppointment.getEndDate(), newAppointment.getStartDate()) == SAME) {
			isClashing = isClashingTime2(checkAppointment, newAppointment);
		} 
		return isClashing;
	}

	private static boolean isClashingTime(Appointment newAppointment, Appointment checkAppointment) {

		boolean isClashing = true;

		if(timeComparator(checkAppointment.getStartTime(), newAppointment.getStartTime()) == LARGER &&
				timeComparator(checkAppointment.getStartTime(), newAppointment.getEndTime()) == SAME) {
			isClashing = false;
		} else if(timeComparator(checkAppointment.getStartTime(), newAppointment.getStartTime()) == LARGER &&
				timeComparator(checkAppointment.getStartTime(), newAppointment.getEndTime()) == LARGER) {
			isClashing = false;
		} else if(timeComparator(checkAppointment.getEndTime(), newAppointment.getEndTime()) == SMALLER &&
				timeComparator(checkAppointment.getEndTime(), newAppointment.getStartTime()) == SMALLER) {
			isClashing = false;
		} else if(timeComparator(checkAppointment.getEndTime(), newAppointment.getEndTime()) == SMALLER &&
				timeComparator(checkAppointment.getEndTime(), newAppointment.getStartTime()) == SAME) {
			isClashing = false;
		} 
		return isClashing;
	}
	
	private static boolean isClashingTime2(Appointment newAppointment, Appointment checkAppointment) {
		
		boolean isClashing = true;
		
		if(timeComparator(checkAppointment.getStartTime(), newAppointment.getEndTime()) == SAME ||
				timeComparator(checkAppointment.getStartTime(), newAppointment.getEndTime()) == LARGER) {
			isClashing = false;
		}
		return isClashing;
	}

	protected static void checkOnTime(String currentDate, String currentTime, int bufferPosition) {
		
		Appointment appointmentInBuffer = new Appointment();
		Task taskInBuffer = new Task();
		
		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.TASK)) {
			taskInBuffer = ((Task) InternalStorage.getBuffer().get(bufferPosition)); 

			if (Comparator.dateComparator(currentDate, taskInBuffer.getEndDate()) == SMALLER) {
				InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(true);

			} else if (Comparator.dateComparator(currentDate, taskInBuffer.getEndDate()) == SAME) {
				if (Comparator.timeComparator(currentTime, taskInBuffer.getEndTime()) == SMALLER) {
					InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(true);
				}
			}
		} else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			appointmentInBuffer = ((Appointment) InternalStorage.getBuffer().get(bufferPosition)); 

			if (Comparator.dateComparator(currentDate, appointmentInBuffer.getEndDate()) == SMALLER) {
				InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(true);

			} else if (Comparator.dateComparator(currentDate, appointmentInBuffer.getEndDate()) == SAME) {
				if (Comparator.timeComparator(currentTime, appointmentInBuffer.getEndTime()) == SMALLER) {
					InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(true);
				}
			}
		} else {
			InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(false);
		}
	}
}
