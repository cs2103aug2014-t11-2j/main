/* This java file will take in the user input from GUI/Storage.
 * This is the location for receiving  information from the GUI/Storage, calls the Logic to execute and
 * finally returns information to the GUI/Storage
 */

package parser;

import java.util.regex.Pattern;

public class CommandHandler {

	//This is the method that will be called by GUI/Storage
	public void readCommand(String userInput) {
			
		switch (determineCommandType(userInput)) {
		case ADD:
			InputIsAdd.executeCommand(userInput);
			break;
			
		case EDIT:
			InputIsEdit.executeCommand(userInput);
			break;

		case DELETE:

			break;

		case TENTATIVE:
			
			break;

		case CONFIRM:
			
			break;

		case CLEAR:
			break;

		case SORT:
			
			break;

		case SEARCH:
			
			break;

		case STATISTIC:
			
			break;

		case UNDO:
			
			break;

		case REDO:

			break;

		case DISPLAY:
			
			break;
		
		case FILTER:
			
			break;
		
		case EXIT:
			
			break;

		case HELP:
			

		default:
		}
	}

	
	private CommandType determineCommandType(String userInput) {
		
		if(isCommand(ParserPatternLocal.addPattern, userInput)) {
			return CommandType.ADD;
		} else if(isCommand(ParserPatternLocal.editPattern, userInput)) {
			return CommandType.EDIT;
		} else if(isCommand(ParserPatternLocal.deletePattern, userInput)) {
			return CommandType.DELETE;
		} else if(isCommand(ParserPatternLocal.tentativePattern, userInput)) {
			return CommandType.TENTATIVE;
		} else if(isCommand(ParserPatternLocal.confirmPattern, userInput)) {
			return CommandType.CONFIRM;
		} else if(isCommand(ParserPatternLocal.sortPattern, userInput)) {
			return CommandType.SORT;
		} else if(isCommand(ParserPatternLocal.searchPattern, userInput)) {
			return CommandType.SEARCH;
		} else if(isCommand(ParserPatternLocal.filterPattern, userInput)) {
			return CommandType.FILTER;
		} else if(isCommand(ParserPatternLocal.clearPattern, userInput)) {
			return CommandType.CLEAR;
		} else if(isCommand(ParserPatternLocal.undoPattern, userInput)) {
			return CommandType.UNDO;
		} else if(isCommand(ParserPatternLocal.redoPattern, userInput)) {
			return CommandType.REDO;
		} else if(isCommand(ParserPatternLocal.statisticPattern, userInput)) {
			return CommandType.STATISTIC;
		} else if(isCommand(ParserPatternLocal.exitPattern, userInput)) {
			return CommandType.EXIT;
		} else if(isCommand(ParserPatternLocal.displayPattern, userInput)) {
			return CommandType.DISPLAY;
		} else if(isCommand(ParserPatternLocal.helpPattern, userInput)) {
			return CommandType.HELP;
		} else if(isCommand(ParserPatternLocal.easterEggPattern, userInput)) {
			return CommandType.EASTER_EGG;
		} else if(isCommand(ParserPatternLocal.finishPattern, userInput)) {
			return CommandType.FINISH;
		} else{
		return CommandType.INVALID;
		}
	}
	
	//Method determines of user input contains the pattern of the command, for eg. add
	private boolean isCommand(Pattern targetPattern, String userInput) {
		return  targetPattern.matcher(userInput).find();
	}
}
