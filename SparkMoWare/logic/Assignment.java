package logic;

import java.util.Vector;

class Assignment {

	/************** Data members **********************/
	private static int numAppointment = 0;
	private String title;
	private int id;				// date of creation;in the format DDMMYY
	private int startDate;		// in the format DDMMYY, if inactive null
	private int startTime;		// in the format HHMM, if inactive null
	private int endDate;		// in the format DDMMYY, if inactive null
	private int endTime;		// in the format HHMM, if inactive null
	private boolean isDone;
	//private String description;
	//private int alarm;			// in the format HHMM, if inactive null
	private Vector<String> tag;	// to support tagging
	private int type;			// type of assignment
	
	/************** Constants **********************/
	
	private static final String DEFAULT_STRING = "DEFAULT";
	private static final int DEFAULT = -1;
	@SuppressWarnings("unused")
	private static final int TASK = 0;
	@SuppressWarnings("unused")
	private static final int APPOINTMENT = 1;
	private static final int TENTATIVE = 2;
	
	/************** Constructors **********************/
	// Default constructor
	Assignment() {
		id = DEFAULT;
		title = DEFAULT_STRING;
		type = DEFAULT;
		startDate = DEFAULT;
		startTime = DEFAULT;
		endDate = DEFAULT;
		endTime = DEFAULT;
		isDone = false;
		tag = new Vector<String>();
	}

	/**************** Accessors ***********************/
	public String getTitle() {
		return title;
	}

	public int getId() {
		return id;
	}

	public int getStartDate() {
		return startDate;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getEndDate() {
		return endDate;
	}
	
	public int getEndTime() {
		return endTime;
	}

	//public String getDescription() {
	//	return description;
	//}

	//public int getAlarm() {
	//	return alarm;
	//}
	
	public Vector<String> getTag() {
		return tag;
	}
	
	public int getType() {
		return type;
	}
	
	public boolean getIsDone(){
		return isDone;
	}

	/**************** Mutators ************************/
	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setId(int newId) {
		id = newId;
	}

	public void setStartDate(int newstartDate) {
		startDate = newstartDate;
	}
	
	public void setStartTime(int newStartTime) {
		startTime = newStartTime;
	}
	
	public void setEndDate(int newEndDate) {
		endDate = newEndDate;
	}
	
	public void setEndTime(int newEndTime) {
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
}