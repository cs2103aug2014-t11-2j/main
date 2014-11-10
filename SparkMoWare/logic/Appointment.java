package logic;

//@author A0111572R

public class Appointment extends Task {

	/************** Data members **********************/
	
	private String startDate;
	private String startTime;
	
	/************** Constants **********************/
	
	private static final String DEFAULT_DATE = "01012014";
	private static final String DEFAULT_TIME = "0000";
	
	/************** Constructors **********************/

	public Appointment() {
		this(DEFAULT_DATE, DEFAULT_TIME);
	}
	
	public Appointment(String startDate, String startTime) {
		
		super();
		setStartDate(startDate);
		setStartTime(startTime);
		setAssignType(AssignmentType.APPT);
	}

	/**************** Accessors ***********************/
	
	public String getStartDate() {
		return this.startDate;
	}
	
	public String getStartTime() {
		return this.startTime;
	}
	
	/**************** Mutators ************************/
	
	public void setStartDate(String newStartDate) {
		this.startDate = newStartDate;
	}
	
	public void setStartTime(String newStartTime) {
		this.startTime = newStartTime;
	}

	/**************** Overriding ************************/
	
	public String toString() {
		return getIndex() + "~" + getAssignType() + "~" + getTitle() + "~" 
				+ getStartDate() + "~" + getStartTime() + "~" + getEndDate() + "~" + getEndTime() + "~" 
				+ getIsDone() +	"~" + getIsOnTime() + "~" + getPriority();
	}
}