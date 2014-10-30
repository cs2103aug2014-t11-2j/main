package parser;

import java.util.Vector;

import parser.EnumGroup.AssignmentType;
import parser.EnumGroup.CommandType;

public class RefinedUserInput {
	
	/************** Data members **********************/
	
	private CommandType command;
	private String id;
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
	
	/************** Constructors **********************/
	
	public RefinedUserInput() {
		final String defaultContent  = "default";
		final String defaultPriority = "NIMPT";
		setCommandType(EnumGroup.CommandType.DEFAULT);
		setId(defaultContent);
		setTitle(defaultContent);
		setStartDate(defaultContent);
		setStartTime(defaultContent);
		setEndDate(defaultContent);
		setEndTime(defaultContent);
		setAssignmentType(EnumGroup.AssignmentType.DEFAULT);
		setPriority(defaultPriority);
		setIsNewTentative(false);
		setSpecialContent(defaultContent);
	}
	
/**************** Accessors ***********************/
	
	public CommandType getCommandType() {
		return this.command;
	}
	
	public String getId() {
		return this.id;
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
	
	protected void setId(String id) {
		this.id = id;
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
		return getCommandType() + "~" + getId() + "~" + getTitle() + "~" + getStartDate() + "~" + getStartTime() + "~"
				+ getEndDate() + "~" + getEndTime() + "~" + getAssignmentType() + "~" + getPriority( )+ "~"
				+ getIsNewTentative() + "~" + getSpecialContent() + "~" + getTentativeDates() + "~" + getTentativeTimes();
	}
}