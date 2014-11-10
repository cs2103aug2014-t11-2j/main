package logic;

import java.util.LinkedList;

import logic.Assignment.AssignmentType;
import parser.EnumGroup.CommandType;

//@author A0111572R

public class RedoTask {

	private static int position;

	protected static void redo() {

		FutureHistory futureHistory = InternalStorage.popFuture();

		if(futureHistory.getCommand().equals(CommandType.ADD)) {
			redoAdd(futureHistory.getSerial());

		} else if(futureHistory.getCommand().equals(CommandType.DONE)) {
			redoDone(futureHistory.getSerial());

		} else if(futureHistory.getCommand().equals(CommandType.EDIT)) {
			redoEdit(futureHistory);

		} else if(futureHistory.getCommand().equals(CommandType.DELETE)) {
			redoDelete(futureHistory);

		} else if(futureHistory.getCommand().equals(CommandType.TENTATIVE)) {
			redoTentative(futureHistory);

		}else if(futureHistory.getCommand().equals(CommandType.CLEAR)) {
			LinkedList<Assignment> buffer = futureHistory.getClearedHistory();
			redoClear(buffer);

		} else {
			position = InternalStorage.getBufferPosition(futureHistory.getSerial());
			redoConfirm(position, futureHistory.getAppointment());
		}
	}
	
	private static void redoAdd(int id) {

		FutureHistory historyFuture = new FutureHistory();
		position = InternalStorage.getBufferPosition(id);

		historyFuture = RedoUndoUpdate.updateDelete(position);
		InternalStorage.pushHistory(historyFuture);
		Delete.delete(id);
	}

	private static void redoTentative(FutureHistory futureHistory) {

		FutureHistory historyFuture = new FutureHistory();
		int id;

		SetTentative.addTentativeToBuffer(futureHistory.getTentative());
		id = futureHistory.getTentative().getIndex();

		historyFuture = RedoUndoUpdate.updateTentative(id);

		InternalStorage.pushHistory(historyFuture);
	}

	private static void redoDone(int id) {

		FutureHistory historyFuture = new FutureHistory();
		position = InternalStorage.getBufferPosition(id);

		InternalStorage.getBuffer().get(position).setIsDone(true);
		InternalStorage.addBufferFirst(InternalStorage.getBuffer().remove(position));

		historyFuture = RedoUndoUpdate.updateDone(id, position);
		InternalStorage.pushHistory(historyFuture);
	}

	private static void redoEdit(FutureHistory futureHistory) {

		FutureHistory historyFuture = new FutureHistory();
		position = InternalStorage.getBufferPosition(futureHistory.getSerial());

		historyFuture = RedoUndoUpdate.updateEdit(futureHistory.getSerial());

		redoEdit2(futureHistory);
		
		InternalStorage.pushHistory(historyFuture);
	}
	
	private static void redoEdit2(FutureHistory futureHistory) {
		
		if(futureHistory.getAssignType().equals(AssignmentType.ASGN)) {
			InternalStorage.getBuffer().remove(position);
			Add.addAssignmentToBuffer(futureHistory.getAssignment());				
		} else if(futureHistory.getAssignType().equals(AssignmentType.APPT)) {
			InternalStorage.getBuffer().remove(position);
			Add.addAppointmentToBuffer(futureHistory.getAppointment());
		} else if(futureHistory.getAssignType().equals(AssignmentType.TASK)) {
			InternalStorage.getBuffer().remove(position);
			Add.addTaskToBuffer(futureHistory.getTask());
		} else {
			InternalStorage.getBuffer().remove(position);
			SetTentative.addTentativeToBuffer(futureHistory.getTentative());
		}
	}

	private static void redoDelete(FutureHistory futureHistory) {

		FutureHistory historyFuture = new FutureHistory();
		position = InternalStorage.getBufferPosition(futureHistory.getSerial());
		int id;

		id = redoDelete2(futureHistory);
		historyFuture = RedoUndoUpdate.updateAdd(id);

		InternalStorage.pushHistory(historyFuture);
	}
	
	private static int redoDelete2(FutureHistory futureHistory) {
		
		int id;
		
		if(futureHistory.getAssignType().equals(AssignmentType.ASGN)) {
			Add.addAssignmentToBuffer(futureHistory.getAssignment());
			id = futureHistory.getAssignment().getIndex();

		} else if(futureHistory.getAssignType().equals(AssignmentType.APPT)) {
			Add.addAppointmentToBuffer(futureHistory.getAppointment());
			id = futureHistory.getAppointment().getIndex();

		} else if(futureHistory.getAssignType().equals(AssignmentType.TASK)) {
			Add.addTaskToBuffer(futureHistory.getTask());
			id = futureHistory.getTask().getIndex();

		} else {
			SetTentative.addTentativeToBuffer(futureHistory.getTentative());
			id = futureHistory.getTentative().getIndex();
		}
		return id;
	}
	
	private static void redoClear(LinkedList<Assignment> buffer) {

		FutureHistory futureHistory = new FutureHistory();

		futureHistory = RedoUndoUpdate.updateClear(buffer);

		for(int i = 0; i < buffer.size(); i++) {
			Delete.delete(buffer.get(i).getIndex());
		}
		InternalStorage.pushHistory(futureHistory);
	}

	private static void redoConfirm(int position, Appointment appointment) {

		FutureHistory historyFuture = new FutureHistory();
		
		historyFuture = RedoUndoUpdate.updateConfirmBack(appointment, appointment.getIndex());

		InternalStorage.getBuffer().remove(position);
		Add.addAppointmentToBuffer(appointment);

		InternalStorage.pushHistory(historyFuture);
	}
}