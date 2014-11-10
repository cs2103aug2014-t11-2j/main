package logic;

import java.util.LinkedList;

import logic.Assignment.AssignmentType;
import parser.EnumGroup.CommandType;

//@author A0111572R

public class RedoUndoUpdate {
	
	private static FutureHistory futureHistory = new FutureHistory();
	
	protected static FutureHistory updateAdd(int id) {
		
		futureHistory.setCommand(CommandType.ADD);
		futureHistory.setSerial(id);
		
		return futureHistory;
	}
	
	protected static FutureHistory updateEdit(int id) {
		
		futureHistory.setSerial(id);
		int position = InternalStorage.getBufferPosition(id);
		updateAssignment(position);
		futureHistory.setCommand(CommandType.EDIT);
		
		return futureHistory;
	}
	
	protected static FutureHistory updateEditOver(Assignment assignment) {
		
		if(assignment.getAssignType().equals(AssignmentType.APPT)) {
			futureHistory.setAssignType(AssignmentType.APPT);
			futureHistory.setAppointment((Appointment) assignment);

		} else if(assignment.getAssignType().equals(AssignmentType.TASK)) {
			futureHistory.setAssignType(AssignmentType.TASK);
			futureHistory.setTask((Task) assignment);
			
		} else if(assignment.getAssignType().equals(AssignmentType.TNTV)) {
			futureHistory.setAssignType(AssignmentType.TNTV);
			futureHistory.setTentative((Tentative) assignment);
		} else {
			futureHistory.setAssignType(AssignmentType.ASGN);
			futureHistory.setAssignment(assignment);
		}
		futureHistory.setCommand(CommandType.EDIT);
		
		return futureHistory;
	}
	protected static FutureHistory updateDelete(int position) {
		
		updateAssignment(position);
		futureHistory.setCommand(CommandType.DELETE);
		
		return futureHistory;
	}
	
	protected static FutureHistory updateDeleteTentative(int position) {
		
		updateAssignment(position);
		futureHistory.setCommand(CommandType.TENTATIVE);
		
		return futureHistory;
	}
	
	private static void updateAssignment(int position) {
		
		if(InternalStorage.getBuffer().get(position).getAssignType().equals(AssignmentType.ASGN)) {
			futureHistory.setAssignment(InternalStorage.getBuffer().get(position));
			futureHistory.setAssignType(AssignmentType.ASGN);
			
		} else if(InternalStorage.getBuffer().get(position).getAssignType().equals(AssignmentType.TASK)) {
			Task task = (Task) InternalStorage.getBuffer().get(position);
			futureHistory.setTask(task);
			futureHistory.setAssignType(AssignmentType.TASK);
			
		} else if(InternalStorage.getBuffer().get(position).getAssignType().equals(AssignmentType.APPT)) {
			Appointment appointment = (Appointment) InternalStorage.getBuffer().get(position);
			futureHistory.setAppointment(appointment);
			futureHistory.setAssignType(AssignmentType.APPT);
			
		} else {
			Tentative tentative = (Tentative) InternalStorage.getBuffer().get(position);
			futureHistory.setTentative(tentative);
			futureHistory.setAssignType(AssignmentType.TNTV);
		}
	}
	
	protected static FutureHistory updateTentative(int id) {
		
		futureHistory.setCommand(CommandType.TENTATIVE);
		futureHistory.setSerial(id);

		return futureHistory;
	}
	
	protected static FutureHistory updateConfirm(Tentative tentative, int id) {
		
		futureHistory.setTentative(tentative);
		futureHistory.setCommand(CommandType.CONFIRM);
		futureHistory.setSerial(id);
		
		return futureHistory;
	}
	
	protected static FutureHistory updateConfirmBack(Appointment appointment, int id) {
		
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
	
	protected static FutureHistory updateAddBack() {
		
		futureHistory.setCommand(CommandType.CLEAR);
		
		return futureHistory;
	}
	
	protected static FutureHistory updateDone(int id, int position) {
		
		futureHistory.setCommand(CommandType.DONE);
		futureHistory.setPosition(position);
		futureHistory.setSerial(id);

		return futureHistory;
	}
}