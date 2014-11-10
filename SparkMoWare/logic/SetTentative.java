package logic;

import java.util.Vector;

//@author A0111572R

public class SetTentative {

	protected static int addTentative(String title, Vector<String> dates, Vector<String> times) {

		Tentative newTentative = new Tentative();

		int tentativeIdGen = Id.serialNumGen();

		newTentative.setIndex(tentativeIdGen);
		newTentative.setTitle(title);
		newTentative.setPriority(Assignment.PRIORITY_NONE);
		
		setTimeSlot(newTentative, dates, times);
		addTentativeToBuffer(newTentative);
		
		return tentativeIdGen;
	}
	
	private static void setTimeSlot(Tentative newTentative, Vector<String> dates, Vector<String> times) {
		
		for(int vectorCount = 0; vectorCount < dates.size(); vectorCount++) {
			if(vectorCount % 2 == 0) {
				newTentative.addStartDate(dates.get(vectorCount));
				newTentative.addStartTime(times.get(vectorCount));
			} else {
				newTentative.addEndDate(dates.get(vectorCount));
				newTentative.addEndTime(times.get(vectorCount));
			}
		}
	}
	
	protected static void setToTentative(Appointment newAppointment) {

		Tentative newTentative = new Tentative();

		newTentative.setIndex(newAppointment.getIndex());
		newTentative.setTitle(newAppointment.getTitle());
		newTentative.addStartDate(newAppointment.getStartDate());
		newTentative.addStartTime(newAppointment.getStartTime());
		newTentative.addEndDate(newAppointment.getEndDate());
		newTentative.addEndTime(newAppointment.getEndTime());

		addTentativeToBuffer(newTentative);
	}

	protected static void addTentativeToBuffer(Tentative newTentative) {

		int position;
		
		if (InternalStorage.getLineCount() == 0) {
			InternalStorage.addBuffer(newTentative);
		} else {
			position = Comparator.addTentativeToBigBuffer(newTentative);
			InternalStorage.addBuffer(position, newTentative);
		}
	}
}