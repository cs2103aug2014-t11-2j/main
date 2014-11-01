package logic;

import java.util.ListIterator;
import java.util.Vector;
import logic.Assignment.AssignmentType;

public class SetTentative {

	public static String addTentative(String title, Vector<String> dates, Vector<String> times) {

		Tentative newTentative = new Tentative();

		String tentativeIdGenerated = Id.serialNumGen();

		newTentative.setId(tentativeIdGenerated);
		newTentative.setTitle(title);
		newTentative.setPriority(Assignment.PRIORITY_NONE);
		
		for(int vectorCount = 0; vectorCount < dates.size(); vectorCount++) {
			if(vectorCount % 2 == 0) {
				newTentative.addStartDate(dates.get(vectorCount));
				newTentative.addStartTime(times.get(vectorCount));
			} else {
				newTentative.addEndDate(dates.get(vectorCount));
				newTentative.addEndTime(times.get(vectorCount));
			}
		}
		InternalStorage.addBuffer(newTentative);
		
		return tentativeIdGenerated;
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

	protected static void addTentativeToBuffer(Tentative newTentative) {

		int bufferPosition = 0;

		if (InternalStorage.getLineCount() == 0) {
			InternalStorage.addBuffer(newTentative);
		} else {

			ListIterator<Assignment> buffer = InternalStorage.getBuffer().listIterator();

			while(buffer.hasNext()) {
				Assignment assignment = buffer.next();
				
				if(assignment.getAssignType().equals(AssignmentType.APPT) ||
						assignment.getAssignType().equals(AssignmentType.TASK)) {
					InternalStorage.addBuffer(bufferPosition, newTentative);
					break;
				}
				bufferPosition++;
			}
		}
	}
}