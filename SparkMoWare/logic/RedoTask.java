package logic;

import java.util.LinkedList;

import logic.Assignment.AssignmentType;
import parser.EnumGroup.CommandType;

public class RedoTask {
	
	private static int position;
	
	public static void redo() {

		FutureHistory futureHistory = InternalStorage.popFuture();

		if(futureHistory.getCommand().equals(CommandType.ADD) ||
				futureHistory.getCommand().equals(CommandType.TENTATIVE)) {
			redoAdd(futureHistory.getSerial());

		} else if(futureHistory.getCommand().equals(CommandType.DONE)) {
			redoDone(futureHistory.getSerial());

		} else if(futureHistory.getCommand().equals(CommandType.EDIT)) {
			redoEdit(futureHistory);

		} else if(futureHistory.getCommand().equals(CommandType.DELETE)) {
			redoDelete(futureHistory);

		} else if(futureHistory.getCommand().equals(CommandType.CLEAR)) {
			LinkedList<Assignment> buffer = futureHistory.getClearedHistory();
			redoClear(buffer);

		} else {
			position = InternalStorage.getBufferPosition(futureHistory.getSerial());
			redoConfirm(position, futureHistory.getTentative());
		}
	}
	private static void redoAdd(String id) {
		
		FutureHistory historyFuture = new FutureHistory();
		position = InternalStorage.getBufferPosition(id);
		
		historyFuture = RedoUndoUpdate.updateDelete(position);
		InternalStorage.pushFuture(historyFuture);
		Delete.delete(id);
	}
	
	private static void redoDone(String id) {
		
		FutureHistory historyFuture = new FutureHistory();
		position = InternalStorage.getBufferPosition(id);
		
		InternalStorage.getBuffer().get(position).setIsDone(false);
		
		historyFuture = RedoUndoUpdate.updateDone(id);
		InternalStorage.pushFuture(historyFuture);
	}
	
	private static void redoEdit(FutureHistory futureHistory) {
		
		FutureHistory historyFuture = new FutureHistory();
		position = InternalStorage.getBufferPosition(futureHistory.getSerial());
		
		historyFuture = RedoUndoUpdate.updateEdit(futureHistory.getSerial(), position);
		
		if(futureHistory.getAssignType().equals(AssignmentType.ASSIGNMENT)) {
			InternalStorage.getBuffer().remove(position);
			Add.addAssignmentToBuffer(futureHistory.getAssignment());				
		} else if(futureHistory.getAssignType().equals(AssignmentType.APPOINTMENT)) {
			InternalStorage.getBuffer().remove(position);
			Add.addAppointmentToBuffer(futureHistory.getAppointment());
		} else if(futureHistory.getAssignType().equals(AssignmentType.TASK)) {
			InternalStorage.getBuffer().remove(position);
			Add.addTaskToBuffer(futureHistory.getTask());
		} else {
			InternalStorage.getBuffer().remove(position);
			SetTentative.addTentativeToBuffer(futureHistory.getTentative());
		}
		InternalStorage.pushFuture(historyFuture);
	}
	
	private static void redoDelete(FutureHistory futureHistory) {
		
		FutureHistory historyFuture = new FutureHistory();
		position = InternalStorage.getBufferPosition(futureHistory.getSerial());
		String id;
		
		if(futureHistory.getAssignType().equals(AssignmentType.ASSIGNMENT)) {
			Add.addAssignmentToBuffer(futureHistory.getAssignment());
			id = futureHistory.getAssignment().getId();
			
		} else if(futureHistory.getAssignType().equals(AssignmentType.APPOINTMENT)) {
			Add.addAppointmentToBuffer(futureHistory.getAppointment());
			id = futureHistory.getAppointment().getId();
			
		} else if(futureHistory.getAssignType().equals(AssignmentType.TASK)) {
			Add.addTaskToBuffer(futureHistory.getTask());
			id = futureHistory.getTask().getId();
			
		} else {
			SetTentative.addTentativeToBuffer(futureHistory.getTentative());
			id = futureHistory.getTentative().getId();
		}
		historyFuture = RedoUndoUpdate.updateAdd(id);
		
		InternalStorage.pushFuture(historyFuture);
	}
	
	private static void redoClear(LinkedList<Assignment> buffer) {
		
		FutureHistory historyFuture = new FutureHistory();
		
		historyFuture = RedoUndoUpdate.updateClear(buffer);
		
		buffer.addAll(InternalStorage.getBuffer());
		Sort.insertionSortDeadline(buffer);
		InternalStorage.setBuffer(buffer);
		
		InternalStorage.pushFuture(historyFuture);
	}
	
	private static void redoConfirm(int position, Tentative tentative) {
		
		FutureHistory historyFuture = new FutureHistory();
		Appointment appointment = ((Appointment) InternalStorage.getBuffer().get(position));
		historyFuture = RedoUndoUpdate.updateConfirmBack(appointment, tentative.getId());
		
		InternalStorage.getBuffer().remove(position);
		SetTentative.addTentativeToBuffer(tentative);
		
		InternalStorage.pushFuture(historyFuture);
	}
}
