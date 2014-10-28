package parser;

import java.util.regex.Matcher;

public class Determine {
	
	//requires change to use pattern
	protected static EnumGroup.CommandType getCommandType(String command) {
		Matcher addMatcher = ParserPatternLocal.addPattern.matcher(command);
		Matcher tentativeMatcher = ParserPatternLocal.tentativePattern.matcher(command);
		Matcher confirmMatcher = ParserPatternLocal.confirmPattern.matcher(command);
		Matcher deleteMatcher = ParserPatternLocal.deletePattern.matcher(command);
		Matcher searchMatcher = ParserPatternLocal.searchPattern.matcher(command);
		Matcher editMatcher = ParserPatternLocal.editPattern.matcher(command);
		Matcher clearMatcher = ParserPatternLocal.clearPattern.matcher(command);
		Matcher sortMatcher = ParserPatternLocal.sortPattern.matcher(command);
		Matcher statisticMatcher = ParserPatternLocal.statisticPattern.matcher(command);
		Matcher undoMatcher = ParserPatternLocal.undoPattern.matcher(command);
		Matcher redoMatcher = ParserPatternLocal.redoPattern.matcher(command);
		Matcher helpMatcher = ParserPatternLocal.helpPattern.matcher(command);
		Matcher exitMatcher = ParserPatternLocal.exitPattern.matcher(command);
		Matcher displayMatcher = ParserPatternLocal.displayPattern.matcher(command);
		
		
		if (addMatcher.find()) {
			return EnumGroup.CommandType.ADD;
		} else if(tentativeMatcher.find()) {
			return EnumGroup.CommandType.TENTATIVE;
		} else if (confirmMatcher.find()) {
			return EnumGroup.CommandType.CONFIRM;
		} else if (deleteMatcher.find()) {
			return EnumGroup.CommandType.DELETE;
		} else if (searchMatcher.find()) {
			return EnumGroup.CommandType.SEARCH;
		} else if (editMatcher.find()) {
			return EnumGroup.CommandType.EDIT;
		} else if (clearMatcher.find()) {
			return EnumGroup.CommandType.CLEAR;
		} else if (sortMatcher.find()) {
			return EnumGroup.CommandType.SORT;
		} else if (statisticMatcher.find()) {
			return EnumGroup.CommandType.STATISTIC;
		} else if (undoMatcher.find()) {
			return EnumGroup.CommandType.UNDO;
		} else if (redoMatcher.find()) {
			return EnumGroup.CommandType.REDO;
		} else if (helpMatcher.find()) {
			return EnumGroup.CommandType.HELP;
		} else if (exitMatcher.find()) {
			return EnumGroup.CommandType.EXIT;
		} else if (displayMatcher.find()){
			return EnumGroup.CommandType.DISPLAY;
		} else {
			return EnumGroup.CommandType.INVALID;
		}		
	}
	
	//requires change to use pattern
	protected static EnumGroup.EditType getEditType(String input) {
		if(input.equalsIgnoreCase("title")) {
			return EnumGroup.EditType.TITLE;
		} else if(input.equalsIgnoreCase("START_DATE")) {
			return EnumGroup.EditType.START_DATE;
		} else if(input.equalsIgnoreCase("START_TIME")) {
			return EnumGroup.EditType.START_TIME;
		} else if(input.equalsIgnoreCase("END_DATE")) {
			return EnumGroup.EditType.END_DATE;
		} else if(input.equalsIgnoreCase("END_TIME")) {
			return EnumGroup.EditType.END_TIME;
		} else if(input.equalsIgnoreCase("PRIORITY")) {
			return EnumGroup.EditType.PRIORITY;
		} else if(input.equalsIgnoreCase("DONE")) {
			return EnumGroup.EditType.DONE;
		} else {
			return EnumGroup.EditType.INVALID;
		}
	}
}