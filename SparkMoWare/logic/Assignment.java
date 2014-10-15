package logic;

import java.util.Vector;

public class Assignment{

	/************** Data members **********************/

	protected String title;
	protected String id; // date of creation;in the format DDMMYY
	protected String startDate; // in the format DDMMYY, if inactive null
	protected String startTime; // in the format HHMM, if inactive null
	protected String endDate; // in the format DDMMYY, if inactive null
	protected String endTime; // in the format HHMM, if inactive null
	protected boolean isDone;
	protected boolean isOnTime;
	protected int type; // type of assignment
	protected String priority; // important else null
	// private String description;
	// private int alarm; // in the format HHMM, if inactive null
	// private Vector<String> tag; // to support tagging
	protected static int numAppointment = 0;
	
	/************** Constants **********************/
	
	protected static final String DEFAULT_STRING = "DEFAULT";
	protected static final String DEFAULT = "-1";
	protected static final int TYPE_TASK = 0;
	protected static final int TYPE_APPOINTMENT = 1;
	protected static final int TYPE_TENTATIVE = 2;
	
	/************** Constructors **********************/
	// Default constructor
	public Assignment() {
		this(DEFAULT, DEFAULT_STRING, -1, DEFAULT, DEFAULT, DEFAULT, DEFAULT, false, false, null, new Vector<String>());
	}
	
	public Assignment(String id, String title, int type, String startDate, String startTime,
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

	// public void setDescription(String newDescription) {
	// description = newDescription;
	// }

	// public void setAlarm(int newAlarm) {
	// alarm = newAlarm;
	// }

	// public void setTag(Vector<String> newTag) {
	// tag = newTag;
	// }

	public void setType(int newType) {
		type = newType;
	}

	public void setIsDone(boolean newIsDone) {
		isDone = newIsDone;
	}

	public void setIsOnTime(boolean newIsOnTime) {
		isOnTime = newIsOnTime;
	}

	public void setPriority(String newPriority) {
		priority = newPriority;
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