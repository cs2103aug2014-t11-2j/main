package logic;

import java.util.LinkedList;
import java.util.Vector;

/**
 * Logic: Confirm component to confirm any tentative appointment
 * 		  with the time slot input.
 * @author Teck Zhi
 */

public class ConfirmTentative {

	private static Tentative tentativeNeeded = new Tentative();

	protected static int confirmTentative(int serialId, String confirmStartDate, String confirmStartTime, 
			String confirmEndDate, String confirmEndTime) {

		Appointment confirmAppointment = new Appointment();
		LinkedList<Assignment> tentatives = new LinkedList<Assignment> ();

		tentatives = SearchAll.searchAll(InternalStorage.getBuffer(), Integer.toString(serialId));

		if(tentatives.size() > 0) {
		 
			tentativeNeeded = ((Tentative) tentatives.get(0));

			confirmAppointment = findConfirmTentative(confirmStartDate, confirmStartTime, confirmEndDate, confirmEndTime);
			int id = Id.serialNumGen();
			confirmAppointment.setIndex(id);
			Delete.delete(tentativeNeeded.getIndex());
			Add.addAppointmentToBuffer(confirmAppointment);
		}
		return confirmAppointment.getIndex();
	}

	private static Appointment findConfirmTentative(String confirmStartDate, String confirmStartTime, 
			String confirmEndDate, String confirmEndTime){

		Appointment newAppointment = new Appointment();
		Vector<String> startDate = tentativeNeeded.getStartDate();
		Vector<String> startTime = tentativeNeeded.getStartTime();
		Vector<String> endDate = tentativeNeeded.getEndDate();
		Vector<String> endTime = tentativeNeeded.getEndTime();

		int size = startDate.size();

		for(int dateFormatCount = 0; dateFormatCount < size; dateFormatCount++) {

			if(startDate.get(dateFormatCount).equals(confirmStartDate) &&
					startTime.get(dateFormatCount).equals(confirmStartTime) &&
					endDate.get(dateFormatCount).equals(confirmEndDate) &&
					endTime.get(dateFormatCount).equals(confirmEndTime)) {

				newAppointment.setTitle(tentativeNeeded.getTitle());
				newAppointment.setStartDate(startDate.get(dateFormatCount));
				newAppointment.setStartTime(startTime.get(dateFormatCount));
				newAppointment.setEndDate(endDate.get(dateFormatCount));
				newAppointment.setEndTime(endTime.get(dateFormatCount));
			}
		}
		return newAppointment;
	}
}