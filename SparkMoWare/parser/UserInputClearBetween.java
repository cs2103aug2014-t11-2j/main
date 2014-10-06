package parser;

public class UserInputClearBetween extends UserInputClear {
	
	/************** Data members **********************/
	
	private String startDate;
	
	/************** Constructors **********************/
	
	public UserInputClearBetween() {
		
	}
	
	public UserInputClearBetween(String duration, String startDate, String endDate) {
		super(duration, endDate);
		setStartDate(startDate);
	}
	
	/**************** Accessors ***********************/
	
	protected String getStartDate() {
		return this.startDate;
	}
	
	/**************** Mutators ************************/
	
	private void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**************** Overriding ************************/
	
	public String toString() {
		return getCommandType() + "~" + getDuration() + "~" + getStartDate() + "~" + getEndDate();
	}
}
