package logic;

import java.util.Vector;

public class Assignment extends Appointment{

	/************** Data members **********************/

	
	/************** Constants **********************/
	
	private static final String DEFAULT_STRING = "DEFAULT";
	private static final String DEFAULT = "-1";
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
	
	/**************** Overriding ************************/
	
}