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

	// compares id with nextId, return true if idA is bigger else return false
	// the format is 250920140001
	public static boolean serialNumberComparator(String idA, String idB) {

		assert(idA.length() == 12 && idA.length() == 12);

		int checkDate = dateComparator(idA.substring(0, 8), idB.substring(0, 8));
		boolean serialCheck = false;

		if (checkDate == SAME) {
			// check Sn
			idA = Id.removeFrontZero(idA.substring(8));

			idB = Id.removeFrontZero(idB.substring(8));

			if (Integer.parseInt(idA) > Integer.parseInt(idB)) {
				serialCheck = true;
			}
		} else if (checkDate == LARGER) {
			serialCheck = true;
		}
		return serialCheck;
	}

	public static int dateComparator(String dateA, String dateB) {

		assert(dateA.length() == 8 && dateB.length() == 8);

		String yearA = dateA.trim().substring(4, 8);
		String yearB = dateB.trim().substring(4, 8);

		String monthA = dateA.trim().substring(2, 4);
		String monthB = dateB.trim().substring(2, 4);

		String dayA = dateA.trim().substring(0, 2);
		String dayB = dateB.trim().substring(0, 2);

		yearA = Id.removeFrontZero(yearA);
		yearB = Id.removeFrontZero(yearB);

		monthA = Id.removeFrontZero(monthA);
		monthB = Id.removeFrontZero(monthB);

		dayA = Id.removeFrontZero(dayA);
		dayB = Id.removeFrontZero(dayB);

		if (dateA.equals(dateB)) {
			return SAME;
		} else if (Integer.parseInt(yearA) > Integer.parseInt(yearB)) {
			return LARGER;
		} else if (Integer.parseInt(yearA) < Integer.parseInt(yearB)) {
			return SMALLER;
		} else if (Integer.parseInt(monthA) > Integer.parseInt(monthB)) {
			assert (Integer.parseInt(yearA) == Integer.parseInt(yearB));
			return LARGER;
		} else if (Integer.parseInt(monthA) < Integer.parseInt(monthB)) {
			assert (Integer.parseInt(yearA) == Integer.parseInt(yearB));
			return SMALLER;
		} else if (Integer.parseInt(dayA) > Integer.parseInt(dayB)) {
			assert (Integer.parseInt(yearA) == Integer.parseInt(yearB) && Integer
					.parseInt(monthA) == Integer.parseInt(monthB));
			return LARGER;
		}
		assert (Integer.parseInt(yearA) == Integer.parseInt(yearB) && Integer
				.parseInt(monthA) == Integer.parseInt(monthB));
		return SMALLER;
	}

	public static int timeComparator(String timeA, String timeB) {

		assert(timeA.length() == 4 && timeB.length() == 4);

		String hourA = timeA.trim().substring(0, 2);
		String hourB = timeA.trim().substring(0, 2);

		String minA = timeB.trim().substring(2, 4);
		String minB = timeB.trim().substring(2, 4);

		hourA = Id.removeFrontZero(hourA);
		hourB = Id.removeFrontZero(hourB);

		minA = Id.removeFrontZero(minA);
		minB = Id.removeFrontZero(minB);

		if (timeA.equals(timeB)) {
			return SAME;
		} else if (Integer.parseInt(hourA) > Integer.parseInt(hourB)) {
			return LARGER;
		} else if (Integer.parseInt(hourA) < Integer.parseInt(hourB)) {
			return SMALLER;
		} else if (Integer.parseInt(minA) > Integer.parseInt(minB)) {
			assert(Integer.parseInt(hourA) == Integer.parseInt(hourB));
			return LARGER;
		}
		assert(Integer.parseInt(hourA) == Integer.parseInt(hourB));
		return SMALLER;
	}

	protected static int addToBigBuffer(Appointment newAppointment) {

		Appointment appointmentInBuffer = new Appointment();
		Task taskInBuffer = new Task();
		int count = 0;
		ListIterator<Assignment> buffer = InternalStorage.getBuffer().listIterator();
		
		while(buffer.hasNext()) {
			count++;
			if (buffer.next().getAssignType().equals(AssignmentType.APPOINTMENT)) {

				appointmentInBuffer = ((Appointment) buffer.previous());

				if (Comparator.dateComparator(newAppointment.getEndDate(),
						appointmentInBuffer.getEndDate()) == -1) {
					break;
				}
			} 
<<<<<<< HEAD
			if(buffer.previous().getAssignType().equals(AssignmentType.TASK)) {
				taskInBuffer = ((Task) buffer.next());
				
				if (Comparator.dateComparator(newAppointment.getEndDate(),
						taskInBuffer.getEndDate()) == -1) {
					break;
				}
			}
			// assert InternalStorage.getBuffer().get(bufferCount).getAssignType().equals(AssignmentType.APPOINTMENT)|| InternalStorage.getBuffer().get(bufferCount).getAssignType().equals(AssignmentType.TASK);
=======

			assert InternalStorage.getBuffer().get(bufferCount).getAssignType()
			.equals(AssignmentType.APPOINTMENT)
			|| InternalStorage.getBuffer().get(bufferCount)
			.getAssignType().equals(AssignmentType.TASK);
>>>>>>> ff6347f8122bd4f9f6b0a145ff81802479089943
		}
		return count;
	}

	protected static boolean isClashing(Appointment newAppointment) {

		boolean isClashing = false;
		ListIterator<Assignment> buffer = InternalStorage.getBuffer().listIterator();
		Appointment checkAppointment = new Appointment();

		while(buffer.hasNext()) {
			if(buffer.next().getAssignType().equals(AssignmentType.APPOINTMENT)) {
				checkAppointment = ((Appointment) buffer.next()); 
			}
			
			if(Comparator.dateComparator(newAppointment.getEndDate(), 
					checkAppointment.getEndDate()) == SAME &&
					Comparator.dateComparator(newAppointment.getStartDate(), 
							checkAppointment.getStartDate()) == SAME) {
				
				isClashing = isClashingTime(newAppointment, checkAppointment);
			}
			isClashing = isClashingDate(newAppointment, checkAppointment);
		}
		return isClashing;
	}
	
	private static boolean isClashingDate(Appointment newAppointment, Appointment checkAppointment) {
		
		boolean isClashing = false;
		
		if(Comparator.dateComparator(newAppointment.getEndDate(), 
				checkAppointment.getEndDate()) == SMALLER &&
				Comparator.dateComparator(newAppointment.getStartDate(), 
						checkAppointment.getStartDate()) == LARGER) {
			isClashing = true;

		} else if(Comparator.dateComparator(newAppointment.getEndDate(), 
				checkAppointment.getStartDate()) == LARGER &&
				Comparator.dateComparator(newAppointment.getStartDate(), 
						checkAppointment.getStartDate()) == SMALLER) {
			isClashing = true;
		
		} else if(Comparator.dateComparator(newAppointment.getEndDate(), 
				checkAppointment.getEndDate()) == LARGER &&
				Comparator.dateComparator(newAppointment.getStartDate(), 
						checkAppointment.getEndDate()) == SMALLER) {
			isClashing = true;
			
		} else if(Comparator.dateComparator(newAppointment.getEndDate(), 
				checkAppointment.getEndDate()) == LARGER &&
				Comparator.dateComparator(newAppointment.getStartDate(), 
						checkAppointment.getStartDate()) == LARGER) {
			isClashing = true;
		} 
		return isClashing;
	}
	
	private static boolean isClashingTime(Appointment newAppointment, Appointment checkAppointment) {
		
		boolean isClashing = false;
		
		if(Comparator.dateComparator(newAppointment.getEndTime(), 
				checkAppointment.getEndTime()) == SMALLER &&
				Comparator.dateComparator(newAppointment.getStartTime(), 
						checkAppointment.getStartTime()) == LARGER) {
			isClashing = true;

		} else if(Comparator.dateComparator(newAppointment.getEndTime(), 
				checkAppointment.getStartTime()) == LARGER &&
				Comparator.dateComparator(newAppointment.getStartTime(), 
						checkAppointment.getStartTime()) == SMALLER) {
			isClashing = true;
		
		} else if(Comparator.dateComparator(newAppointment.getEndTime(), 
				checkAppointment.getEndTime()) == LARGER &&
				Comparator.dateComparator(newAppointment.getStartTime(), 
						checkAppointment.getEndTime()) == SMALLER) {
			isClashing = true;
			
		} else if(Comparator.dateComparator(newAppointment.getEndTime(), 
				checkAppointment.getEndTime()) == LARGER &&
				Comparator.dateComparator(newAppointment.getStartTime(), 
						checkAppointment.getStartTime()) == LARGER) {
			isClashing = true;
		} 
		return isClashing;
	}
}
