package parser;

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
	private String specialContent;
	/* Special content is for the following command cases:
	 * delete all (on, before, during)
	 * edit which is to be edited (title, start date, end date, etc.)
	 * tentative (number of days)
	 * sort and search (date, serial number, etc.)
	 */
	
	/************** Constructors **********************/
	
	public RefinedUserInput() {
		final String defaultContent  = "default";
		setCommandType(EnumGroup.CommandType.DEFAULT);
		setId(defaultContent);
		setTitle(defaultContent);
		setStartDate(defaultContent);
		setStartTime(defaultContent);
		setEndDate(defaultContent);
		setEndTime(defaultContent);
		setAssignmentType(EnumGroup.AssignmentType.DEFAULT);
		setSpecialContent(defaultContent);
	}
	
/**************** Accessors ***********************/
	
	protected CommandType getCommandType() {
		return this.command;
	}
	
	protected String getId() {
		return this.id;
	}
	
	protected String getTitle() {
		return this.title;
	}
	
	protected String getStartDate() {
		return this.startDate;
	}
	
	protected String getStartTime() {
		return this.startTime;
	}
	
	protected String getEndDate() {
		return this.endDate;
	}
	
	protected String getEndTime() {
		return this.endTime;
	}
	
	protected AssignmentType getAssignmentType() {
		return this.assignment;
	}
	
	protected String getSpecialContent() {
		return this.specialContent;
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
	
	protected void setSpecialContent(String specialContent) {
		this.specialContent = specialContent;
	}
	
	/**************** Overriding ************************/
	
	public String toString() {
		return getCommandType() + "~" + getId() + "~" + getTitle() + "~" + getStartDate() + "~" + getStartTime() + "~"
				+ getEndDate() + "~" + getEndTime() + "~" + getAssignmentType() + "~" + getSpecialContent();
	}
}
