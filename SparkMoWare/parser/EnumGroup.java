//Class that contains all the enums

package parser;

public class EnumGroup {

	protected enum AssignmentType {
		TASK, APPOINTMENT, TENTATIVE
	}
	
	protected enum CommandType {
		ADD, EDIT, DELETE, TENTATIVE, CONFIRM, SORT, SEARCH, FILTER,
		CLEAR, UNDO, REDO, STATISTIC, EXIT, INVALID, DISPLAY, HELP,
		DONE
		//unlikely to use invalid or exit
	}
	
	protected enum EditType {
		TITLE, START_DATE, START_TIME, END_DATE, END_TIME, INVALID, PRIORITY, DONE
		//unlikely to use invalid or done
	}
}
