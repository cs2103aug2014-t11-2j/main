package parser;

public class UserInputClear extends UserInput{
	
	/************** Data members **********************/
	
	private String duration;
	private String endDate;
	
	/************** Constructors **********************/
	
	protected UserInputClear() {
		
	}
	
	protected UserInputClear(String duration, String endDate) {
		setCommandType(CommandType.CLEAR);
		setDuration(duration);
		setEndDate(endDate);
	}
	
	/**************** Accessors ***********************/
	
	protected String getDuration() {
		return this.duration;
	}
	
	protected String getEndDate() {
		return this.endDate;
	}
	
	/**************** Mutators ************************/

	private void setDuration(String duration) {
		this.duration = duration;
	}
	
	private void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**************** Overriding ************************/
	
	public String toString() {
		return getCommandType() + "~" + getDuration() + "~" + getEndDate();
	}
}
