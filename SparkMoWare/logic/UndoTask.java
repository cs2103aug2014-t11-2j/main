package logic;

import java.util.LinkedList;
import logic.Assignment.AssignmentType;
import parser.EnumGroup.CommandType;

//@author A0111572R

public class UndoTask {
	
	private static int position;
	
	public static void undo() {
		
		FutureHistory futureHistory = InternalStorage.popHistory();
		
		if(futureHistory.getCommand().equals(CommandType.ADD)) {
			undoAdd(futureHistory.getSerial());
			
		} else if(futureHistory.getCommand().equals(CommandType.DONE)) {
			position = futureHistory.getPosition();
			undoDone(futureHistory.getSerial(), position);
			
		} else if(futureHistory.getCommand().equals(CommandType.EDIT)) {
			undoEdit(futureHistory);

		} else if(futureHistory.getCommand().equals(CommandType.DELETE)) {
			undoDelete(futureHistory);
			
		} else if(futureHistory.getCommand().equals(CommandType.CLEAR)) {
			LinkedList<Assignment> buffer = new LinkedList<Assignment>(); 
			buffer = futureHistory.getClearedHistory();
			undoClear(buffer);
			
		} else if(futureHistory.getCommand().equals(CommandType.TENTATIVE)) {
			undoTentative(futureHistory.getSerial());
		}else {
			position = InternalStorage.getBufferPosition(futureHistory.getSerial());
			undoConfirm(position, futureHistory.getTentative());
		}
	}
	
	private static void undoAdd(int id) {
	
		FutureHistory historyFuture = new FutureHistory();
		position = InternalStorage.getBufferPosition(id);
		
		historyFuture = RedoUndoUpdate.updateDelete(position);
		InternalStorage.pushFuture(historyFuture);
		Delete.delete(id);
	}
	
	private static void undoTentative(int id) {
		
		FutureHistory historyFuture = new FutureHistory();
		position = InternalStorage.getBufferPosition(id);
		
		historyFuture = RedoUndoUpdate.updateDeleteTentative(position);
		InternalStorage.pushFuture(historyFuture);
		Delete.delete(id);
	}
	
	private static void undoDone(int id, int position) {
		
		FutureHistory historyFuture = new FutureHistory();
		int newPosition = InternalStorage.getBufferPosition(id);
		
		InternalStorage.getBuffer().get(newPosition).setIsDone(false);
		InternalStorage.addBuffer(position, InternalStorage.getBuffer().remove(newPosition));
		historyFuture = RedoUndoUpdate.updateDone(id, 0);
		
		InternalStorage.pushFuture(historyFuture);
	}
	
	private static void undoEdit(FutureHistory futureHistory) {
		
		FutureHistory historyFuture = new FutureHistory();
		position = InternalStorage.getBufferPosition(futureHistory.getSerial());
		
		historyFuture = undoEdit2(futureHistory);
		InternalStorage.pushFuture(historyFuture);
	}
	
	private static FutureHistory undoEdit2(FutureHistory futureHistory) {
		
		FutureHistory historyFuture = new FutureHistory();
		
		if(futureHistory.getAssignType().equals(AssignmentType.ASGN)) {
			Assignment assignment = InternalStorage.getBuffer().get(position);
			InternalStorage.getBuffer().remove(position);
			Add.addAssignmentToBuffer(futureHistory.getAssignment());
			historyFuture = RedoUndoUpdate.updateEditOver(assignment);
			
		} else if(futureHistory.getAssignType().equals(AssignmentType.APPT)) {
			Appointment appointment = (Appointment) InternalStorage.getBuffer().get(position);
			InternalStorage.getBuffer().remove(position);
			Add.addAppointmentToBuffer(futureHistory.getAppointment());
			historyFuture = RedoUndoUpdate.updateEditOver(appointment);
			
		} else if(futureHistory.getAssignType().equals(AssignmentType.TASK)) {
			Task task = (Task) InternalStorage.getBuffer().get(position);
			InternalStorage.getBuffer().remove(position);
			Add.addTaskToBuffer(futureHistory.getTask());
			historyFuture = RedoUndoUpdate.updateEditOver(task);
			
		} else {
			Tentative tentative = (Tentative) InternalStorage.getBuffer().get(position);
			InternalStorage.getBuffer().remove(position);
			SetTentative.addTentativeToBuffer(futureHistory.getTentative());
			historyFuture = RedoUndoUpdate.updateEditOver(tentative);
		}
		return historyFuture;
	}
	
	private static void undoDelete(FutureHistory futureHistory) {
		
		FutureHistory historyFuture = new FutureHistory();
		position = InternalStorage.getBufferPosition(futureHistory.getSerial());
		int id;
		
		id = undoDelete2(futureHistory);
		
		historyFuture = RedoUndoUpdate.updateAdd(id);
		
		InternalStorage.pushFuture(historyFuture);
	}
	
	private static int undoDelete2(FutureHistory futureHistory) {
		
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
	private static void undoClear(LinkedList<Assignment> buffer) {
		
		FutureHistory historyFuture = new FutureHistory();
		
		historyFuture = RedoUndoUpdate.updateAddBack();
		InternalStorage.getBuffer().addAll(buffer);
		
		buffer = Sort.insertionSortDeadline(InternalStorage.getBuffer());
		InternalStorage.setBuffer(buffer);
		
		InternalStorage.pushFuture(historyFuture);
	}
	
	private static void undoConfirm(int position, Tentative tentative) {
		
		FutureHistory historyFuture = new FutureHistory();
		Appointment appointment = ((Appointment) InternalStorage.getBuffer().get(position));
		historyFuture = RedoUndoUpdate.updateConfirmBack(appointment, tentative.getIndex());
		
		InternalStorage.getBuffer().remove(position);
		SetTentative.addTentativeToBuffer(tentative);
		
		InternalStorage.pushFuture(historyFuture);
	}
}