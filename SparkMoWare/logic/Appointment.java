package logic;

import java.util.Vector;

public class Appointment extends Task {

	/************** Data members **********************/
	
	protected static int numAppointment = 0;
	protected String startDate; // in the format DDMMYY, if inactive null
	protected String startTime; // in the format HHMM, if inactive null
	
	/************** Constants **********************/
	
	protected static final int TYPE_APPOINTMENT = 1;
	
	/************** Constructors **********************/
	// Default constructor
	public Appointment() {
		this(DEFAULT, DEFAULT_STRING, -1, DEFAULT, DEFAULT, DEFAULT, DEFAULT, false, false, null, new Vector<String>());
	}
	

	public Appointment(String id, String title, int type, String startDate, String startTime,
			String endDate, String endTime, boolean isDone, boolean isOnTime, String priority, Vector<String> tag) {
		
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
	}

	/**************** Accessors ***********************/
	public String getTitle() {
		return this.title;
	}

	public String getId() {
		return this.id;
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

	//public String getDescription() {
	//	return description;
	//}

	//public int getAlarm() {
	//	return alarm;
	//}
	
	//public Vector<String> getTag() {
	//	return this.tag;
	//}
	
	public int getType() {
		return this.type;
	}
	
	public boolean getIsDone() {
		return this.isDone;
	}
	
	public boolean getIsOnTime() {
		return this.isOnTime;
	}
	
	public String getPriority() {
		return this.priority;
	}
	
	public int getNumAppointment() {
		return numAppointment;
	}

	/**************** Mutators ************************/
	
	public void setStartDate(String newstartDate) {
		startDate = newstartDate;
	}
	
	public void setStartTime(String newStartTime) {
		startTime = newStartTime;
	}
	
	public void setNumAppointment(int newNumAppointment){
		numAppointment = newNumAppointment;
	}
	
	/**************** Overriding ************************/
	
	@Override
	public String toString() {
		return getId() + "~" + getTitle() + "~" + getType() + "~" + getStartDate() + "~" + 
				getStartTime() + "~" + getEndDate() + "~" + getEndTime() + "~" + getIsDone() + 
				"~" + getIsOnTime() + "~" + getPriority();
	}
	

}
