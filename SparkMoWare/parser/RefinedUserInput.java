package parser;

import java.util.Vector;

import logic.Assignment.AssignmentType;
import parser.EnumGroup.CommandType;

//@author A0110788B

/**
 * The RefinedUserInput object contains all relevant information that is interpreted by the Parser and organised accordingly.
 */
public class RefinedUserInput {
	
	/************** Data members **********************/
	
	private CommandType command;
	private int index;
	private String title;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private AssignmentType assignment;
	private String priority;
	private boolean isNewTentative;
	private String specialContent;
	/* Special content is for the following command cases:
	 * delete all (on, before, during)
	 * tentative (number of days)
	 * sort and search (date, serial number, etc.)
	 */
	private Vector<String> tentativeDates;
	private Vector<String> tentativeTimes;
	
	private static final String DEFAULT_CONTENT = "default";
	private static final String DEFAULT_PRIORITY = "NIMPT";
	private static final int DEFAULT_INDEX = 0;
	/************** Constructors **********************/
	
	/**
	 * Default Constructor for RefinedUserInput object. By default, all String content are "default",
	 * priority is not "NIMPT" and index is 0. 
	 */
	public RefinedUserInput() {
		
		setCommandType(EnumGroup.CommandType.DEFAULT);
		setIndex(DEFAULT_INDEX);
		setTitle(DEFAULT_CONTENT);
		setStartDate(DEFAULT_CONTENT);
		setStartTime(DEFAULT_CONTENT);
		setEndDate(DEFAULT_CONTENT);
		setEndTime(DEFAULT_CONTENT);
		setAssignmentType(AssignmentType.DEFAULT);
		setPriority(DEFAULT_PRIORITY);
		setIsNewTentative(false);
		setSpecialContent(DEFAULT_CONTENT);
	}
	
	/**************** Accessors ***********************/
	
	/**
	 * 
	 * @return the CommandType of this RefinedUserInput.
	 */
	public CommandType getCommandType() {
		return this.command;
	}
	
	/**
	 * 
	 * @return the Index of this RefinedUserInput.
	 */
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * 
	 * @return the Title of this RefinedUserInput.
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 
	 * @return the StartDate of this RefinedUserInput.
	 */
	public String getStartDate() {
		return this.startDate;
	}

	/**
	 * 
	 * @return the StartTime of this RefinedUserInput.
	 */
	public String getStartTime() {
		return this.startTime;
	}
	
	/**
	 * 
	 * @return the EndDate of this RefinedUserInput.
	 */
	public String getEndDate() {
		return this.endDate;
	}
	
	/**
	 * 
	 * @return the EndTime of this RefinedUserInput.
	 */
	public String getEndTime() {
		return this.endTime;
	}
	
	/**
	 * 
	 * @return the AssignmentType of this RefinedUserInput.
	 */
	public AssignmentType getAssignmentType() {
		return this.assignment;
	}
	
	/**
	 * 
	 * @return the Priority of this RefinedUserInput.
	 */
	public String getPriority() {
		return this.priority;
	}
	
	/**
	 * 
	 * @return true if this RefinedUserInput contains information for a new tentative input.
	 */
	public Boolean getIsNewTentative() {
		return this.isNewTentative;
	}
	
	/**
	 * 
	 * @return the SpecialContent of this RefinedUserInput.
	 */
	public String getSpecialContent() {
		return this.specialContent;
	}
	
	/**
	 * 
	 * @return the tentative dates of this RefinedUserInput.
	 */
	public Vector<String> getTentativeDates() {
		return this.tentativeDates;
	}
	
	/**
	 * 
	 * @return the tentative times of this RefinedUserInput.
	 */
	public Vector<String> getTentativeTimes() {
		return this.tentativeTimes;
	}
	
	/**************** Mutators **************************/
	
	/**
	 * Changes the current CommandType to the new command.
	 * @param command The new CommandType of this RefinedUserInput.
	 */
	protected void setCommandType(CommandType command) {
		this.command = command;
	}
	
	/**
	 * Changes the current index to the new index.
	 * @param index The new int index of this RefinedUserInput.
	 */
	protected void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * Changes the current title to the new title.
	 * @param title The new String title of this RefinedUserInput.
	 */
	protected void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Changes the current start date to the new start date.
	 * @param startDate The new String start date of this RefinedUSerInput.
	 */
	protected void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Changes the current start time to the new start time.
	 * @param startTime The new String start time of this RefinedUserInput.
	 */
	protected void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * Changes the current end date to the new end date.
	 * @param endDate The new String end date of this RefinedUserInput.
	 */
	protected void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Changes the current end time to the new .
	 * @param endTime The new end time of this RefinedUserIput.
	 */
	protected void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * Changes the current AssignmentType to the new AssignmentType.
	 * @param assignment The new AssignmentType of this RefinedUserInput.
	 */
	protected void setAssignmentType(AssignmentType assignment) {
		this.assignment = assignment;
	}
	
	/**
	 * Changes the current priority to the new priority.
	 * @param priority The new String priority of this RefinedUserInput.
	 */
	protected void setPriority(String priority) {
		this.priority = priority;
	}
	
	/**
	 * Changes the current Boolean state of new tentative information to the new Boolean state of new tentative information.
	 * @param isNewTentative The Boolean state of whether this RefinedUserInput contains new tentative information.
	 */
	protected void setIsNewTentative(Boolean isNewTentative) {
		this.isNewTentative = isNewTentative;
	}
	
	/**
	 * Changes the current special content to the new special content.
	 * @param specialContent The new String special content of this RefinedUserInput.
	 */
	protected void setSpecialContent(String specialContent) {
		this.specialContent = specialContent;
	}
	
	/**
	 * Overrides the old vector of tentative dates with the new vector of tentative dates.
	 * @param tentativeDate The new Vector of tentative dates of this RefinedUserInput.
	 */
	protected void setTentativeDates(Vector <String> tentativeDate) {
		this.tentativeDates = (tentativeDate);
	}
	
	/**
	 * Overrides the old vector of tentative times with the new vector of tentative times.
	 * @param tentativeTime the new Vector of tentative times of this RefinedUserInput.
	 */
	protected void setTentativeTimes(Vector <String> tentativeTime) {
		this.tentativeTimes = (tentativeTime);
	}
	
	/**************** Overriding ************************/
	
	public String toString() {
		return getCommandType() + "~" + getIndex() + "~" + getTitle() + "~" + getStartDate() + "~" + getStartTime() + "~"
				+ getEndDate() + "~" + getEndTime() + "~" + getAssignmentType() + "~" + getPriority( )+ "~"
				+ getIsNewTentative() + "~" + getSpecialContent() + "~" + getTentativeDates() + "~" + getTentativeTimes();
	}
}