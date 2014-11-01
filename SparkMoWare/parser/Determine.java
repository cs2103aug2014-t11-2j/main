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
		Matcher finishMatcher = ParserPatternLocal.finishPattern.matcher(command);
		
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
		} else if (displayMatcher.find()) {
			return EnumGroup.CommandType.DISPLAY;
		} else if (finishMatcher.find()) {
			return EnumGroup.CommandType.DONE;
		} else {
			return EnumGroup.CommandType.INVALID;
		}		
	}
	
	//requires change to use pattern
	protected static EnumGroup.EditType getEditType(String input) {
		Matcher titleMatcher = ParserPatternLocal.titlePattern.matcher(input);
		Matcher startDateMatcher = ParserPatternLocal.startDatePattern.matcher(input);
		Matcher startTimeMatcher = ParserPatternLocal.startTimePattern.matcher(input);
		Matcher endDateMatcher = ParserPatternLocal.endDatePattern.matcher(input);
		Matcher endTimeMatcher = ParserPatternLocal.endTimePattern.matcher(input);
		Matcher priorityMatcher = ParserPatternLocal.priorityPattern.matcher(input);
		
		if(titleMatcher.find()) {
			return EnumGroup.EditType.TITLE;
		} else if(startDateMatcher.find()) {
			return EnumGroup.EditType.START_DATE;
		} else if(startTimeMatcher.find()) {
			return EnumGroup.EditType.START_TIME;
		} else if(endDateMatcher.find()) {
			return EnumGroup.EditType.END_DATE;
		} else if(endTimeMatcher.find()) {
			return EnumGroup.EditType.END_TIME;
		} else if(priorityMatcher.find()) {
			return EnumGroup.EditType.PRIORITY;
		} else {
			return EnumGroup.EditType.INVALID;
		}
	}
}