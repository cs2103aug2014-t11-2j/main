package parser;

public class UserInputAddTask extends UserInput{

	/************** Data members **********************/
	
	private String title;
	private String endDate;
	private String endTime;
	private AssignmentType assignment;
	
	/************** Constructors **********************/
	
	protected UserInputAddTask() {
		
	}
	
	protected UserInputAddTask(String title, String endDate, String endTime) {
		setCommandType(CommandType.ADD);
		setTitle(title);
		setEndDate(endDate);
		setEndTime(endTime);
		setAssignmentType(AssignmentType.TASK);
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
	
	protected AssignmentType getAssignmentType() {
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
	
	protected void setAssignmentType(AssignmentType assignment) {
		this.assignment = assignment;
	}
	
	/**************** Overriding ************************/
	
	public String toString() {
		return getCommandType() + "~" + getTitle() + "~" + getEndDate() + "~" + getEndTime() + "~" + getAssignmentType();
	}
}
