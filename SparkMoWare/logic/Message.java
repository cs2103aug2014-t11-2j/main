package logic;

/**
 * Logic: Message component to store all the necessary messages.
 * @author Varunica
 */

public class Message {
	
	public static final String WELCOME = "Welcome to SparkMoWare!";
	protected static final String PROMPT = "command: ";
	protected static final String ADDED = "You have added one more job! You may carry on:)";
	protected static final String EDITED = "Content has already been edited! Are you sure?";
	
	protected static final String TENTATIVE_ADDED = "Why you can't confirm the appointment yet?";	
	protected static final String TENTATIVE_CONFIRM = "Your appointment has finally been approved!";
	
	protected static final String DELETED = "Another job is gone! I assumed you have done a good job?";
	protected static final String DELETE_ALL = "Now they can't trace back what you have did!";
	
	protected static final String SEARCH = "Is this what you really want?";
	protected static final String SORT = "This is so much organized:)";
	
	protected static final String STATISTIC = "Let me see how well are you doing recently!";
	protected static final String DISPLAY = "Original list is up!";
	
	protected static final String FILTER = "Why do you not want to see the rest?!";
	protected static final String DONE = "Good job completing a job!";
	
	protected static final String INVALID_COMMAND = "Why you no follow the format?!";
	
	protected static final String REDO = "Last action has been implemented";
	protected static final String UNDO = "Last action has been reverted";
	
	protected static final String UNABLE_TO_REDO = "Stop typing redo! There's nothing for you inside already!";
	protected static final String UNABLE_TO_UNDO = "Stop typing undo! This is what you want from the start!";
	
	protected static final String INVALID_SEARCH_PARAMETER = "Sorry, what you want is not found!";
}
