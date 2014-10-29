package logic;

import java.util.LinkedList;
import logic.Assignment.AssignmentType;
import parser.EnumGroup.CommandType;

public class UndoTask {
	
	public static void undo() {
		
		FutureHistory futureHistory = InternalStorage.popHistory();
		int position;

		if(futureHistory.getCommand().equals(CommandType.ADD) ||
				futureHistory.getCommand().equals(CommandType.TENTATIVE)) {
			futureHistory.setCommand(CommandType.DELETE);
			Delete.delete(futureHistory.getSerial());
			
		} else if(futureHistory.getCommand().equals(CommandType.DONE)) {
			position = InternalStorage.getBufferPosition(futureHistory.getSerial());
			InternalStorage.getBuffer().get(position).setIsDone(false);
			
		} else if(futureHistory.getCommand().equals(CommandType.EDIT)) {
			position = InternalStorage.getBufferPosition(futureHistory.getSerial());

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

		} else if(futureHistory.getCommand().equals(CommandType.DELETE)) {
			position = InternalStorage.getBufferPosition(futureHistory.getSerial());

			if(futureHistory.getAssignType().equals(AssignmentType.ASSIGNMENT)) {
				
				Add.addAssignmentToBuffer(futureHistory.getAssignment());				
			} else if(futureHistory.getAssignType().equals(AssignmentType.APPOINTMENT)) {
				
				Add.addAppointmentToBuffer(futureHistory.getAppointment());
			} else if(futureHistory.getAssignType().equals(AssignmentType.TASK)) {
				
				Add.addTaskToBuffer(futureHistory.getTask());
			} else {
				
				SetTentative.addTentativeToBuffer(futureHistory.getTentative());
			}
		} else if(futureHistory.getCommand().equals(CommandType.CLEAR)) {
			LinkedList<Assignment> buffer = futureHistory.getClearedHistory();
			buffer.addAll(InternalStorage.getBuffer());
			
			Sort.insertionSortDeadline(buffer);
			InternalStorage.setBuffer(buffer);
		} else {
			position = InternalStorage.getBufferPosition(futureHistory.getSerial());
			InternalStorage.getBuffer().remove(position);
			SetTentative.addTentativeToBuffer(futureHistory.getTentative());
		}
		
		InternalStorage.pushFuture(futureHistory);
	}
}
