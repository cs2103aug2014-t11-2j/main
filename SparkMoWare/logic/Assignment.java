package logic;

import java.util.Vector;

public class Assignment {

	/************** Data members **********************/
	private static int numAppointment = 0;
	private String title;
	private String id;			// date of creation;in the format DDMMYY
	private String startDate;	// in the format DDMMYY, if inactive null
	private String startTime;	// in the format HHMM, if inactive null
	private String endDate;		// in the format DDMMYY, if inactive null
	private String endTime;		// in the format HHMM, if inactive null
	private boolean isDone;
	private boolean isOnTime;
	private int type;			// type of assignment
	private int priority;		// 0 1 2 where 0 is low 1 is normal 2 is high
	//private String description;
	//private int alarm;		// in the format HHMM, if inactive null
	private Vector<String> tag;	// to support tagging

	
	/************** Constants **********************/
	
	private static final String DEFAULT_STRING = "DEFAULT";
	protected static final int TYPE_TASK = 0;
	private static final String DEFAULT = "-1";
	protected static final int TYPE_APPOINTMENT = 1;
	protected static final int TYPE_TENTATIVE = 2;
	
	/************** Constructors **********************/
	// Default constructor
	public Assignment() {
		this(DEFAULT, DEFAULT_STRING, -1, DEFAULT, DEFAULT, DEFAULT, DEFAULT, false, false, 1, new Vector<String>());
	}
	
	public Assignment(String id, String title, int type, String startDate, String startTime,
			String endDate, String endTime, boolean isDone, boolean isOnTime, int priority, Vector<String> tag) {
		
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
		setTag(tag);
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
	
	public Vector<String> getTag() {
		return this.tag;
	}
	
	public int getType() {
		return this.type;
	}
	
	public boolean getIsDone() {
		return this.isDone;
	}
	
	public boolean getIsOnTime() {
		return this.isOnTime;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public int getNumAppointment() {
		return numAppointment;
	}

	/**************** Mutators ************************/
	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setId(String newId) {
		id = newId;
	}

	public void setStartDate(String newstartDate) {
		startDate = newstartDate;
	}
	
	public void setStartTime(String newStartTime) {
		startTime = newStartTime;
	}
	
	public void setEndDate(String newEndDate) {
		endDate = newEndDate;
	}
	
	public void setEndTime(String newEndTime) {
		endTime = newEndTime;
	}


	//public void setDescription(String newDescription) {
	//	description = newDescription;
	//}

	//public void setAlarm(int newAlarm) {
	//	alarm = newAlarm;
	//}
		
	public void setTag(Vector<String> newTag) {
		tag = newTag;
	}
	
	public void setType(int newType) {
		type = newType;
	}
	
	public void setIsDone(boolean newIsDone){
		isDone = newIsDone;
	}
	
	public void setIsOnTime(boolean newIsOnTime){
		isOnTime = newIsOnTime;
	}
	
	public void setPriority(int newPriority){
		priority = newPriority;
	}
	
	public void setNumAppointment(int newNumAppointment){
		numAppointment = newNumAppointment;
	}
	
	/**************** Overriding ************************/
	
	@Override
	public String toString() {
		return getId() + "~ " + getTitle() + "~ " + getType() + "~ " + getStartDate() + "~ " + 
				getStartTime() + "~ " + getEndDate() + "~ " + getEndTime() + "~ " + getIsDone() + 
				"~ " + getIsOnTime() + "~ " + getPriority();
	}
	
}