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
		
	}
	
	public RefinedUserInput(CommandType command, String id,
							String title, String startDate,
							String startTime, String endDate,
							String endTime, AssignmentType assignment,
							String specialContent) {
		setCommandType(command);
		setId(id);
		setTitle(title);
		setStartDate(startDate);
		setStartTime(startTime);
		setEndDate(endDate);
		setEndTime(endTime);
		setAssignmentType(assignment);
		setSpecialContent(specialContent);
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
	
	private void setCommandType(CommandType command) {
		this.command = command;
	}
	
	private void setId(String id) {
		this.id = id;
	}
	
	private void setTitle(String title) {
		this.title = title;
	}
	
	private void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	private void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	private void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	private void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	private void setAssignmentType(AssignmentType assignment) {
		this.assignment = assignment;
	}
	
	private void setSpecialContent(String specialContent) {
		this.specialContent = specialContent;
	}
	
	/**************** Overriding ************************/
	
	public String toString() {
		return getCommandType() + "~" + getId() + "~" + getTitle() + "~" + getStartDate() + "~" + getStartTime() + "~"
				+ getEndDate() + "~" + getEndTime() + "~" + getAssignmentType() + "~" + getSpecialContent();
	}
}
