package parser;

public class UserInputAddTask extends UserInput{

	/************** Data members **********************/
	
	private String title;
	private String endDate;
	private String endTime;
	private EnumGroup.AssignmentType assignment;
	
	/************** Constructors **********************/
	
	protected UserInputAddTask() {
		
	}
	
	protected UserInputAddTask(String title, String endDate, String endTime) {
		setCommandType(EnumGroup.CommandType.ADD);
		setTitle(title);
		setEndDate(endDate);
		setEndTime(endTime);
		setAssignmentType(EnumGroup.AssignmentType.TASK);
	}
	
	/**************** Accessors ***********************/
	
	protected String getTitle() {
		return this.title;
	}
	
	protected String getEndDate() {
		return this.endDate;
	}
	
	protected String getEndTime() {
		return this.endTime;
	}
	
	protected EnumGroup.AssignmentType getAssignmentType() {
		return this.assignment;
	}
	
	/**************** Mutators **************************/
	
	private void setTitle(String title) {
		this.title = title;
	}
	
	private void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	private void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	protected void setAssignmentType(EnumGroup.AssignmentType assignment) {
		this.assignment = assignment;
	}
	
	/**************** Overriding ************************/
	
	public String toString() {
		return getCommandType() + "~" + getTitle() + "~" + getEndDate() + "~" + getEndTime() + "~" + getAssignmentType();
	}
}
