//Class that contains all the enums

package parser;

public class EnumGroup {

	public enum AssignmentType {
		TASK, APPOINTMENT, TENTATIVE, DEFAULT
	}
	
	public enum CommandType {
		ADD, EDIT, DELETE, TENTATIVE, CONFIRM, SORT, SEARCH, FILTER,
		CLEAR, UNDO, REDO, STATISTIC, EXIT, INVALID, DISPLAY, HELP,
		DONE, DEFAULT, INVALID_FORMAT, EDITED
		//unlikely to use invalid or exit
	}
	
	public enum EditType {
		TITLE, START_DATE, START_TIME, END_DATE, END_TIME, INVALID, PRIORITY, DONE
		//unlikely to use invalid or done
	}
}
