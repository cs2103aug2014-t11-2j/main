//Class that contains all the enums

package parser;

//@author A0110788B
public class EnumGroup {

	/**
	 * The enums of the supported commands.
	 */
	public enum CommandType {
		ADD, EDIT, DELETE, TENTATIVE, CONFIRM, SORT, SEARCH, FILTER,
		CLEAR, UNDO, REDO, STATISTIC, EXIT, INVALID, DISPLAY, HELP,
		DONE, DEFAULT, INVALID_FORMAT, EDITED
	}
	
	/**
	 * The enums of what can be edited.
	 */
	public enum EditType {
		TITLE, START_DATE, START_TIME, END_DATE, END_TIME, INVALID, PRIORITY, DONE
	}
}