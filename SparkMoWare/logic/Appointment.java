package logic;

public class Appointment extends Task {

	/************** Data members **********************/
	
	private String startDate;
	private String startTime;
	private int numAppointment = 0;
	
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
		numAppointment++;
	}

	/**************** Accessors ***********************/
	
	public String getStartDate() {
		return this.startDate;
	}
	
	public String getStartTime() {
		return this.startTime;
	}
	
	public int getNumAppointment() {
		return this.numAppointment;
	}
	
	/**************** Mutators ************************/
	
	public void setStartDate(String newStartDate) {
		this.startDate = newStartDate;
	}
	
	public void setStartTime(String newStartTime) {
		this.startTime = newStartTime;
	}
	
	public void setNumAppointment(int numAppointment) {
		this.numAppointment = numAppointment;
	}

	/**************** Overriding ************************/
	
	public String toString() {
		return getDateCreation() + "~" + getIndex() + "~" + getAssignType() + "~" + getTitle() + "~" 
				+ getStartDate() + "~" + getStartTime() + "~" + getEndDate() + "~" + getEndTime() + "~" 
				+ getIsDone() +	"~" + getIsOnTime() + "~" + getPriority();
	}
}