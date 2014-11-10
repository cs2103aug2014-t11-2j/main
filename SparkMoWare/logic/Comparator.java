package logic;

import java.util.ListIterator;

import logic.Assignment.AssignmentType;

//@author A0111572R

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

		String yearA = retrieveYear(dateA);
		String yearB = retrieveYear(dateB);

		String monthA = retrieveMonth(dateA);
		String monthB = retrieveMonth(dateB);

		String dayA = retrieveDay(dateA);
		String dayB = retrieveDay(dateB);


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

	private static String retrieveYear(String date) {

		String year = date.trim().substring(6, 8);
		year = removeFrontZero(year);

		return year;
	}

	private static String retrieveMonth(String date) {

		String month = date.trim().substring(3, 5);
		month = removeFrontZero(month);

		return month;
	}

	private static String retrieveDay(String date) {

		String day = date.trim().substring(0, 2);
		day = removeFrontZero(day);

		return day;
	}

	protected static int timeComparator(String timeA, String timeB) {

		String hourA = retrieveHour(timeA);
		String hourB = retrieveHour(timeB);

		String minA = retrieveMin(timeA);
		String minB = retrieveMin(timeB);

		int compareResult = compareHour(hourA, hourB, minA, minB);

		return compareResult;
	}

	private static String retrieveHour(String time) {

		String hour = time.trim().substring(0, 2);
		return hour;
	}

	private static String retrieveMin(String time) {

		String min = time.trim().substring(2, 4);
		return min;
	}

	private static int compareHour(String hourA, String hourB, String minA, String minB) {

		int compareResult = SAME;

		if(hourA.equals(hourB)) {
			compareResult = compareMin(minA, minB);
		} else if(hourA.equals("00") && !hourB.equals("00")) {
			compareResult = SMALLER;
		} else if(!hourA.equals("00") && hourB.equals("00")) {
			compareResult = LARGER;
		} else if(!hourA.equals("00") && !hourB.equals("00")) {
			hourA = removeFrontZero(hourA);
			hourB = removeFrontZero(hourB);

			if(Integer.parseInt(hourA) > Integer.parseInt(hourB)) {
				compareResult = LARGER;
			} else {
				compareResult = SMALLER;
			}
		}
		return compareResult;		
	}

	private static int compareMin(String minA, String minB) {

		int compareResult = SAME;

		if(!minA.equals(minB)) {
			if(minA.equals("00") && !minB.equals("00")) {
				compareResult = SMALLER;
			} else if(!minA.equals("00") && minB.equals("00")) {
				compareResult = LARGER;
			} else if(!minA.equals("00") && !minB.equals("00")) {
				minA = removeFrontZero(minA);
				minB = removeFrontZero(minB);

				if(Integer.parseInt(minA) > Integer.parseInt(minB)) {
					compareResult = LARGER;
				} else {
					compareResult = SMALLER;
				}
			}
		}
		return compareResult;
	}

	protected static String removeFrontZero(String input) {
		while (input.length() > 0 && input.charAt(0) == '0') {
			input = input.substring(1);
		}
		return input;
	}

	protected static int addToBigBuffer(Appointment newAppointment) {

		int count = 0;

		ListIterator<Assignment> bufferList = InternalStorage.getBuffer().listIterator();

		while(bufferList.hasNext()) {
			Assignment assignment = bufferList.next();
			if(checkFittingTimeSlot(assignment, newAppointment)) {
				break;
			}
			count++;
		}
		return count;
	}

	private static boolean checkFittingTimeSlot(Assignment assignment, Appointment newAppointment) {

		Appointment nextAppointmentInBuffer = new Appointment();
		Task nextTaskInBuffer = new Task();

		if(assignment.getAssignType().equals(AssignmentType.TASK)) {
			nextTaskInBuffer = ((Task) assignment);
			return compareWithTask(newAppointment, nextTaskInBuffer);

		} else if(assignment.getAssignType().equals(AssignmentType.APPT)) {
			nextAppointmentInBuffer = ((Appointment) assignment);
			return compareWithAppointment(newAppointment, nextAppointmentInBuffer);
		}
		return false;
	}

	private static boolean compareWithTask(Appointment newAppointment, Task nextTaskInBuffer) {

		if(Comparator.dateComparator(newAppointment.getEndDate(), nextTaskInBuffer.getEndDate()) == 0) {
			return true;
		} else if(Comparator.dateComparator(newAppointment.getEndDate(), nextTaskInBuffer.getEndDate()) == -1) {
			return true;
		}
		return false;
	}

	private static boolean compareWithAppointment(Appointment newAppointment, Appointment nextAppointmentInBuffer) {

		if(Comparator.dateComparator(newAppointment.getEndDate(), nextAppointmentInBuffer.getEndDate()) == -1) {
			return true;
		} else if(Comparator.dateComparator(newAppointment.getEndDate(), nextAppointmentInBuffer.getEndDate()) == 0) {
			if(Comparator.timeComparator(newAppointment.getEndTime(), nextAppointmentInBuffer.getEndTime()) == -1) {
				return true;
			}
		}
		return false;
	}

	protected static int addTaskToBigBuffer(Task newTask) {

		int count = 0;

		ListIterator<Assignment> bufferList = InternalStorage.getBuffer().listIterator();

		while(bufferList.hasNext()) {
			Assignment assignment = bufferList.next();

			if(checkFittingTimeSlot2(assignment, newTask) == 1){
				break;
			} else if(checkFittingTimeSlot2(assignment, newTask) == 2) {
				count++;
				break;
			}
			count++;
		}
		return count;
	}

	private static int checkFittingTimeSlot2(Assignment assignment, Task newTask) {

		Appointment nextAppointmentInBuffer = new Appointment();
		Task nextTaskInBuffer = new Task();

		if(assignment.getAssignType().equals(AssignmentType.TASK)) {
			nextTaskInBuffer = ((Task) assignment);
			return compareWithTask2(newTask, nextTaskInBuffer);

		} else if(assignment.getAssignType().equals(AssignmentType.APPT)) {
			nextAppointmentInBuffer = ((Appointment) assignment);
			return compareWithAppointment2(newTask, nextAppointmentInBuffer);
		}
		return -1;
	}

	private static int compareWithTask2(Task newTask, Task nextTaskInBuffer) {

		if(Comparator.dateComparator(newTask.getEndDate(), nextTaskInBuffer.getEndDate()) == 0) {
			return 1;
		} else if(Comparator.dateComparator(newTask.getEndDate(), nextTaskInBuffer.getEndDate()) == -1) {
			return 1;
		}
		return -1;
	}

	private static int compareWithAppointment2(Task newTask, Appointment nextAppointmentInBuffer) {

		if(Comparator.dateComparator(newTask.getEndDate(), nextAppointmentInBuffer.getEndDate()) == -1) {
			return 1;
		} else if(Comparator.dateComparator(newTask.getEndDate(), nextAppointmentInBuffer.getEndDate()) == 0) {
			return 2;
		}
		return -1;
	}

	protected static int addTentativeToBigBuffer(Tentative newTentative) {

		int bufferPosition = 0;
		ListIterator<Assignment> buffer = InternalStorage.getBuffer().listIterator();

		while(buffer.hasNext()) {
			Assignment assignment = buffer.next();

			if(checkFittingTentativeTimeSlot(assignment, bufferPosition)) {
				break;
			}
			bufferPosition++;
		}
		return bufferPosition;
	}

	private static boolean checkFittingTentativeTimeSlot(Assignment assignment, int bufferPosition) {

		if((assignment.getAssignType().equals(AssignmentType.APPT) ||
				assignment.getAssignType().equals(AssignmentType.TASK)) && 
				assignment.getIsDone() == false) {
			return true;
		} else if(bufferPosition == InternalStorage.getLineCount() - 1) {
			return true;
		}
		return false;
	}

	protected static boolean isClashing(Appointment newAppointment) {

		boolean isClashing = false;
		ListIterator<Assignment> buffer = InternalStorage.getBuffer().listIterator();

		while(buffer.hasNext()) {
			Assignment assignment = buffer.next();
			isClashing = checkClashing(assignment, newAppointment);
			
			if(isClashing) {
				break;
			}
		}
		return isClashing;
	}

	private static boolean checkClashing(Assignment assignment, Appointment newAppointment) {

		boolean isClashing = false;
		Appointment checkAppointment = new Appointment();

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

		Task taskInBuffer = new Task();

		if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.TASK)) {
			taskInBuffer = ((Task) InternalStorage.getBuffer().get(bufferPosition)); 
			checkOnTime2(currentDate, currentTime, taskInBuffer, bufferPosition);
			
		} else if(InternalStorage.getBuffer().get(bufferPosition).getAssignType().equals(AssignmentType.APPT)) {
			taskInBuffer = ((Task) InternalStorage.getBuffer().get(bufferPosition)); 
			checkOnTime2(currentDate, currentTime, taskInBuffer, bufferPosition);
			
		} else {
			InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(false);
		}
	}
	
	private static void checkOnTime2(String currentDate, String currentTime, Task taskInBuffer, int bufferPosition) {
		
		if (Comparator.dateComparator(currentDate, taskInBuffer.getEndDate()) == SMALLER) {
			InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(true);

		} else if (Comparator.dateComparator(currentDate, taskInBuffer.getEndDate()) == SAME) {
			if (Comparator.timeComparator(currentTime, taskInBuffer.getEndTime()) == SMALLER) {
				InternalStorage.getBuffer().get(bufferPosition).setIsOnTime(true);
			}
		}
	}	
}