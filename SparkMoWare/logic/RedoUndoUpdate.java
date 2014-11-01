package logic;

import java.util.LinkedList;

import logic.Assignment.AssignmentType;
import parser.EnumGroup.CommandType;

public class RedoUndoUpdate {
	
	private static FutureHistory futureHistory = new FutureHistory();
	
	protected static FutureHistory updateAdd(String id) {
		
		futureHistory.setCommand(CommandType.ADD);
		futureHistory.setSerial(id);
		
		return futureHistory;
	}
	
	protected static FutureHistory updateEdit(String id, int position) {
		
		futureHistory.setSerial(id);
		updateAssignment(position);
		futureHistory.setCommand(CommandType.EDIT);
		
		return futureHistory;
	}
	
	protected static FutureHistory updateDelete(int position) {
		
		updateAssignment(position);
		futureHistory.setCommand(CommandType.DELETE);
		
		return futureHistory;
	}
	
	private static void updateAssignment(int position) {
		
		if(InternalStorage.getBuffer().get(position).getAssignType().equals(AssignmentType.ASGN)) {
			futureHistory.setAssignment(InternalStorage.getBuffer().get(position));
			futureHistory.setAssignType(AssignmentType.ASGN);
			
		} else if(InternalStorage.getBuffer().get(position).getAssignType().equals(AssignmentType.APPT)) {
			Appointment appointment = (Appointment) InternalStorage.getBuffer().get(position);
			futureHistory.setAppointment(appointment);
			futureHistory.setAssignType(AssignmentType.APPT);
			
		} else if(InternalStorage.getBuffer().get(position).getAssignType().equals(AssignmentType.TASK)) {
			Task task = (Task) InternalStorage.getBuffer().get(position);
			futureHistory.setTask(task);
			futureHistory.setAssignType(AssignmentType.TASK);
			
		} else {
			Tentative tentative = (Tentative) InternalStorage.getBuffer().get(position);
			futureHistory.setTentative(tentative);
			futureHistory.setAssignType(AssignmentType.TNTV);
		}
	}
	
	protected static FutureHistory updateTentative(String id) {
		
		futureHistory.setCommand(CommandType.TENTATIVE);
		futureHistory.setSerial(id);

		return futureHistory;
	}
	
	protected static FutureHistory updateConfirm(Tentative tentative, String id) {
		
		futureHistory.setTentative(tentative);
		futureHistory.setCommand(CommandType.CONFIRM);
		futureHistory.setSerial(id);
		
		return futureHistory;
	}
	
	protected static FutureHistory updateConfirmBack(Appointment appointment, String id) {
		
		futureHistory.setAppointment(appointment);
		futureHistory.setCommand(CommandType.CONFIRM);
		futureHistory.setSerial(id);
		
		return futureHistory;
	}
	
	protected static FutureHistory updateClear(LinkedList<Assignment> deleted) {
		
		futureHistory.setCommand(CommandType.CLEAR);
		futureHistory.addClearedHistory(deleted);
		
		return futureHistory;
	}
	
	protected static FutureHistory updateDone(String id) {
		
		futureHistory.setCommand(CommandType.DONE);
		futureHistory.setSerial(id);

		return futureHistory;
	}
}