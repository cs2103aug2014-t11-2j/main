//Class that contains all the enums

package parser;

/**
 * 
 * @author Matthew Song
 *
 */
public class EnumGroup {

	/**
	 * The enums of the supported commands.
	 * @author Matthew Song
	 */
	public enum CommandType {
		ADD, EDIT, DELETE, TENTATIVE, CONFIRM, SORT, SEARCH, FILTER,
		CLEAR, UNDO, REDO, STATISTIC, EXIT, INVALID, DISPLAY, HELP,
		DONE, DEFAULT, INVALID_FORMAT, EDITED
	}
	
	/**
	 * The enums of what can be edited.
	 * @author Matthew Song
	 *
	 */
	public enum EditType {
		TITLE, START_DATE, START_TIME, END_DATE, END_TIME, INVALID, PRIORITY, DONE
	}
}