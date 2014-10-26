package logic;

import java.util.ListIterator;
import logic.Assignment.AssignmentType;

/*
 * Allows the user to create a number of tentative dates 
 * before confirming to have only one date for user's appointment
 * Title will be attached with the word [tentative]
 * userInput must be in this format:
 * <title><ddmmyyyy><hhmm><ddmmyyyy><hhmm>
 */

public class SetTentative {

	public static Tentative addTentative(String tentativeTitle) {

		Tentative newTentative = new Tentative();

		String tentativeIdGenerated = Id.serialNumGen();

		newTentative.setId(tentativeIdGenerated);
		newTentative.setTitle(tentativeTitle);
		newTentative.setPriority(Assignment.PRIORITY_NONE);

		return newTentative;
	}

	protected static void addTentativeAppt(Tentative newTentative, String startDate,
			String startTime, String endDate, String endTime) {

		newTentative.addStartDate(startDate);
		newTentative.addStartTime(startTime);
		newTentative.addEndDate(endDate);
		newTentative.addEndTime(endTime);
	}

	protected static void addSingleTentative(String title, String startDate, String startTime,
			String endDate, String endTime) {

		Tentative newTentative = new Tentative();
		
		newTentative.setId(Id.serialNumGen());
		newTentative.setTitle(title);
		newTentative.addStartDate(startDate);
		newTentative.addStartTime(startTime);
		newTentative.addEndDate(endDate);
		newTentative.addEndTime(endTime);

		addTentativeToBuffer(newTentative);
	}
	
	protected static void setToTentative(Appointment newAppointment) {

		Tentative newTentative = new Tentative();

		newTentative.setId(newAppointment.getId());
		newTentative.setTitle(newAppointment.getTitle());
		newTentative.addStartDate(newAppointment.getStartDate());
		newTentative.addStartTime(newAppointment.getStartTime());
		newTentative.addEndDate(newAppointment.getEndDate());
		newTentative.addEndTime(newAppointment.getEndTime());

		addTentativeToBuffer(newTentative);
	}

	private static void addTentativeToBuffer(Tentative newTentative) {

		int bufferPosition = 0;

		if (InternalStorage.getLineCount() == 0) {
			InternalStorage.addBuffer(newTentative);
		} else {

			ListIterator<Assignment> buffer = InternalStorage.getBuffer().listIterator();

			while(buffer.hasNext()) {

				if(buffer.next().getAssignType().equals(AssignmentType.APPOINTMENT)
						|| buffer.next().getAssignType().equals(AssignmentType.TASK)
						|| buffer.next().getAssignType().equals(AssignmentType.TENTATIVE)) {
					InternalStorage.addBuffer(bufferPosition, newTentative);
				}
				bufferPosition++;
			}
		}
	}
}