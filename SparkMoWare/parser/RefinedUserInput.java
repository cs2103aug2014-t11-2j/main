package parser;

import java.util.Vector;

import logic.Assignment.AssignmentType;
import parser.EnumGroup.CommandType;

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
	/************** Constructors **********************/
	
	public RefinedUserInput() {
		
		setCommandType(EnumGroup.CommandType.DEFAULT);
		setIndex(0);
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
	
	public CommandType getCommandType() {
		return this.command;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getStartDate() {
		return this.startDate;
	}
	
	public String getStartTime() {
		return this.startTime;
	}
	
	public String getEndDate() {
		return this.endDate;
	}
	
	public String getEndTime() {
		return this.endTime;
	}
	
	public AssignmentType getAssignmentType() {
		return this.assignment;
	}

	public String getPriority() {
		return this.priority;
	}
	
	public Boolean getIsNewTentative() {
		return this.isNewTentative;
	}
	
	public String getSpecialContent() {
		return this.specialContent;
	}
	
	public Vector<String> getTentativeDates() {
		return this.tentativeDates;
	}
	
	public Vector<String> getTentativeTimes() {
		return this.tentativeTimes;
	}
	
	/**************** Mutators **************************/
	
	protected void setCommandType(CommandType command) {
		this.command = command;
	}
	
	protected void setIndex(int index) {
		this.index = index;
	}
	
	protected void setTitle(String title) {
		this.title = title;
	}
	
	protected void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	protected void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	protected void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	protected void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	protected void setAssignmentType(AssignmentType assignment) {
		this.assignment = assignment;
	}
	
	protected void setPriority(String priority) {
		this.priority = priority;
	}
	
	protected void setIsNewTentative(Boolean isNewTentative) {
		this.isNewTentative = isNewTentative;
	}
	
	protected void setSpecialContent(String specialContent) {
		this.specialContent = specialContent;
	}
	
	protected void setTentativeDates(Vector <String> tentativeDate) {
		this.tentativeDates = (tentativeDate);
	}
	
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