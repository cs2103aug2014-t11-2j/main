package logic;

import java.util.LinkedList;
import logic.Assignment.AssignmentType;
import parser.EnumGroup.CommandType;
import parser.Interpreter;
import parser.RefinedUserInput;
import storage.Storage;

public class SparkMoVare {

	protected static final int SYSTEM_EXIT_NO_ERROR = 0;
	protected static final int SYSTEM_EXIT_ERROR = -1;

	protected static final boolean IS_NOT_STATS_OR_INVALID = false;

	public static void main(String[] args) {

		Print.printToUser(Message.WELCOME);
		Storage.openFile(InternalStorage.getFilePath(),Id.getLatestSerialNumber(), InternalStorage.getBuffer());
		toDoManager();
	}

	public static void toDoManager() {

		Output returnOutput = new Output();

		while (true) {
			Print.printToUser(Message.PROMPT);

			returnOutput = executeCommand(InternalStorage.getScanner().nextLine());

			Print.printList(returnOutput.getReturnBuffer());
			Print.printToUser(returnOutput.getFeedback());
			System.out.println(returnOutput.getTotalAssignment());
			System.out.println(returnOutput.getTotalCompleted());
			System.out.println(returnOutput.getTotalOnTime());
		}		
	} 

	public static Output executeCommand(String userStringInput) {

		FutureHistory futureHistory = new FutureHistory();
		Output returnOutput = new Output();
		RefinedUserInput userInput = new RefinedUserInput();
		String id;
		int position;
		
		userInput = Interpreter.reader(userStringInput);

		CommandType command = userInput.getCommandType();

		if (command != CommandType.UNDO && command != CommandType.REDO ) {
			while (!InternalStorage.getFuture().empty()){
				InternalStorage.popFuture();
			}
		}		
		switch (command) {
		case ADD:
			id = Add.addSomething(userInput);
			futureHistory.setCommand(CommandType.ADD);
			futureHistory.setSerial(id);

			InternalStorage.pushHistory(futureHistory);

			returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					Message.ADDED, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			return returnOutput;

		case EDIT:
			position = InternalStorage.getBufferPosition(userInput.getId());
			
			futureHistory.setSerial(userInput.getId());
			
			if(InternalStorage.getBuffer().get(position).getAssignType().equals(AssignmentType.ASSIGNMENT)) {
				futureHistory.setAssignment(InternalStorage.getBuffer().get(position));
				futureHistory.setAssignType(AssignmentType.ASSIGNMENT);
				
			} else if(InternalStorage.getBuffer().get(position).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				Appointment appointment = (Appointment) InternalStorage.getBuffer().get(position);
				futureHistory.setAppointment(appointment);
				futureHistory.setAssignType(AssignmentType.APPOINTMENT);
				
			} else if(InternalStorage.getBuffer().get(position).getAssignType().equals(AssignmentType.TASK)) {
				Task task = (Task) InternalStorage.getBuffer().get(position);
				futureHistory.setTask(task);
				futureHistory.setAssignType(AssignmentType.TASK);
				
			} else {
				Tentative tentative = (Tentative) InternalStorage.getBuffer().get(position);
				futureHistory.setTentative(tentative);
				futureHistory.setAssignType(AssignmentType.TENTATIVE);
			}
			futureHistory.setCommand(CommandType.EDIT);
			
			InternalStorage.pushHistory(futureHistory);
			
			Edit.editAssignment(userInput);

			returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					Message.EDITED, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			return returnOutput;

		case DELETE:
			position = InternalStorage.getBufferPosition(userInput.getId());

			if(InternalStorage.getBuffer().get(position).getAssignType().equals(AssignmentType.ASSIGNMENT)) {
				futureHistory.setAssignment(InternalStorage.getBuffer().get(position));

			} else if(InternalStorage.getBuffer().get(position).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				Appointment appointment = (Appointment) InternalStorage.getBuffer().get(position);
				futureHistory.setAppointment(appointment);

			} else if(InternalStorage.getBuffer().get(position).getAssignType().equals(AssignmentType.TASK)) {
				Task task = (Task) InternalStorage.getBuffer().get(position);
				futureHistory.setTask(task);

			} else {
				Tentative tentative = (Tentative) InternalStorage.getBuffer().get(position);
				futureHistory.setTentative(tentative);
			}
			futureHistory.setCommand(CommandType.DELETE);
			
			InternalStorage.pushHistory(futureHistory);
			
			Delete.delete(userInput.getId());

			returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					Message.DELETED, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			return returnOutput;

		case TENTATIVE:
			id = SetTentative.addTentative(userInput.getTitle(), userInput.getTentativeDates(), userInput.getTentativeTimes());
			futureHistory.setCommand(CommandType.TENTATIVE);
			futureHistory.setSerial(id);

			InternalStorage.pushHistory(futureHistory);

			returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					Message.TENTATIVE_ADDED, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			return returnOutput;

		case CONFIRM:
			position = InternalStorage.getBufferPosition(userInput.getId());
			futureHistory.setTentative((Tentative) InternalStorage.getBuffer().get(position));
			futureHistory.setCommand(CommandType.CONFIRM);
			
			id = ConfirmTentative.confirmTentative(userInput.getId(), userInput.getStartDate(),
					userInput.getStartTime(), userInput.getEndDate(), userInput.getEndTime());
			
			futureHistory.setSerial(id);
			
			InternalStorage.pushHistory(futureHistory);
			
			returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					Message.TENTATIVE_CONFIRM, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			return returnOutput;

		case CLEAR:
			LinkedList<Assignment> deleted = new LinkedList<Assignment>();
			
			deleted = Delete.deleteAll(userInput.getSpecialContent(), userInput.getStartDate(), userInput.getEndDate());
			
			futureHistory.setCommand(CommandType.CLEAR);
			futureHistory.addClearedHistory(deleted);
			
			InternalStorage.pushHistory(futureHistory);
			
			returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					Message.DELETE_ALL, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			return returnOutput;

		case SORT:
			LinkedList<Assignment> sortedBuffer = new LinkedList<Assignment>();

			sortedBuffer = Sort.multipleSortRequired(InternalStorage.getBuffer(), userInput.getSpecialContent(),
					userInput.getStartDate(), userInput.getEndDate());

			returnOutput = ModifyOutput.returnModification(sortedBuffer,
					Message.SORT, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			return returnOutput;

		case SEARCH:
			LinkedList<Assignment> searchBuffer = new LinkedList<Assignment>();

			searchBuffer = SearchAll.searchAll(InternalStorage.getBuffer(), userInput.getSpecialContent());

			if(searchBuffer.size() == 0) {
				returnOutput = ModifyOutput.returnModification(searchBuffer,
						Message.INVALID_SEARCH_PARAMETER, InternalStorage.getLineCount(), Statistic.getCompleted(), 
						Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);
			} else {
				returnOutput = ModifyOutput.returnModification(searchBuffer,
						Message.SEARCH, InternalStorage.getLineCount(), Statistic.getCompleted(), 
						Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);
			}
			return returnOutput;

		case DONE:
			id = userInput.getId();

			Edit.completeAssignment(id);

			futureHistory.setCommand(CommandType.DONE);
			futureHistory.setSerial(id);

			InternalStorage.pushHistory(futureHistory);

			returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					Message.DONE, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			return returnOutput;

		case STATISTIC:
			returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					Message.STATISTIC, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					Statistic.getIsOnTime(), true, IS_NOT_STATS_OR_INVALID);

			return returnOutput;

		case UNDO:

			if(InternalStorage.getHistory().isEmpty()) {
				returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
						Message.UNABLE_TO_UNDO, InternalStorage.getLineCount(), Statistic.getCompleted(), 
						Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);
			} else {
				UndoTask.undo();

				returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
						Message.UNDO, InternalStorage.getLineCount(), Statistic.getCompleted(), 
						Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);
			}
			return returnOutput;

		case REDO:

			if(InternalStorage.getFuture().isEmpty()) {
				returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
						Message.UNABLE_TO_REDO, InternalStorage.getLineCount(), Statistic.getCompleted(), 
						Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);
			} else {
				RedoTask.redo();

				returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
						Message.REDO, InternalStorage.getLineCount(), Statistic.getCompleted(), 
						Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);
			}
			return returnOutput;

		case DISPLAY:
			returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					Message.DISPLAY, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			Print.display();
			return returnOutput;

		case FILTER:
			LinkedList<Assignment> filteredBuffer = new LinkedList<Assignment>();

			filteredBuffer = Filter.filterMain(InternalStorage.getBuffer(), userInput.getSpecialContent(), 
					userInput.getStartDate(), userInput.getEndDate());

			returnOutput = ModifyOutput.returnModification(filteredBuffer,
					Message.FILTER, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			return returnOutput;

		case EXIT:
			System.exit(SYSTEM_EXIT_NO_ERROR);
			break;

		default:
			returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					Message.INVALID_COMMAND, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, true);

			return returnOutput;
		}
		System.out.println("File saved");
		Storage.saveFile(InternalStorage.getFilePath(), InternalStorage.getBuffer());

		return returnOutput;
	}
}