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
	// Default constructor
	public Appointment() {
		this(DEFAULT_DATE, DEFAULT_TIME);
		
		//this(DEFAULT, DEFAULT_STRING, DEFAULT, DEFAULT_DATE, DEFAULT_TIME, DEFAULT_DATE, DEFAULT_TIME, false, false, PRIORITY_NONE);
	}
	
	/*public Appointment(String id, String title, String type, String startDate, String startTime,
			String endDate, String endTime, boolean isDone, boolean isOnTime, String priority) {
		
		setId(id);
		setTitle(title);
		setType(type);
		setStartDate(startDate);
		setStartTime(startTime);
		setEndDate(endDate);
		setEndTime(endTime);
		setIsDone(isDone);
		setIsOnTime(isOnTime);
		setPriority(priority);
		//setTag(tag);
		numAppointment++;
	}*/
	
	public Appointment(String startDate, String startTime) {
		
		super();
		setStartDate(startDate);
		setStartTime(startTime);
		setAssignType(assignmentType.APPOINTMENT);
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
	
	public void setStartDate(String newstartDate) {
		this.startDate = newstartDate;
	}
	
	public void setStartTime(String newStartTime) {
		this.startTime = newStartTime;
	}
	
	public void setNumAppointment(int numAppointment) {
		this.numAppointment = numAppointment;
	}

		/**************** Overriding ************************/
	
	public String toString() {
		return getId() + "~" + getTitle() + "~" + getAssignType() + "~" + getStartDate() + "~" + 
				getStartTime() + "~" + getEndDate() + "~" + getEndTime() + "~" + getIsDone() + 
				"~" + getIsOnTime() + "~" + getPriority();
	}
}