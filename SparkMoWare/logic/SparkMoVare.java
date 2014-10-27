package logic;

import java.util.LinkedList;
import parser.EnumGroup.CommandType;
import parser.Interpreter;
import parser.RefinedUserInput;
import storage.Storage;

public class SparkMoVare {

	protected static final int SYSTEM_EXIT_NO_ERROR = 0;
	protected static final int SYSTEM_EXIT_ERROR = -1;

	protected static final boolean IS_NOT_STATS_OR_INVALID = false;

	/* each index of refinedUserinput represent something
	 * 0:The command string
	 * 1:Serial Number (S/N) of the Assignment ASSUMPTION: serial number length is at most 12 digits DD/MM/YYYY/0000
	 * 2:Title of the Assignment
	 * 3:Start Date
	 * 4:Start Time
	 * 5:End Date
	 * 6:End Time
	 * 7:Type: Task(0), Appointment(1) and Tentative(2)
	 * 8:For command types: delete all (on, before, during)
	 *					   edit which is to be edited, such as title or start date
	 *					   tentative (number of days)
	 *					   sort and search by date, serial number, etc.
	 * 9:Priority 
	 */


	//Fundamentally the same as CommandType, but without single word commands 

	public static void main(String[] args) {

		Print.printToUser(Message.WELCOME);
		Storage.openFile(InternalStorage.getFilePath(), Id.getLatestSerialNumber(), InternalStorage.getBuffer());
		toDoManager();
	}

	public static void toDoManager() {

		Output returnOutput = new Output();

		while (true) {
			Print.printToUser(Message.PROMPT);

			returnOutput = executeCommand(InternalStorage.getScanner().nextLine());
			/*	
			if (getCommandType(refinedUserInput[0]) != CommandType.UNDO &&
					getCommandType(refinedUserInput[0]) != CommandType.REDO &&
					getCommandType(refinedUserInput[0]) != CommandType.INVALID &&
					getCommandType(refinedUserInput[0]) != CommandType.DISPLAY) {
				InternalStorage.pushHistory(InternalStorage.getBuffer());
			 */
			//		}
			Print.printList(returnOutput.getReturnBuffer());
			Print.printToUser(returnOutput.getFeedback());
			System.out.println(returnOutput.getTotalAssignment());
			System.out.println(returnOutput.getTotalCompleted());
			System.out.println(returnOutput.getTotalOnTime());
		}		
	} 

	public static Output executeCommand(String userStringInput) {

		Output returnOutput = new Output();

		RefinedUserInput userInput = new RefinedUserInput();
		userInput = Interpreter.reader(userStringInput);

		CommandType command = userInput.getCommandType();
		/*
		if (command != CommandType.UNDO && command != CommandType.REDO ) {
			while (!InternalStorage.getFuture().empty()){
				InternalStorage.popFuture();
			}
		}
		 */		
		if(!command.equals(CommandType.TENTATIVE) && 
			!InternalStorage.getNewTentative().getStartDate().isEmpty()) {
			InternalStorage.addBuffer(InternalStorage.getNewTentative());
			
		 } else {
			 Tentative newTentative = new Tentative();
			InternalStorage.setNewTentative(newTentative); 
		 }
		
		 switch (command) {
		 case ADD:
			 Add.addSomething(userInput);

			 returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					 Message.ADDED, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					 Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			 // assertTrue(InternalStorage.getBufferPosition(userInput.getId()) > -1);
			 return returnOutput;

		 case EDIT:
			 Edit.editAssignment(userInput);

			 returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					 Message.EDITED, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					 Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			 return returnOutput;

		 case DELETE:
			 Delete.delete(userInput.getId());

			 returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					 Message.DELETED, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					 Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			 // assertTrue(InternalStorage.getBufferPosition(userInput.getId()) > -1);
			 return returnOutput;
/*
		 case TENTATIVE:

			 if(userInput.getIsNewTentative()) {
				 Tentative newTentative = SetTentative.addTentative(userInput.getTitle());
				 InternalStorage.setNewTentative(newTentative);

			 } else {
				 SetTentative.addTentativeAppt(InternalStorage.getNewTentative(), userInput.getStartDate(), 
						 userInput.getStartTime(), userInput.getEndDate(), userInput.getEndTime());
			 }

			 returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					 Message.TENTATIVE_ADDED, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					 Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			 return returnOutput;
*/
		 case CONFIRM:
			 ConfirmTentative.confirmTentative(userInput.getId(), userInput.getStartDate(),
					 userInput.getStartTime(), userInput.getEndDate(), userInput.getEndTime());

			 returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					 Message.TENTATIVE_CONFIRM, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					 Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			 return returnOutput;

		 case CLEAR:
			 Delete.deleteAll(userInput.getSpecialContent(), userInput.getStartDate(), userInput.getEndDate());

			 returnOutput = ModifyOutput.returnModification(InternalStorage.getBuffer(),
					 Message.DELETE_ALL, InternalStorage.getLineCount(), Statistic.getCompleted(), 
					 Statistic.getIsOnTime(), IS_NOT_STATS_OR_INVALID, IS_NOT_STATS_OR_INVALID);

			 // assertFalse(InternalStorage.getLineCount() > 0);
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
			 Edit.completeAssignment(userInput.getId());

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
				 RedoUndo.undo();

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
				 RedoUndo.redo();

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