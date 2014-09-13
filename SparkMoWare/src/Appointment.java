import java.util.Vector;

class Appointment {

	/************** Data members **********************/
	private static int numAppointment = 0;
	private String title;
	private int date;			// date of creation;in the format DDMMYY
	private int startDate;		// in the format DDMMYY, if inactive null
	private int startTime;		// in the format HHMM, if inactive null
	private int endDate;		// in the format DDMMYY, if inactive null
	private int endTime;		// in the format HHMM, if inactive null
	private String description;
	private int alarm;			// in the format HHMM, if inactive null
	private int duration;		// in the format HHMM
	private Vector<String> tag;	// to support tagging

	/************** Constructors **********************/
	// Default constructor
	private Appointment() {
	}

	/**************** Accessors ***********************/
	private String getTitle() {
		return title;
	}

	private int getDate() {
		return date;
	}

	private int getStartDate() {
		return startDate;
	}
	
	private int getStartTime() {
		return startTime;
	}
	
	private int getEndDate() {
		return endDate;
	}
	
	private int getEndTime() {
		return endTime;
	}

	private String getDescription() {
		return description;
	}

	private int getAlarm() {
		return alarm;
	}
	
	private int getduration() {
		return duration;
	}
	
	private Vector<String> getTag() {
		return tag;
	}

	/**************** Mutators ************************/
	private void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setDate(int newDate) {
		date = newDate;
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


	public void setDescription(String newDescription) {
		description = newDescription;
	}

	public void setAlarm(int newAlarm) {
		alarm = newAlarm;
	}
	
	private void setDuration(int newDuration) {
		duration = newDuration;
	}
	
	private void setTag(Vector<String> newTag) {
		tag = newTag;
	}

}

