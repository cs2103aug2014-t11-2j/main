package parser;

public class ParserCommandLocal {
	
	protected static EnumGroup.CommandType getCommandType(String command) {

		if (command.equalsIgnoreCase("add")) {
			return EnumGroup.CommandType.ADD;
		} else if(command.equalsIgnoreCase("tentative")) {
			return EnumGroup.CommandType.TENTATIVE;
		} else if (command.equalsIgnoreCase("confirm")) {
			return EnumGroup.CommandType.CONFIRM;
		} else if (command.equalsIgnoreCase("delete")) {
			return EnumGroup.CommandType.DELETE;
		} else if (command.equalsIgnoreCase("search")) {
			return EnumGroup.CommandType.SEARCH;
		} else if (command.equalsIgnoreCase("edit")) {
			return EnumGroup.CommandType.EDIT;
		} else if (command.equalsIgnoreCase("clear")) {
			return EnumGroup.CommandType.CLEAR;
		} else if (command.equalsIgnoreCase("sort")) {
			return EnumGroup.CommandType.SORT;
		} else if (command.equalsIgnoreCase("statistic")) {
			return EnumGroup.CommandType.STATISTIC;
		} else if (command.equalsIgnoreCase("undo")) {
			return EnumGroup.CommandType.UNDO;
		} else if (command.equalsIgnoreCase("redo")) {
			return EnumGroup.CommandType.REDO;
		} else if (command.equalsIgnoreCase("help")) {
			return EnumGroup.CommandType.HELP;
		} else if (command.equalsIgnoreCase("exit")) {
			return EnumGroup.CommandType.EXIT;
		} else if (command.equalsIgnoreCase("display")){
			return EnumGroup.CommandType.DISPLAY;
		} else {
			return EnumGroup.CommandType.INVALID;
		}		
	}
}