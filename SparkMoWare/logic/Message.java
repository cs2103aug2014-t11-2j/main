package logic;

/*
 * One class to store all the printing messages
 */
public class Message {
	
	protected static final String WELCOME = "Welcome to SparkMoWare!";
	protected static final String PROMPT = "command: ";
	protected static final String ADDED = "added to %1$s: \"%2$s\"";
	protected static final String EDITED = "content has already been edited";
	protected static final String TENTATIVE = "Schedule(s): ";
	protected static final String TENTATIVE_ADDED = "All of your tentative appointments have been added";
	
	protected static final String DELETED = "deleted from %1$s: \"%2$s\"";
	protected static final String DELETE_ON = "all content(s) of date %1$s is(are) deleted";
	protected static final String DELETE_BEFORE = "all content(s) before date %1$s is(are) deleted";
	protected static final String DELETE_BETWEEN = "all content(s) from date %1$s to date %2$s is(are) deleted";
	protected static final String DELETE = "all content deleted from %1$s";
	
	protected static final String DOES_NOT_EXISTS = "%1$s does not exists";
	protected static final String NO_TITLE = "Title is blank";
	protected static final String NOTHING_COMPLETED = "Nothing has been completed";
	protected static final String FORMAT_PROMPT = "Please type the %1$s again: ";
	protected static final String EMPTY = "%1$s is empty";
	
	protected static final String REDO = "Last action has been implemented";
	protected static final String UNDO = "Last action has been reverted";
	
	protected static final String UNABLE_TO_REDO = "Error: There are no actions to redo";
	protected static final String UNABLE_TO_UNDO = "Error: There are no actions to undo";
	protected static final String INVALID_COMMAND = "Invalid Commad Issued";

	protected static final String INVALID_FORMAT = "Invalid Format";	
	protected static final String INVALID_SEARCH_PARAMETER = "Invalid search parameter entered";

	public static final String STORAGE_FILE_ERROR = "Exception encountered while initalising the Storage file";
	protected static final String SAVE_FILE_ERROR = "Exception encountered while saving the textfile";
}
