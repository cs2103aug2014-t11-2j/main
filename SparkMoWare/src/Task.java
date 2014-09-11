import java.util.Vector;

class Task {

	/************** Data members **********************/
	private static int numTask = 0;
	private String title;
	private int date;		// date of creation;in the format DDMMYY
	private int deadline;	// in the format DDMMYY, if inactive null
	private String description;
	private int alarm;		// in the format HHMM, if inactive null
	private Vector<String> tag;	// to support tagging
	
	/************** Constructors **********************/
	// Default constructor
	private Task() {
	}

	/**************** Accessors ***********************/
	private String getTitle() {
		return title;
	}

	private int getDate() {
		return date;
	}

	private int getDeadline() {
		return deadline;
	}

	private String getDescription() {
		return description;
	}

	private int getAlarm() {
		return alarm;
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

	public void setDeadline(int newDeadline) {
		deadline = newDeadline;
	}

	public void setDescription(String newDescription) {
		description = newDescription;
	}

	public void setAlarm(int newAlarm) {
		alarm = newAlarm;
	}
	
	private void setTag(Vector<String> newTag) {
		tag = newTag;
	}

}

